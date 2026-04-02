package com.mhcoder.services;

import com.mhcoder.config.SpringConfig;
import com.mhcoder.dto.ExceptionResponse;
import com.mhcoder.dto.UserDetails;
import com.mhcoder.dto.UserLogin;
import com.mhcoder.models.User;
import com.mhcoder.repository.UserReposistory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserReposistory userReposistory;

    @Autowired
    private SpringConfig springConfig;
    public ExceptionResponse createUser(UserDetails userDetails) {

        User user=new User();
        user.setId(userDetails.getId());
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPassword(springConfig.passwordEncoder().encode(user.getPassword()));
        userReposistory.save(user);

        ExceptionResponse response=new ExceptionResponse();
        response.setMessage("user Signup success..");

        return response;
    }

    public UserDetails userLogin(UserLogin userLogin) {

        User user = userReposistory.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new RuntimeException("Plz enter valid email id"));

        if (!springConfig.passwordEncoder().matches(userLogin.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setFirstname(user.getFirstname());
        userDetails.setLastname(user.getLastname());

        return userDetails;
    }
    public ExceptionResponse updateUser(Long id, UserDetails userDetails) {

        User details = userReposistory.findById(id)
                .orElseThrow(() -> new RuntimeException("User id not found : " + id));

        details.setFirstname(userDetails.getFirstname());
        details.setLastname(userDetails.getLastname());
        details.setPassword(springConfig.passwordEncoder().encode(userDetails.getPassword()));

        userReposistory.save(details);

        ExceptionResponse response=new ExceptionResponse();
        response.setMessage("user Signup success..");
        return response;
    }

    public UserDetails getByUserId(Long id) {
        User user=userReposistory.findById(id).orElseThrow(() -> new RuntimeException("User id not found : " + id));
        UserDetails details=new UserDetails();
        details.setId(user.getId());
        details.setFirstname(user.getFirstname());
        details.setLastname(user.getLastname());
        details.setEmail(user.getEmail());

        return details;
    }

    public ExceptionResponse deleteByUserId(Long id) {
        User user = userReposistory.findById(id)
                .orElseThrow(() -> new RuntimeException("User id not found : " + id));

        userReposistory.deleteById(id);
        ExceptionResponse response=new ExceptionResponse();
        response.setMessage("user Signup success..");
        return response;
    }

    public List<UserDetails> allUserData() {
        List<User> users = userReposistory.findAll();

        List<UserDetails> userDetailsList = new ArrayList<>();

        for (User user : users) {
            UserDetails details = new UserDetails();
            details.setId(user.getId());
            details.setFirstname(user.getFirstname());
            details.setLastname(user.getLastname());
            details.setEmail(user.getEmail());

            userDetailsList.add(details);
        }

        return userDetailsList;
    }

}
