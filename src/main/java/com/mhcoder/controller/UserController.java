package com.mhcoder.controller;

import com.mhcoder.dto.ExceptionResponse;
import com.mhcoder.dto.UserDetails;
import com.mhcoder.dto.UserLogin;
import com.mhcoder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ExceptionResponse> createUser(@RequestBody UserDetails userDetails){
        ExceptionResponse result=userService.createUser(userDetails);
        return new ResponseEntity<ExceptionResponse>(result,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDetails> userLogin(@RequestBody UserLogin userLogin){
        UserDetails userDetails=userService.userLogin(userLogin);
        if (userDetails != null) {
            return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
        }
        return new ResponseEntity<UserDetails>(userDetails,HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<UserDetails>> alluserData(){
        List<UserDetails> userDetails=userService.allUserData();
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }
    @PutMapping("/updateUserPassword/{id}")
    public ResponseEntity<ExceptionResponse> userUpdate(@PathVariable Long id, @RequestBody UserDetails userDetails){
        ExceptionResponse userData=userService.updateUser(id,userDetails);
        return new ResponseEntity<ExceptionResponse>(userData,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getByUserId(@PathVariable Long id){
        UserDetails details=userService.getByUserId(id);
        return new ResponseEntity<UserDetails>(details,HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ExceptionResponse> userDelete(@PathVariable Long id){
        ExceptionResponse userData=userService.deleteByUserId(id);
        return  new ResponseEntity<ExceptionResponse>(userData,HttpStatus.OK);
    }

}
