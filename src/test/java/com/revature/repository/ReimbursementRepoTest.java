//package com.revature.repository;
//
//import com.revature.models.Reimbursement;
//import com.revature.models.User;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Timestamp;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//public class ReimbursementRepoTest {
//
//    ReimbursementRepo testRepo;
//    Reimbursement testReimbursement;
//    Set<Reimbursement> reimbursementsSet;
//
//    @Before
//    public void setUp() throws Exception {
//        testRepo = new ReimbursementRepo();
//        testReimbursement = new Reimbursement();
//        reimbursementsSet = new HashSet<>();
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//        testReimbursement = null;
//        testRepo =null;
//        reimbursementsSet = null;
//
//    }

//    @Test
//    public void findAllReimbursements() {
//       reimbursementsSet = testRepo.findAllReimbursements();
//
//       for(Reimbursement r:reimbursementsSet){
//           System.out.println(r);
//       }
//    }
//
//    @Test
//    public void findReimbursementByReimbId() {
//
//        testReimbursement = testRepo.findReimbursementByReimbId(1).get();
//        System.out.println(testReimbursement.getAmount());
//    }
//
//    @Test
//    public void getPendingReimbursements() {
//        reimbursementsSet = testRepo.getPendingReimbursements();
//
//        for(Reimbursement r:reimbursementsSet){
//            System.out.println(r);
//        }
//
//    }
//
//    @Test
//    public void getResolvedReimbursements() {
//    }
//
//    @Test
//    public void findAllReimbursementsForIndividual() {
//
//        reimbursementsSet = testRepo.findAllReimbursementsForIndividual(1);
//        for(Reimbursement r:reimbursementsSet){
//            System.out.println(r);
//        }
//
//    }
//
//    @Test
//    public void findAllPendingReimbursementsForIndividual() {
//        reimbursementsSet = testRepo.findAllPendingReimbursementsForIndividual(1);
//        for(Reimbursement r:reimbursementsSet){
//            System.out.println(r);
//        }
//    }
//
//    @Test
//    public void findAllResolvedReimbursementsForIndividual() {
//    }
//
//
//    @Test
//    public void addReimbursement() {
//
//        testRepo.addReimbursement(new Reimbursement(500.00,"travel around",4,2,2));
//        assertEquals(500.00,500.00,testReimbursement.getAmount());
//    }
//
//    @Test
//    public void approveReimbursement() {
//        testRepo.approveReimbursement(4,1,1);
//        testReimbursement = testRepo.findReimbursementByReimbId(1).get();
//        System.out.println(testReimbursement);
//    }
//}