package com.jobinjose.talize.controller;

import com.jobinjose.talize.dao.UserDao;
import com.jobinjose.talize.model.UserModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao udao;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/signup", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UserSignup(@RequestBody UserModel user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        HashMap<String, String> hmap = new HashMap<>();
        List<UserModel> users = udao.getUserByEmail(user.getEmail());
        if(users.size() != 0) {
            hmap.put("status", "failed");
        } else {
            String encPassword = encoder.encode(user.getPassword());
            user.setPassword(encPassword);
            udao.save(user);
            List<UserModel> userDetails = udao.getUserByEmailAndPassword(user.getEmail(), encPassword);
            if(userDetails.size() != 0) {
                hmap.put("encTocken", userDetails.get(0).getEncTocken());
                hmap.put("status", "success");
            } else {
                hmap.put("status", "failed");
            }
        }
        return hmap;
    }

    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UserLogin(@RequestBody UserModel user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        HashMap<String, String> hmap = new HashMap<>();
        List<UserModel> users = udao.getUserByEmail(user.getEmail());
        if(users.size() != 0) {
            if(encoder.matches(user.getPassword(), users.get(0).getPassword())) {
                hmap.put("status", "success");
            } else {
                hmap.put("status", "failed");
            }
        } else {
            hmap.put("status", "failed");
        }
        return hmap;
    }
}
