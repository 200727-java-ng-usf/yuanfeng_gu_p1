package com.revature.services;

import com.revature.models.User;
import com.revature.repository.ReimbursementRepo;
import com.revature.repository.UserRepo;

import com.revature.exceptions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public User findUserByUsername(String username) throws AuthenticationException, InvalidRequestException {
        if (username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Invalid username provide ! ");
        }

        User targetUser = userRepo.findUserByUsername(username).orElseThrow(AuthenticationException::new);

        return targetUser;
    }

    public Set<User> findAllUser(){

        return  userRepo.findAllUsers();
    }



    public Set<User> findUserByRole(String role) throws InvalidRequestException {
        if (role == null || role.trim().equals("")) {
            throw new InvalidRequestException("Invalid role provide ! ");
        }

        Set<User> targetUsers = new HashSet<>();
        targetUsers = userRepo.findRole(role);
        return targetUsers;
    }

    public void addUser(User newUser) {
        userRepo.save(newUser);
    }

    public void deleteUser(String username){
        userRepo.deleteUser(username);
    }

    public void editUser(String username,String password,String email,int role,int id){
        userRepo.editUser(username,password,email, role, id);
    }
}
