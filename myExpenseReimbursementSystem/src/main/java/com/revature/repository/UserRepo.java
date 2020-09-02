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


    public Optional<User> findUserByUsername(String username){
        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from ers_users eu WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    public Optional<User> findUserRoleId(Integer roleId){
        Optional<User> _user = Optional.empty();


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from ers_users eu WHERE user_role_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,roleId);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }


    public Set<User> findAllUsers() {

        Set<User> user = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select  * from ers_users eu";

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

            String sql = "INSERT INTO ers_users eu (username, password, first_name, lastname, email, user_role_id) " +
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

    public  Optional<User> findRole(String role) {

        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM ers_users "
                    + "LEFT JOIN ers_users_roles ON (ers_users.user_role_id = ers_users_roles.role_id)"
                    + "WHERE ers_users_roles.role_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, role);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }


    public void deleteUser(String username){
        boolean success = false;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "delete from ers_users eu where username = ? ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.execute();

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



