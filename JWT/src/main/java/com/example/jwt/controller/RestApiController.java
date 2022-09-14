package com.example.jwt.controller;

import com.example.jwt.Model.User;
import com.example.jwt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class RestApiController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("home")
    public String home(){
        return "<h1>home</h1>";
    }

    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입완료";
    }

    //user,admin 권한만 접근가능
    @GetMapping("user")
    public String user(){
        return "user";
    }

    //manager,admin 권한만 접근가능
    @GetMapping("moderator")
    public String manager(){
        return "moderator";
    }

    //admin 권한만 접근가능
    @GetMapping("admin")
    public String admin(){
        return "admin";
    }
}
