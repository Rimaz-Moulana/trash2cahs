package com.trash2cash.trash2cash.controller;

import com.trash2cash.trash2cash.dto.UserDto;
import com.trash2cash.trash2cash.entity.AuthMobileRequest;
import com.trash2cash.trash2cash.entity.AuthRequest;
import com.trash2cash.trash2cash.entity.UserInfo;
import com.trash2cash.trash2cash.service.JwtService;
import com.trash2cash.trash2cash.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @GetMapping("/welcome")
//    public String welcome() {
//        return "Welcome this endpoint is not secure";
//    }

    @PostMapping("/signup")
    public ResponseEntity<String> addNewUser(@RequestBody UserDto userDto) {
        try{
            UserInfo userInfo = new UserInfo();

            userInfo.setFirstname(userDto.getFirstname());
            userInfo.setLastname(userDto.getLastname());
            userInfo.setEmail(userDto.getEmail());
            userInfo.setPassword(userDto.getPassword());
            userInfo.setRoles(userDto.getRoles());
            userInfo.setDob(userDto.getDob());
            userInfo.setGender(userDto.getGender());
            userInfo.setMobilenumber(userDto.getMobilenumber());
            userInfo.setLandline(userDto.getLandline());
            userInfo.setUsertype(userDto.getUsertype());
            userInfo.setStatus(userDto.getStatus());
            userInfo.setUpdated_at(userDto.getUpdated_at());
            userInfo.setCreated_at(userDto.getCreated_at());
            userInfo.setVerified(userDto.getVerified());

            service.addUser(userInfo);

            return ResponseEntity.ok("Added Successfully!");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error Occurred");
        }

    }

    @PostMapping("/signin")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/signinmobile")
    public String authenticateAndGetTokenUsingMobile(@RequestBody AuthMobileRequest authMobileRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authMobileRequest.getMobilenumber(), authMobileRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authMobileRequest.getMobilenumber());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
