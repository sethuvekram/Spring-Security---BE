package com.App.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Register {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;


}
