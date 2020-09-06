package com.revature.repository;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ReimbursementRepo {

    public Set<Reimbursement> findAllReimbursements() {

        Set<Reimbursement> reimbursements = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            reimbursements = mapResultSet(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reimbursements;

    }

    public Optional<Reimbursement> findReimbursementByReimbId(int id) {

        Optional<Reimbursement> _reim = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er WHERE reimb_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            _reim = mapResultSet(rs).stream().findFirst();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _reim;
    }


    public Set<Reimbursement> getPendingReimbursements() {
        Set<Reimbursement>  reimbursements = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er WHERE reimb_status_id = 2";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            reimbursements = mapResultSet(rs);


        } catch (
                SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    public Set<Reimbursement> getResolvedReimbursements() {
        Set<Reimbursement>  reimbursements = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er WHERE reimb_status_id = 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            reimbursements = mapResultSet(rs);


        } catch (
                SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;
    }


    public Set<Reimbursement> findAllReimbursementsForIndividual(int authorId) {

        Set<Reimbursement>  reimbursements = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er WHERE author_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, authorId);


            ResultSet rs = pstmt.executeQuery();
            reimbursements = mapResultSet(rs);


        } catch (
                SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;

    }

    public Set<Reimbursement> findAllPendingReimbursementsForIndividual(int authorId) {
        Set<Reimbursement>  reimbursements = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er WHERE author_id = ? and reimb_status_id = 2 ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, authorId);


            ResultSet rs = pstmt.executeQuery();
            reimbursements = mapResultSet(rs);


        } catch (
                SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;

    }

    public Set<Reimbursement> findAllResolvedReimbursementsForIndividual(int authorId) {
        Set<Reimbursement>  reimbursements = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_reimbursements er WHERE author_id = ? and reimb_status_id < 2 ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, authorId);


            ResultSet rs = pstmt.executeQuery();
            reimbursements = mapResultSet(rs);


        } catch (
                SQLException sqle) {
            sqle.printStackTrace();
        }

        return reimbursements;

    }



    public void addReimbursement(Reimbursement reimbursement) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO  \"reimbursementSys\".ers_reimbursements(amount, submitted, description, author_id, reimb_status_id, reimb_tpye_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"reimb_id"});
            pstmt.setDouble(1, reimbursement.getAmount());
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(3, reimbursement.getDescription());
            pstmt.setInt(4, reimbursement.getAuthorId());
            pstmt.setInt(5, reimbursement.getStatusId());
            pstmt.setInt(6, reimbursement.getTypeId());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                reimbursement.setReimbId(rs.getInt(1));

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }


    public void decisionOfReimbursement(int resolverId,int statusId,int reimbId ) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE \"reimbursementSys\".ers_reimbursements SET resolved = ?,resolver_id = ?,reimb_status_id = ? WHERE reimb_id  = ? ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            psmt.setInt(2, resolverId);
            psmt.setInt(3, statusId);
            psmt.setInt(4, reimbId );


            psmt.executeUpdate();

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }


    }









    private Set<Reimbursement> mapResultSet(ResultSet rs) throws SQLException {

        Set<Reimbursement> reimbursements = new HashSet<>();

        while (rs.next()) {
            Reimbursement temp = new Reimbursement();
            temp.setReimbId(rs.getInt("reimb_id"));
            temp.setAmount(rs.getDouble("amount"));
            temp.setDateSubmitted(rs.getTimestamp("submitted"));
            temp.setResolvedDate(rs.getTimestamp("resolved"));
            temp.setDescription(rs.getString("description"));
            temp.setAuthorId(rs.getInt("author_id"));
            temp.setStatusId(rs.getInt("reimb_status_id"));
            temp.setTypeId(rs.getInt("reimb_tpye_id"));

            reimbursements.add(temp);
        }

        return reimbursements;

    }
}
