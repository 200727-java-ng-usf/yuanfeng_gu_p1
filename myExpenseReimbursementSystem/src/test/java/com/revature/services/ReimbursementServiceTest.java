//package com.revature.services;
//
//import com.revature.models.Reimbursement;
//import com.revature.repository.ReimbursementRepo;
//import com.revature.repository.UserRepo;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//public class ReimbursementServiceTest {
//
//    ReimbursementRepo testRepo;
//    Set<Reimbursement> reimbursementSet;
//    Reimbursement reimbursement;
//    ReimbursementService reimbursementService;
//    private ReimbursementService mockService;
//
//    @Before
//    public void setUp() throws Exception {
//        testRepo = new ReimbursementRepo();
//         reimbursement = new Reimbursement();
//         reimbursementService = new ReimbursementService();
//        mockService = Mockito.mock(ReimbursementService.class);
//
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        testRepo = null;
//        reimbursement = null;
//        mockService = null;
//        reimbursementService = null;
//    }
//
//
//
//    @Test
//    public void findAllReimbursementsForIndividual() {
//        assertTrue(reimbursementService.findAllReimbursementsForIndividual(1).stream().findFirst().isPresent());
//        assertEquals(420.00,420.0,reimbursementService.findAllReimbursementsForIndividual(1).stream().findFirst().get().getAmount());
//    }
//
//    @Test
//    public void findAllPendingReimbursementsForIndividual() {
//        //assertTrue(reimbursementService.findAllPendingReimbursementsForIndividual(1).stream().findFirst().isPresent());
//        //assertEquals(300.0,300.0,reimbursementService.findAllPendingReimbursementsForIndividual(1).stream().findFirst().get().getAmount());
//
//    }
//
//    @Test
//    public void findAllResolvedReimbursementsForIndividual() {
//        assertTrue(reimbursementService.findAllResolvedReimbursementsForIndividual(1).stream().findFirst().isPresent());
//        assertEquals(420.00,420.0,reimbursementService.findAllResolvedReimbursementsForIndividual(1).stream().findFirst().get().getAmount());
//
//
//    }
//
//    @Test
//    public void addReimbursement() {
//        mockService.addReimbursement(reimbursement);
//    }
//
//    @Test
//    public void getPendingReimbursements() {
//
//        assertFalse(reimbursementService.getPendingReimbursements().isEmpty());
//        assertEquals(300.0,300.0,reimbursementService.getPendingReimbursements().stream().findFirst().get().getAmount());
//    }
//
//    @Test
//    public void getResolvedReimbursements() {
//        assertFalse(reimbursementService.getResolvedReimbursements().isEmpty());
//        assertEquals(420.00,420.0,reimbursementService.getResolvedReimbursements().stream().findFirst().get().getAmount());
//    }
//
//    @Test
//    public void testFindAllReimbursements() {
//        assertTrue(reimbursementService.findAllReimbursements().stream().findFirst().isPresent());
//        assertEquals(420.00,420.0,reimbursementService.findAllReimbursements().stream().findFirst().get().getAmount());
//
//
//    }
//
//    @Test
//    public void testAddReimbursement() {
//        mockService.addReimbursement(reimbursement);
//    }
//
//
//    @Test
//    public void decisionOfReimbursement() {
//        mockService.decisionOfReimbursement(2,1,2);
//    }
//}