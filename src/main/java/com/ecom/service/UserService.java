package com.ecom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.UserDtls;

public interface UserService {

    UserDtls saveUser(UserDtls user);

    UserDtls getUserByEmail(String email);

    List<UserDtls> getUsers(String role);

    Boolean updateAccountStatus(Integer id, Boolean status);

    void increaseFailedAttempt(UserDtls user);

    void userAccountLock(UserDtls user);

    boolean unlockAccountTimeExpired(UserDtls user);

    void resetAttempt(int userId);

    void updateUserResetToken(String email, String resetToken);

    UserDtls getUserByToken(String token);

    UserDtls updateUser(UserDtls user);

    UserDtls updateUserProfile(UserDtls user, MultipartFile img);

    UserDtls saveAdmin(UserDtls user);

    Boolean existsEmail(String email);

    void sendVerificationEmail(UserDtls user);
}
