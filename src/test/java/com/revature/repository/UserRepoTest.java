package com.revature.repository;

import com.revature.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserRepoTest {

    UserRepo testRepo;
    User testUser;
    Set<User> userSet;
    User testUser2;

    @Before
    public void setUp() throws Exception {
        testRepo = new UserRepo();
        testUser = new User();
        userSet = new HashSet<>();
        testUser2= new User();

    }

    @After
    public void tearDown() throws Exception {
        testRepo = null;
        testUser2 =null;
    }
//
//    @Test
//    public void findUserByCredentials() {
//
//        testUser = testRepo.findUserByCredentials("gu8", "8888").get();
//        assertEquals(2,testUser.getRole());
//    }
//
//    @Test
//    public void findUserByUsername() {
//        testUser = testRepo.findUserByUsername("effie").get();
//        assertEquals(2,testUser.getRole());
//    }
//
//    @Test
//    public void findUserRoleId() {
//        userSet = testRepo.findUserRoleId(2);
//        for(User user:userSet){
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void findAllUsers() {
//        userSet = testRepo.findAllUsers();
//        for(User user:userSet){
//            System.out.println(user);
//        }
//
//    }
//
//    @Test
//    public void save() {
//        testRepo.save(new User("tony","1111","tony","parker","tony.parker@hotmail.com",1));
//    }
//
//    @Test
//    public void findRole() {
//        userSet = testRepo.findRole("employee");
//        for(User user:userSet){
//            System.out.println(user);
//        }
//    }
//
    @Test
    public void deleteUser() {
        testRepo.deleteUser("linda7");
    }
//
//    @Test
//    public void editUser() {
//
//
//        testRepo.editUser("testUser","password","testuser@gmail.com",2,4);
//
//
//
//    }
}