package com.models;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class Developer {
    //first, create all of the necessary attributes that pertain to the Class and encapsulate them.
    private String employeeFirstName;

    private String employeeLastName;

    private String employeeNumber;

    private LocalDate birthday;







    //second, build a constructor for the class, so that the class may develop specific instances from
    // the constructor
    public Developer(String employeeNumber, String firstName, String lastName, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmployeeNumber(employeeNumber);
        setBirthday(birthday);
    }
    //sometimes, a constructor needs to have more parameters passed to it. In case of such an event, we build a
    // second constructor that takes more parameters from the input, thus overriding the original constructor so as
    // to accommodate the new instance of the class.


    //third, generate getters and setters, which allow new instances to be sent to the database, and allows existing instances
    // to be retrieved from the database.
    public String getEmployeeNumber() {

        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setFirstName(String firstName) {
        this.employeeFirstName = firstName;
    }
    public void setLastName(String lastName) {
        this.employeeLastName = lastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {

        int age = Period.between(birthday, LocalDate.now()).getYears();

        if (age>=16){
            this.birthday=birthday;}

            else
                throw new IllegalArgumentException("Employees must be over the age of 16 to work at VisionWorks.");

        this.birthday = birthday;


    }

    // fourth, create a method that allows instances of the above class to be entered into the database.
    public void insertIntoDB() throws SQLException{

        //create a connection instance
        Connection conn = null;

        //create a prepared statement instance
        PreparedStatement preparedStatement = null;

        //attempt to establish a connection
        try {

            //populate the connection instance to the desired database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/visionworksframeinventory?verifyServerCertificate=false&useSSL=true", "root", "VisionWorks!");

            //create a valid sql statement template that will allow the instance and place it in a string
            // variable called "sql"

            String sql = "INSERT INTO developer (employeeNumber, employeeFirstName, employeeLastName, birthday)" +
                    "VALUES (?,?,?,?)";

            //
            preparedStatement = conn.prepareStatement(sql);

            //take the instance of the birthday and place it in the date.valueOf format
            //place the instance in the date.valueOf format in the Date variable known as db
            Date db = Date.valueOf((birthday));

            //take the prepared statement and place them in the appropriate order/columns,
            // aka parameterIndex
            preparedStatement.setInt(1, Integer.parseInt(employeeNumber));
            preparedStatement.setString(2,employeeFirstName);
            preparedStatement.setString(3, employeeLastName);
            preparedStatement.setDate(4, db);

            //update the database by adding this particular update.
            preparedStatement.executeUpdate();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            //close the prepared statement
            if (preparedStatement != null)
            preparedStatement.close();

            //close the connection
            if (conn != null){
                conn.close();
            }
        }
    }

    public void updateDB() throws SQLException {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/visionworksframeinventory?verifyServerCertificate=false&useSSL=true", "root", "VisionWorks!");
            String sql = "UPDATE developer SET employeeNumber = ?, employeeFirstName = ?, employeeLastName = ?, birthday = ? WHERE employeeNumber = ?";

            preparedStatement = conn.prepareStatement(sql);

            Date db = Date.valueOf(birthday);
            preparedStatement.setString(1, employeeNumber);
            preparedStatement.setString(2, employeeFirstName);
            preparedStatement.setString(3, employeeLastName);
            preparedStatement.setDate(4, db);
            preparedStatement.setString(5, this.employeeNumber);

            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        finally {
            if (conn != null) {
                conn.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }



    }





}
