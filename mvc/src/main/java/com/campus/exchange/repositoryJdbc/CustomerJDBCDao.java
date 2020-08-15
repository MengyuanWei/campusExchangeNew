package com.campus.exchange.repositoryJdbc;

import com.campus.exchange.modelJdbc.CustomerJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerJDBCDao {
    static final String DBURL = "repositoryJdbc:postgresql://localhost:5430/projectdb";
    static final String USER = "alex";
    static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());

    // CRUD
    public List<CustomerJDBC> getCustomers() {
        List<CustomerJDBC> customerJDBCS = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Open a connection
            logger.debug("open connection...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("create statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");

                //Fill the object
                CustomerJDBC customerJDBC = new CustomerJDBC();
                customerJDBC.setName(name);
                customerJDBC.setPassword(password);
                customerJDBC.setEmail(email);
                customerJDBC.setPhoneNumber(phoneNumber);
                customerJDBCS.add(customerJDBC);
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
        return customerJDBCS;
    }


}
