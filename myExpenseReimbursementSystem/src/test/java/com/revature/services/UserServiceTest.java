//package com.revature.services;
//
//import com.revature.exceptions.InvalidRequestException;
//import com.revature.models.User;
//import com.revature.repository.UserRepo;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import static org.junit.Assert.*;
//
//public class UserServiceTest {
//    UserService userService;
//    User testUser;
//    private UserRepo mockRepo;
//    private UserService mockService;
//
//
//
//
//    @Before
//    public void setUp() throws Exception {
//
//        testUser = new User();
//        mockService = Mockito.mock(UserService.class);
//        mockRepo = Mockito.mock(UserRepo.class);
//        userService = new UserService();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//        testUser = null;
//        mockService = null;
//        userService = null;
//
//    }
//
//    @Test
//    public void authenticate() throws InvalidRequestException {
//
//        assertEquals("Gu",userService.authenticate("gu8","8888").getLastName());
//    }
//
//    @Test
//    public void findUserByUsername() throws InvalidRequestException {
//
//        assertEquals("Gu",userService.findUserByUsername("gu8").getLastName());
//
//
//    }
//
//    @Test
//    public void findAllUser() {
//      assertTrue(userService.findAllUser().stream().findFirst().isPresent());
//        assertEquals("shen","shen",userService.findAllUser().stream().findFirst().get().getLastName());
//    }
//
//    @Test
//    public void findUserById() {
//
//        assertEquals("Gu",userService.findUserById(1).getLastName());
//        assertEquals("shen",userService.findUserById(4).getLastName());
//    }
//
//    @Test
//    public void findUserByRole() throws InvalidRequestException {
//        assertFalse(userService.findUserByRole("employee").isEmpty());
//        assertEquals("shen",userService.findUserByRole("employee").stream().findFirst().get().getLastName());
//    }
//
//    @Test
//    public void testAddUser() {
//        mockService.addUser(testUser);
//
//    }
//
//    @Test
//    public void deleteUser() {
//        mockService.deleteUser("testUser");
//    }
//
//    @Test
//    public void editUser() {
//
//        mockService.editUser("aaa","bbb","ccc@dsds",2,2);
//    }
//}