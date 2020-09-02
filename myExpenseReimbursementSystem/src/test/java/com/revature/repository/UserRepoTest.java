package com.revature.repository;

import com.revature.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepoTest {

    UserRepo testRepo;
    User testUser;

    @Before
    public void setUp() throws Exception {
        testRepo = new UserRepo();
        testUser = new User();

    }

    @After
    public void tearDown() throws Exception {
        testRepo = null;
    }

    @Test
    public void findUserByCredentials() {

        testUser = testRepo.findUserByCredentials("gu8", "8888").get();
        assertEquals(2,testUser.getRole());
    }

    @Test
    public void findUserByUsername() {
    }

    @Test
    public void findUserRoleId() {
    }

    @Test
    public void findAllUsers() {
    }

    @Test
    public void save() {
    }

    @Test
    public void findRole() {
    }

    @Test
    public void deleteUser() {
    }
}