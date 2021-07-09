package jdbc_lab1_swen301;

import java.sql.*;
import org.relique.jdbc.csv.CsvDriver;

/**
 * FetchStudents class. Based off template in: https://github.com/simoc/csvjdbc
 */
public class FetchStudents {

    private static Statement statement;

    public static ResultSet fetchAll() throws Exception {
        return statement.executeQuery("SELECT * FROM students");
    }

    public static ResultSet fetchById(int id) throws Exception {
        return statement.executeQuery("SELECT * FROM students WHERE ID = '" + id + "'");
    }

    public static ResultSet fetchByDegreeAndMajor(String degree, String major) throws Exception {
        return statement.executeQuery("SELECT * FROM students WHERE DEGREE = '" + degree
                                      + "' AND MAJOR = '" + major + "'");
    }

    public static void printOutput() throws Exception {
        System.out.println("\n---------x---------------------------------------");
        System.out.println("calling fetchAll... ");
        CsvDriver.writeToCsv(fetchAll(), System.out, true);
        System.out.println("\n------------------------------------------------");
        System.out.println("calling fetchById... ");
        CsvDriver.writeToCsv(fetchById(4), System.out, true);
        System.out.println("\n------------------------------------------------");
        System.out.println("calling fetchByDegreeAndMajor... ");
        CsvDriver.writeToCsv(fetchByDegreeAndMajor("BSc", "CS"), System.out, true);
    }

    public static void main(String[] args) throws Exception {
        // Load the driver from the csvjdbc jar
        // the JVM need to load the Class file in order to use the CsvDriver class
        // so load it from the csvjdbc jar manually
        // Class.forName("org.relique.jdbc.csv.CsvDriver");

        // Create a connection to directory given as first command line
        // parameter. Driver properties are passed in URL format
        // (or alternatively in a java.utils.Properties object).
        //
        // A single connection is thread-safe for use by several threads.
        String url = "jdbc:relique:csv:db";
        Connection connection = DriverManager.getConnection(url);// it represent a JDBC
                                                                 // connection

        // below is for accessing the database

        // Create a Statement object to execute the query with.
        // A Statement is not thread-safe.
        statement = connection.createStatement();

        // ResultSet results = statement.executeQuery("SELECT * FROM students");

        // Dump out the results to a CSV file with the same format
        // using CsvJdbc helper function
        // boolean append = true;
        // CsvDriver.writeToCsv(results, System.out, append);

        printOutput();

        // close the connection and clean up
        connection.close();
    }
}