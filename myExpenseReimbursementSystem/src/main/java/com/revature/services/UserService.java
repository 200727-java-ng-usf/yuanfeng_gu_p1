package com.revature.services;

import com.revature.models.User;
import com.revature.repository.ReimbursementRepo;
import com.revature.repository.UserRepo;

import com.revature.exceptions.*;

import java.util.Optional;

public class UserService {

    private UserRepo userRepo;
    private ReimbursementRepo reimbursementRepo;

    public UserService() {

        userRepo = new UserRepo();
        reimbursementRepo = new ReimbursementRepo();
    }

    public User authenticate(String username, String password) throws AuthenticationException, InvalidRequestException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provide ! ");
        }

        User authenticateUser = userRepo.findUserByCredentials(username, password).orElseThrow(AuthenticationException::new);

        return authenticateUser;  // return authenticateUser
    }

    public User findUserByUsername(String username)throws AuthenticationException, InvalidRequestException {
        if (username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Invalid username provide ! ");
        }

        User targetUser = userRepo.findUserByUsername(username).orElseThrow(AuthenticationException::new);

        return  targetUser;
    }

    public void addUser(User newUser){
        userRepo.save(newUser);
    }
}
