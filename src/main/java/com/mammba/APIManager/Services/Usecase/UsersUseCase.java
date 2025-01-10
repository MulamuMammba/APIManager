package com.mammba.APIManager.Services.Usecase;

import com.mammba.APIManager.Model.Users;
import com.mammba.APIManager.Repository.UsersTable;
import com.mammba.APIManager.Services.PasswordEncrypt;

public class UsersUseCase {
    static PasswordEncrypt security = new PasswordEncrypt();

    public static void createUser(String email, String password, String name, String surname) {

        String securePassword = security.EncryptPassword(password);

        Users user = new Users(email, securePassword, name, surname, null, null);

        UsersTable.createUser(user);

    }

    public static void RemoveUser(String email) {
        UsersTable.deleteUser(email);
        ApiUseCase.RemoveAllUserApi(email);
    }

    public static boolean UserExists(String email) {
        Users user = UsersTable.getUserByEmail(email);

        return !(user.getName() == null);
    }
}
