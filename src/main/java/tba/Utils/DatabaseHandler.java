package tba.Utils;

import java.io.*;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseHandler
{

    private static DatabaseHandler instance;

    private Connection connection;
    
    private DatabaseHandler()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/java_tba", "root", "root");
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public static DatabaseHandler getInstance()
    {
        if (instance == null) {
            instance = new DatabaseHandler();
        }

        return instance;
    }

    public static boolean execute(String sql)
    {
        try {
            Statement statement = getInstance().connection.createStatement();
            return statement.execute(sql);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return false;
        }
    }

    public static ResultSet query(String sql)
    {
        try {
            Statement statement = getInstance().connection.createStatement();
            return statement.executeQuery(sql);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static void runScript(String filename) throws SQLException, FileNotFoundException
    {
        ScriptRunner scriptRunner = new ScriptRunner(getInstance().connection);
        Reader reader = new BufferedReader( new FileReader(filename) );
        scriptRunner.runScript(reader);
    }

    public Connection getConnection()
    {
        return connection;
    }

}
