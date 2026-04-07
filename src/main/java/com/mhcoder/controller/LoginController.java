package com.mhcoder.controller;

import com.mhcoder.dto.UserDetails;
import com.mhcoder.dto.UserLogin;
import com.mhcoder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDetails> userLogin(@RequestBody UserLogin userLogin){
        System.out.println("ROLE FROM REQUEST: " + userLogin.getRole()); // 👈 ADD THIS
        UserDetails userDetails=userService.userLogin(userLogin);
        if (userDetails != null) {
            return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
        }
        return new ResponseEntity<UserDetails>(userDetails,HttpStatus.UNAUTHORIZED);
    }
}
