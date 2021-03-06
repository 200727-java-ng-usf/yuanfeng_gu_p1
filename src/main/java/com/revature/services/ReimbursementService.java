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

    public Set<Reimbursement> getPendingReimbursements(){
        return reimbursementRepo.getPendingReimbursements();
    }

    public Set<Reimbursement> getResolvedReimbursements(){
        return reimbursementRepo.getResolvedReimbursements();
    }

    public void decisionOfReimbursement(int resolverId,int statusId,int reimbId ) {
        reimbursementRepo.decisionOfReimbursement(resolverId,statusId,reimbId);
    }

    public void deleteReimbursement(int authorId){
        reimbursementRepo.deleteReimbursement(authorId);
    }

}
