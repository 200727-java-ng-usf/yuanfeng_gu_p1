package com.revature.repository;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;



import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepo {
    public UserRepo() {
    }

    public Optional<User>findUserByCredentials(String username, String password) {

        Optional<User> _user = Optional.empty();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select  * from \"reimbursementSys\".ers_users WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }


    //tested
    public Optional<User> findUserByUsername(String username){
        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_users eu WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }


    //tested
    public Set<User> findUserRoleId(Integer roleId){
        Set<User> users = new HashSet<>();


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_users eu WHERE user_role_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,roleId);

            ResultSet rs = pstmt.executeQuery();
            users = mapResultSet(rs);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return users;
    }


    public Set<User> findAllUsers() {

        Set<User> user = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from \"reimbursementSys\".ers_users eu";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            user = mapResultSet(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;

    }

    public void save(User newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO  \"reimbursementSys\".ers_users (username, password, first_name, lastname, email, user_role_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"ers_user_id"});
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getEmail());
            pstmt.setInt(6, newUser.getRole());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newUser.setId(rs.getInt(1));

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    public  Set<User> findRole(String role) {

        Set<User> user = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from \"reimbursementSys\".ers_users eu \n " +
                    "left join \"reimbursementSys\".ers_users_roles eur on eu.user_role_id = eur.role_id \n " +
                    "where eur.role_name = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, role);

            ResultSet rs = pstmt.executeQuery();
            user = mapResultSet(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }


    public void deleteUser(String username){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "delete from \"reimbursementSys\".ers_users eu where username = ? ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.execute();

        }catch (SQLException sqle) {
                sqle.printStackTrace();
            }
    }


    public void editUser(String username,String password,String email,int role,int id){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE \"reimbursementSys\".ers_users SET username = ?,password = ?,email = ?,user_role_id = ? WHERE  ers_user_id = ? ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.setString(2, password);
            psmt.setString(3, email);
            psmt.setInt(4, role);
            psmt.setInt(5, id);

            psmt.executeUpdate();

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }


    }






    private Set<User> mapResultSet(ResultSet rs) throws SQLException {

        Set<User> users = new HashSet<>();

        while (rs.next()) {
            User temp = new User();
            temp.setId(rs.getInt("ers_user_id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("lastname"));
            temp.setUsername(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setEmail(rs.getString("email"));
            temp.setRole(rs.getInt("user_role_id"));

            users.add(temp);
        }

        return users;

    }
}



