package com.ecom.controller;

import com.ecom.service.UserService;
import com.ecom.model.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token) {
        UserDtls user = userService.getUserByToken(token);
        if (user == null) {
            return "Token inválido o expirado.";
        }

        user.setAccountVerified(true);
        userService.updateUser(user);
        return "Cuenta verificada con éxito.";
    }
}

