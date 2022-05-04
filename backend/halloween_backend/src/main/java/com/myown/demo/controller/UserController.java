
package com.myown.demo.controller;

import com.myown.demo.model.User;
import com.myown.demo.repository.UserDaoJDBC;
import com.myown.demo.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
/*@CrossOrigin(origins = "http://localhost", maxAge = 3600)*/
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDaoJDBC userDaoJDBC;

    @Autowired
    private EmailSender emailSender;

    @CrossOrigin
    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        String response = userDaoJDBC.add(user);
        if(response.equals("200 ok")){
            emailSender.createAndSendEmail(user);
        }
        return response;
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllUser(){
        return userDaoJDBC.deleteAll();
    }

}
