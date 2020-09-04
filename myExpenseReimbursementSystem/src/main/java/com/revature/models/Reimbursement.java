package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {

    //fields

    private int reimbId;
    private double amount;
    private String description;
    private int authorId;
    private int resolverId;
    private int statusId;
    private int typeId;
    private Timestamp dateSubmitted;
    private Timestamp resolvedDate;

    //constructor

    public Reimbursement() {
    }

    public Reimbursement(double amount,String description,
                         int authorId, int statusId, int typeId) {

        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.statusId = statusId;
        this.typeId = typeId;

    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Timestamp getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Timestamp dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Timestamp getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Timestamp resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;
        Reimbursement that = (Reimbursement) o;
        return getReimbId() == that.getReimbId() &&
                Double.compare(that.getAmount(), getAmount()) == 0 &&
                getAuthorId() == that.getAuthorId() &&
                getResolverId() == that.getResolverId() &&
                getStatusId() == that.getStatusId() &&
                getTypeId() == that.getTypeId() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDateSubmitted(), that.getDateSubmitted()) &&
                Objects.equals(getResolvedDate(), that.getResolvedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimbId(), getAmount(), getDescription(), getAuthorId(), getResolverId(), getStatusId(), getTypeId(), getDateSubmitted(), getResolvedDate());
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                ", dateSubmitted=" + dateSubmitted +
                ", resolvedDate=" + resolvedDate +
                '}';
    }
}
