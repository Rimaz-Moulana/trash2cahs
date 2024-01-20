package com.trash2cash.trash2cash.controller;

import com.trash2cash.trash2cash.dto.UserDto;
import com.trash2cash.trash2cash.entity.AuthRequest;
import com.trash2cash.trash2cash.entity.UserInfo;
import com.trash2cash.trash2cash.service.JwtService;
import com.trash2cash.trash2cash.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserInfoService service;

//    @GetMapping("/welcome")
//    public String welcome() {
//        return "Welcome this endpoint is not secure";
//    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAuthority('user')")
    public List<UserDto> viewProfile(@PathVariable Long id) {
        return service.getProfile(id);
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('user')")
    public List<UserDto> viewAllProfile() {

        return service.getUsers();
    }

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<String> updateUserInfo(@PathVariable("userId") int userId, @RequestBody UserInfo updatedUserInfo) {
        try{
            UserInfo userInfo = new UserInfo();

            userInfo.setId(Math.toIntExact(userId));
            userInfo.setFirstname(updatedUserInfo.getFirstname());
            userInfo.setLastname(updatedUserInfo.getLastname());
            userInfo.setEmail(updatedUserInfo.getEmail());
            userInfo.setPassword(updatedUserInfo.getPassword());
            userInfo.setRoles(updatedUserInfo.getRoles());
            userInfo.setDob(updatedUserInfo.getDob());
            userInfo.setGender(updatedUserInfo.getGender());
            userInfo.setMobilenumber(updatedUserInfo.getMobilenumber());
            userInfo.setLandline(updatedUserInfo.getLandline());
            userInfo.setUsertype(updatedUserInfo.getUsertype());
            userInfo.setStatus(updatedUserInfo.getStatus());
            userInfo.setUpdated_at(updatedUserInfo.getUpdated_at());
            userInfo.setCreated_at(updatedUserInfo.getCreated_at());
            userInfo.setVerified(updatedUserInfo.getVerified());

            service.updateUserInfo(userId,userInfo);

            return ResponseEntity.ok("Updated Successfully!");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error Occurred");
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
