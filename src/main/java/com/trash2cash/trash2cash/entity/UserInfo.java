package com.trash2cash.trash2cash.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Table(name= "user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles; // user, staff, manager, admin
    private String dob;
    @Enumerated(EnumType.STRING)
    private Gender gender; //ENUM('M', 'F', 'Other'),
    private String mobilenumber;
    private String landline;
    @Enumerated(EnumType.STRING)
    private UserType usertype; // ENUM('Staff', 'User', ‘Driver’)
    @Enumerated(EnumType.STRING)
    private Status status; //ENUM('active', 'disabled'),
    private String verified;
    private String created_at;
    private String updated_at;


}
