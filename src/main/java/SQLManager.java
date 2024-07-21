import local.locInfo;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class SQLManager {

    String dbUname = locInfo.dbUname;
    String dbPass = locInfo.dbPass;

    //String url = "jdbc:postgresql://localhost:5432/3005_GP";

    static Connection con = null;

    public SQLManager() {
        String dbUname = "postgres";
        String dbPass = "admin";
        String url = "jdbc:postgresql://localhost:5432/BoatCopDB";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, dbUname, dbPass);
            System.out.println("Connection status: " + con);
        } catch (Exception e) {
            System.out.println("Connection Failed : " + e);
        }
    }


    //FUNCTION LIST

    //TODO Add a method to search the DATABASE based on ID, as well as other options based on how the boat lookss


    public ResultSet getBoatWithHin(String HIN) {
        try {
            String f = String.format("SELECT * FROM boat WHERE HIN = %s", HIN);
            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;
        }
        catch (Exception e) {
            System.out.println("Problem in getBoat: " + e);
            return null;
        }
    }

    public ResultSet getOwnershipInfo(String BoatID, int OwnerID){
        try {
            String f = "SELECT * FROM ownership WHERE ";
            if(!BoatID.isEmpty()) f += String.format("boatid = '%s' AND ", BoatID);
            if(OwnerID >= 0)f += String.format("ownerid = '%s' AND ", OwnerID);

            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet getNoticeInfo(int ID, String BoatID, int OwnerID){
        try {
            String f = "SELECT * FROM notice WHERE ";
            if(ID >= 0) f += String.format("noticeid = '%s'", ID);

            else{
                if(!BoatID.isEmpty()) f += String.format("boatid = '%s' AND ", BoatID);
                if(OwnerID >= 0)f += String.format("ownerid = '%s' AND ", OwnerID);

                if (f.endsWith(" AND ")) {
                    f = f.substring(0, f.length() - 5);
                }
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    public ResultSet getOwner(String fn, String ln, String id, String contact, String Lic){
        try{
            String f = "SELECT * FROM owners WHERE ";
            if (!id.isEmpty()) f += String.format("ownerid = '%s' AND ", id);
            if (!fn.isEmpty()) f += String.format("LOWER(TRIM(firstname)) = LOWER('%s') AND ", fn);
            if (!ln.isEmpty()) f += String.format("LOWER(TRIM(lastname)) = LOWER('%s') AND ", ln);
            if (!contact.isEmpty()) f += String.format("LOWER(TRIM(contact)) = LOWER('%s') AND ", contact);
            if (!Lic.isEmpty()) f += String.format("LOWER(TRIM(license)) = LOWER('%s') AND ", Lic);

            // Remove the trailing " AND " if it exists
            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println("Problem in getOwner");
            return null;
        }
    }

    public ResultSet getBoat(String id){
        try{
            String f = String.format("SELECT * FROM boat WHERE boatid = '%s'",id);

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet getBoat(String model, String len, String type, String primColor, String detailColor, String tarpColor, String numSails, String numMasts, String desc, String add1, String add2){
        try{
            String f = "SELECT * FROM boatinfo WHERE ";
            if (!model.trim().isEmpty()) f += String.format("LOWER(TRIM(model)) LIKE LOWER('%s') AND ", model.trim());
            if (!len.trim().isEmpty()) f += String.format("LOWER(TRIM(length::text)) = LOWER('%s') AND ", len.trim());
            if (!type.trim().isEmpty()) f += String.format("LOWER(TRIM(type)) LIKE LOWER('%s') AND ", type.trim());
            if (!primColor.trim().isEmpty()) f += String.format("LOWER(TRIM(primaryColor)) = LOWER('%s') AND ", primColor.trim());
            if (!detailColor.trim().isEmpty()) f += String.format("LOWER(TRIM(detailColor)) = LOWER('%s') AND ", detailColor.trim());
            if (!tarpColor.trim().isEmpty()) f += String.format("LOWER(TRIM(tarpColor)) = LOWER('%s') AND ", tarpColor.trim());
            if (!numSails.trim().isEmpty()) f += String.format("LOWER(TRIM(numSails::text)) = LOWER('%s') AND ", numSails.trim());
            if (!numMasts.trim().isEmpty()) f += String.format("LOWER(TRIM(numMasts::text)) = LOWER('%s') AND ", numMasts.trim());
            if (!desc.trim().isEmpty()) f += String.format("LOWER(TRIM(description)) LIKE LOWER('%s') AND ", desc.trim());
            if (!add1.trim().isEmpty()) f += String.format("LOWER(TRIM(additional1)) = LOWER('%s') AND ", add1.trim());
            if (!add2.trim().isEmpty()) f += String.format("LOWER(TRIM(additional2)) = LOWER('%s') AND ", add2.trim());

            // Remove the trailing " AND " if it exists
            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;
        }
        catch(Exception e){
            System.out.println("Problem in getBoat: " + e);
            return null;
        }
    }
    public ResultSet getBoatInfo(String id){
        try {
            String f = String.format("SELECT * FROM boatinfo WHERE boatid = '%s' LIMIT 1", id);
            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            return r;
        }
        catch (Exception e){
            System.out.println("Error in getBoatInfo (priv func): " +e);
            return null;
        }
    }

    public void printResultSet(ResultSet r) {
        try {
            int colCount = r.getMetaData().getColumnCount();
            System.out.println(colCount);
            while (r.next()) {
                //System.out.println("Got Here");
                String row = "";
                for (int i = 1; i < colCount; ++i) row += r.getString(i) + " , ";
                if(row.endsWith(" , ")) row = row.substring(0, row.length() - 3);
                System.out.println(row);
            }

        } catch (Exception e) {
            System.out.println("In Print Result Set : " + e);
        }


    }


    public boolean updateBoatInfo(Information i){
        try {
            String f = String.format("UPDATE boatinfo SET model = '%s', length = '%f', type = '%s', primarycolor = '%s', detailcolor = '%s', tarpcolor = '%s', numsails = '%d', nummasts = '%d', description = '%s' WHERE '%s' = boatid"
                    ,i.getmodel(),i.getLen(),i.getType(),i.getPrimColor(),i.getDetailColor(),i.getTarpColor(),i.getNumSails(),i.getNumMasts(),i.getDesc(),i.getId());
            Statement s= con.createStatement();
            s.executeUpdate(f);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }



    //MAKE NEW DATABASE INFO

    public boolean newNotice(Notice n){
        try{
            int g = n.getGroup().ordinal() + 1;
            String f = String.format("INSERT INTO notice (boatId, ownerId, groupType, noticeType, incidentNumber, dateOfIssue) " +
                            "VALUES ('%s','%d','%s','%s','%s','%tF')",
                    n.getBoatId(),n.getOwnerId(),n.getGroup(),n.getType(),n.getIncidentNumber(),n.getDateOfIssue());
            //System.out.println(f);
            Statement s = con.createStatement();
            s.executeUpdate(f);
            //String t = String.format("%tF",n.getDateOfIssue());
            //System.out.println(t);
            return true;
        }
        catch (Exception e){
            System.out.println(e + " In newNotice (SQLManager)");
        }
        return false;
    }


    //public  boolean newInfo

    public boolean newBoat(String HIN, String name, Information I){
        try{
            //MAKE MOST RANDOM POSSIBLE PRIMARY KEY
            Random rand = new Random();
            int r = rand.nextInt(1000);
            String boatID = String.format("%s%s%s%d",HIN,name,new SimpleDateFormat("H-m-s-dd-MM-yyyy").format(new Date()),r);

            String hashID = DigestUtils.sha1Hex(boatID);
            System.out.println(boatID);
            System.out.println(hashID);


            String f = String.format("INSERT INTO boat (boatid,hin,name,photos) VALUES ('%s','%s','%s','%s');",hashID,HIN,name,"DOLATER");
//            f += String.format(" INSERT INTO boatinfo (boatid,model,length,type,primarcolor,detailcolor,tarpcolor,numsails,nummasts,description)",
//                    hashID,I.getmodel(),I.getLen(),I.getType(),I.getPrimColor(),I.get);
            f += String.format("INSERT INTO boatinfo (boatid) VALUES ('%s')",hashID);
            I.setID(hashID);
            System.out.println(I.getId());

            Statement s = con.createStatement();

            s.executeUpdate(f);


            return true;
        }
        catch (Exception e){
            System.out.println(e + " In newBoat (SQL MANANGER)");
        }
        return false;
    }

    //IF newboat function was not able to insert both a boat and boatID into the database, scrub any half instanciated data from databasse

    public boolean cleanBoatData(String ID){
        try{
            Statement s = con.createStatement();
            String f = String.format("DELETE FROM boat WHERE boatid = '%s'",ID);
            String f1 = String.format("DELETE FROM boatinfo WHERE boatid = '%s'",ID);

            try{
                s.executeUpdate(f1);
            }
            catch (Exception e){
                System.out.println(e);
            }


            try{
                s.executeUpdate(f);
            }
            catch (Exception e){
                System.out.println(e);
            }



            return true;
        }
        catch (Exception e){
            System.out.println(e + " In cleanBoatData (SQLMANAGER)");
        }
        return false;
    }



}
