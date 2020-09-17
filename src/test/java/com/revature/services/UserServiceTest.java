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
//
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
//        testUser = new User("test","test","test","test","test@hotmail.com",1);
//        userService.addUser(testUser);
//        userService.editUser("test","bbb","ccc@dsds",2,testUser.getId());
//        userService.deleteUser("test");
//
//    }


//    @Test
//    public void editUser() {
//
//        userService.editUser("test","bbb","ccc@dsds",2,testUser.getId());
//    }
//
//
//    @Test
//    public void deleteUser() {
//        userService.deleteUser("test");
//    }

//}