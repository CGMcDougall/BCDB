import local.locInfo;

import java.sql.*;

public class SQLManager {

    String dbUname = locInfo.dbUname;
    String dbPass = locInfo.dbPass;

    static Connection con = null;

    public SQLManager() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(locInfo.dbURL, locInfo.dbUname, locInfo.dbPass);
        } catch (Exception e) {
            System.out.println("Connection Failed : " + e);
        }
    }


    //FUNCTION LIST

    //TODO Add a method to search the DATABASE based on ID, as well as other options based on how the boat lookss


}
