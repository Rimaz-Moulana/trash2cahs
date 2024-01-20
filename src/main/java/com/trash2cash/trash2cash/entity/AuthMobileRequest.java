package com.trash2cash.trash2cash.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthMobileRequest {
    private String mobilenumber;
    private String password;
}
