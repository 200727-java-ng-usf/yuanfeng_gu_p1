package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repository.ReimbursementRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ReimbursementServiceTest {

    ReimbursementRepo testRepo;
    Set<Reimbursement> reimbursementSet;
    Reimbursement reimbursement;
    ReimbursementService reimbursementService;

    @Before
    public void setUp() throws Exception {
        testRepo = new ReimbursementRepo();
         reimbursement = new Reimbursement();
         reimbursementService = new ReimbursementService();

    }

    @After
    public void tearDown() throws Exception {
        testRepo = null;
        reimbursement = null;
    }

    @Test
    public void findAllReimbursements() {

//        reimbursementSet = reimbursementService.findAllReimbursements();
//        for(Reimbursement r:reimbursementSet){
//            System.out.println(r);
//        }


    }

    @Test
    public void findAllReimbursementsForIndividual() {
    }

    @Test
    public void findAllPendingReimbursementsForIndividual() {
    }

    @Test
    public void findAllResolvedReimbursementsForIndividual() {
    }

    @Test
    public void addReimbursement() {
    }

    @Test
    public void getPendingReimbursements() {
    }

    @Test
    public void getResolvedReimbursements() {
    }
}