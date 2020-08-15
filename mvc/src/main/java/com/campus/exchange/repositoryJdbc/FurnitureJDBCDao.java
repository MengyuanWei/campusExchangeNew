package com.campus.exchange.repositoryJdbc;

import com.campus.exchange.modelJdbc.FurnitureJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FurnitureJDBCDao {
    static final String DBURL = "repositoryJdbc:postgresql://localhost:5430/projectdb";
    static final String USER = "alex";
    static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<FurnitureJDBC> getFurnitures() {
        List<FurnitureJDBC> furnitureJDBCS = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
            stmt = conn.createStatement();  //STEP 3: Execute a query

            String sql = "SELECT * FROM furnitures";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String description = rs.getString("description");
                long customers_id = rs.getLong("customers_id");
                BigDecimal price = rs.getBigDecimal("price");

                //Fill the object
                FurnitureJDBC furnitureJDBC = new FurnitureJDBC(id, name, category,
                        description,customers_id, price);
                furnitureJDBCS.add(furnitureJDBC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return furnitureJDBCS;
    }

    public boolean save(FurnitureJDBC furnitureJDBC) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
            stmt = conn.createStatement();  //STEP 3: Execute a query

            String sql = "INSERT INTO furnitures (name,category,description,customers_id,price) " +
                    "VALUES (?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, furnitureJDBC.getName());
            ps.setString(2, furnitureJDBC.getCategory());
            ps.setString(3, furnitureJDBC.getDescription());
            ps.setLong(4, furnitureJDBC.getCustomerId());
            ps.setBigDecimal(5, furnitureJDBC.getPrice());

            int rowInserted = ps.executeUpdate();
            if(rowInserted > 0){
                logger.debug("A new furnitureJDBC record was inserted successfully");
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
//                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if(ps != null) ps.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(String oldFurnitureName, FurnitureJDBC furnitureJDBC){
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
            stmt = conn.createStatement();  //STEP 3: Execute a query

            String sql = "UPDATE furnitures SET name=?, category=?, description=?, " +
                    "customers_id=?, price=? WHERE name=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, furnitureJDBC.getName());
            ps.setString(2, furnitureJDBC.getCategory());
            ps.setString(3, furnitureJDBC.getDescription());
            ps.setLong(4, furnitureJDBC.getCustomerId());
            ps.setBigDecimal(5, furnitureJDBC.getPrice());
            ps.setString(6, oldFurnitureName);

            int rowUpdated = ps.executeUpdate();
            if(rowUpdated > 0){
                logger.debug("An existing furnitureJDBC record was updated successfully");
                return true;
            }
        } catch(SQLException e){
//            logger.error("Delete unsucessful!");
            e.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if(ps != null) ps.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(String name) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
            stmt = conn.createStatement();  //STEP 3: Execute a query

            String sql= "DELETE FROM furnitures WHERE name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0){
                logger.debug("A furniture was deleted successfully!");
                return true;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if(ps!= null) ps.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

//    public static void main(String[] args){
//        FurnitureJDBCDao furnitureDao = new FurnitureJDBCDao();
//        List<FurnitureJDBC> furnitures = furnitureDao.getFurnitures();
//
//        for(FurnitureJDBC furniture: furnitures){
//            System.out.println(furniture.getName());
//        }
//    }

}

