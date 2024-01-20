package com.trash2cash.trash2cash.service;

import com.trash2cash.trash2cash.dto.UserDto;
import com.trash2cash.trash2cash.entity.Role;
import com.trash2cash.trash2cash.entity.UserType;
import com.trash2cash.trash2cash.repository.UserInfoRepository;
import com.trash2cash.trash2cash.entity.UserInfo;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
    
    public UserDetails loadUserByMobile(String mobilenumber) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByMobilenumber(mobilenumber);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + mobilenumber));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    //get all users
    public List<UserDto> getUsers(){
        List<UserInfo> userDto = repository.findAll();
        return modelMapper.map(userDto, new TypeToken<List<UserDto>>(){}.getType());

    }

    //get user details role
    public List<UserDto> getProfile(Long id){
        List<UserInfo> user = repository.findById(id);
        return modelMapper.map(user , new TypeToken<List<UserDto>>(){}.getType());
    }

    public String updateUserInfo(int userId, UserInfo updatedUserInfo) {
        updatedUserInfo.setId(Math.toIntExact(userId));
        updatedUserInfo.setPassword(encoder.encode(updatedUserInfo.getPassword()));
        repository.save(updatedUserInfo);
        return "User Added Successfully";
        // Handle not found case
    }

    public void deleteUser(int id){
        Optional<UserInfo> deleteUser = repository.findById(id);
        if(deleteUser.isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("User with id"+ id + "not found");
        }
    }
}
