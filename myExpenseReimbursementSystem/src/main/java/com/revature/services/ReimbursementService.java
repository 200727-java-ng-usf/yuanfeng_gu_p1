package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repository.ReimbursementRepo;

import java.util.Set;

public class ReimbursementService {

    ReimbursementRepo reimbursementRepo;

    public ReimbursementService() {
        reimbursementRepo = new ReimbursementRepo();
    }

    public Set<Reimbursement> findAllReimbursements(){

        return reimbursementRepo.findAllReimbursements();

    }

    public Set<Reimbursement> findAllReimbursementsForIndividual(int authorId){
        return  reimbursementRepo.findAllReimbursementsForIndividual(authorId);
    }


    public Set<Reimbursement> findAllPendingReimbursementsForIndividual(int authorId){
        return  reimbursementRepo.findAllPendingReimbursementsForIndividual(authorId);
    }


    public Set<Reimbursement> findAllResolvedReimbursementsForIndividual(int authorId){
        return reimbursementRepo.findAllResolvedReimbursementsForIndividual(authorId);
    }

    public void addReimbursement(Reimbursement reimbursement){
        reimbursementRepo.addReimbursement(reimbursement);
    }



}
