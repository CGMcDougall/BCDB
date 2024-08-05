import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO MIDDLE MAN IS THE LAYER BETWEEN MAIN AND SQLMANAGER, USEFULL FOR INPUT AND INSTANTIATING OBJECTS
public class MiddleMan {

    public static void getOwnerHist(SQLManager sql, Scanner in){
            Owner o = getOwnerInfo(sql,in);

            if(o == null) return;
            int id = o.getId();

            try{

                ResultSet r = sql.getOwnershipInfo("",id);



                if(r.isBeforeFirst()){
                    Ownership ow = new Ownership(r);
                    System.out.println(ow.getInfoString());
                }


                r = sql.getNoticeInfo(-1,"",id);

                if(r.isBeforeFirst()){
                    Notice n = new Notice(r);
                    System.out.println(n.getString());
                }


            }
            catch (Exception e){
                System.out.println(e);
            }
    }


    //Returns an array list of notices when given a reference to SQL manager and a boatID or Owner ID (OR both)
    public static ArrayList<Notice> getNoticeHist(SQLManager sql, String BID, String OID){
        try{

            int oid = Integer.valueOf(OID);
            //int bid = Integer.valueOf(BID);

            ResultSet r  = sql.getNoticeInfo(-1,BID,oid);

            ArrayList<Notice> l = new ArrayList<>();

            while(r.next()){
                //System.out.println(String.format("Option '%s'",count));
                Notice n = new Notice(r);
                l.add(n);
            }


            return l;

        }
        catch (Exception e){
            System.out.println(e + " in getOwnerNoticeHist (MM)");
        }
        return null;

    }


    //Gets ownership history from ownership table (based on BID and OID)
    public static ArrayList<Ownership> getOwnershipHist(SQLManager sql, String BID, String OID){
        try {
            int oid = Integer.valueOf(OID);
            //System.out.println("EEEEEE");
            ResultSet r = sql.getOwnershipInfo(BID,oid);

            ArrayList<Ownership> l = new ArrayList<>();

            //System.out.println(r.next());


            while(r.next()){
                Ownership ow = new Ownership(r);
                l.add(ow);
                System.out.println(ow.getInfoString());
            }

            return l;

        }
        catch (Exception e){
            System.out.println(e + " In getOwnerHist (MM)");
        }
        return null;

    }




    public static ArrayList<Owner> getOwnerInfo(SQLManager sql, String ID, String fn, String ln, String con, String lic){
        try{
            ResultSet r = sql.getOwner(fn,ln,ID,con,lic);

            ArrayList<Owner> l = new ArrayList<>();
            int count = 0;
            Owner own;

            while(r.next()){
                System.out.println(String.format("Option '%s'",count));
                Owner o = new Owner(r);
                l.add(o);
                System.out.println(o.getString());
                count++;
            }

            return l;

        }
        catch (Exception e){
            System.out.println(e + "In getOwnerInfo (MM)");
        }
        return null;
    }
    public static Owner getOwnerInfo(SQLManager sql, Scanner in){
        while (true){
            try{

                System.out.println("Enter ownerID");
                String oID = in.nextLine();

                System.out.println("Enter firstname");
                String fn = in.nextLine();

                System.out.println("Enter lastname");
                String ln = in.nextLine();

                System.out.println("Enter contact");
                String cont = in.nextLine();

                System.out.println("Enter license");
                String lic = in.nextLine();

                ResultSet r = sql.getOwner(fn,ln,oID,cont,lic);

                List<Owner> l = new ArrayList<>();
                int count = 0;
                Owner own;

                while(r.next()){
                    System.out.println(String.format("Option '%s'",count));
                    Owner o = new Owner(r);
                    l.add(o);
                    System.out.println(o.getString());
                    count++;
                }


                if(count > 1){
                    System.out.println("Which owner did you mean (Enter a number)");
                    own = l.get(in.nextInt());
                }
                else own = l.get(0);

                return own;


            }
            catch (Exception e){
                System.out.println(e);
                System.out.println("No Results found");
                return null;
            }
        }
    }

    //returns an arraylist of permits given a boat ID
    public ArrayList<Permit> getBoatPermitHist(SQLManager sql, String ID){
        try{
            ResultSet r = sql.getPermitHist(ID);
            ArrayList<Permit> l = new ArrayList<>();


            while(r.next()){
                Permit p = new Permit(r);
                l.add(p);
            }

            return l;

        }
        catch (Exception e){
            System.out.println(e + " in getBoatPermitHist (MM)");
        }
        return null;
    }

    //returns an arrayList of noticess given by a boatID
    public ArrayList<Notice> getBoatNoticeHist(SQLManager sql, String ID){
        try{


        }
        catch (Exception e){
            System.out.println(e + " In getBoatNoticeHist (MM)");
        }
        return null;
    }
    public static void getBoatHist(SQLManager sql, Scanner in){
            Information i = getBoatInfo(sql,in);
            if(i == null)return;
            String id = i.getId();

            try{
                ResultSet r  = sql.getBoat(id);

                if(r.isBeforeFirst()){
                    Boat b = new Boat(r);
                    b.setInfo(i);
                    System.out.println(b.toString());
                }

                r = sql.getOwnershipInfo(id,-1);

                if(r.isBeforeFirst()){
                    Ownership ow = new Ownership(r);
                    System.out.println(ow.getInfoString());
                }

                r = sql.getNoticeInfo(-1,id,-1);

                if(r.isBeforeFirst()){
                    Notice n = new Notice(r);
                    System.out.println(n.getString());
                }

                //Boat b = new(sql.getBoat(i.getId()));
            }
            catch (Exception e){
                System.out.println(e);
            }

    }

    public static void getBoatHist(SQLManager sql, Information i){
        String id = i.getId();

        try{
            ResultSet r  = sql.getBoat(id);

            if(r.isBeforeFirst()){
                Boat b = new Boat(r);
                b.setInfo(i);
                System.out.println(b.toString());
            }

            r = sql.getOwnershipInfo(id,-1);

            if(r.isBeforeFirst()){
                Ownership ow = new Ownership(r);
                System.out.println(ow.getInfoString());
            }

            r = sql.getNoticeInfo(-1,id,-1);

            if(r.isBeforeFirst()){
                Notice n = new Notice(r);
                System.out.println(n.getString());
            }

            //Boat b = new(sql.getBoat(i.getId()));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static boolean updateBoatInfo(SQLManager sql, Information i){
        Scanner in = new Scanner(System.in);
        String newModel = null;
        float newLen = -1;
        String newType = null;
        String newPrimColor = null;
        String newDetailColor = null;
        String newTarpColor = null;
        int newNumSails = -1;
        int newNumMasts = -1;
        String newDesc = null;
        String newAdd1 = null;
        String newAdd2 = null;
        String input;
        try{

            System.out.print(String.format("Enter Boat Model (Currently: %s): ", i.getmodel()));
           input = in.nextLine();
            if (!input.isEmpty()) {
                newModel = input;
            }

            System.out.print(String.format("Enter Length (Currently: %.2f meters): ", i.getLen()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                try {
                    float inputLen = Float.parseFloat(input);
                    if (inputLen >= 0) {
                        newLen = inputLen;
                    } else {
                        System.out.println("Invalid input for length. Keeping current value.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for length. Keeping current value.");
                }
            }

            System.out.print(String.format("Enter Boat Type (Currently: %s): ", i.getType()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newType = input;
            }

            System.out.print(String.format("Enter Primary Color (Currently: %s): ", i.getPrimColor()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newPrimColor = input;
            }

            System.out.print(String.format("Enter Detail Color (Currently: %s): ", i.getDetailColor()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newDetailColor = input;
            }

            System.out.print(String.format("Enter Tarp Color (Currently: %s): ", i.getTarpColor()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newTarpColor = input;
            }

            System.out.print(String.format("Enter Number of Sails (Currently: %d): ", i.getNumSails()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                try {
                    int inputNumSails = Integer.parseInt(input);
                    if (inputNumSails >= 0) {
                        newNumSails = inputNumSails;
                    } else {
                        System.out.println("Invalid input for number of sails. Keeping current value.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for number of sails. Keeping current value.");
                }
            }

            System.out.print(String.format("Enter Number of Masts (Currently: %d): ", i.getNumMasts()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                try {
                    int inputNumMasts = Integer.parseInt(input);
                    if (inputNumMasts >= 0) {
                        newNumMasts = inputNumMasts;
                    } else {
                        System.out.println("Invalid input for number of masts. Keeping current value.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for number of masts. Keeping current value.");
                }
            }

            System.out.print(String.format("Enter Description (Currently: %s): ", i.getDesc()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newDesc = input;
            }

            System.out.print(String.format("Enter Additional Info 1 (Currently: %s): ", i.getAdd1()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newAdd1 = input;
            }

            System.out.print(String.format("Enter Additional Info 2 (Currently: %s): ", i.getAdd2()));
            input = in.nextLine();
            if (!input.isEmpty()) {
                newAdd2 = input;
            }

            i.updateBoatDetails(newModel,newLen,newType,newPrimColor,newDetailColor,newTarpColor,newNumSails,newNumMasts,newDesc,newAdd1,newAdd2);


            System.out.println("Is the following correct: \n");
            System.out.println(i.printBoatDetails());

            return sql.updateBoatInfo(i);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;

    }


    public static ArrayList<Information> getBoatInfo(SQLManager sql, String model, String len,String type,String primColor,String detailColor,String tarpColor,String numSails,String numMasts,String desc){
        try{
            ResultSet r = sql.getBoat(model, len, type, primColor, detailColor, tarpColor, numSails, numMasts, desc, "", "");
            ArrayList<Information> l = new ArrayList<>();
            Information c;
            int count = 0;


            while(r.next()){
                System.out.println(String.format("Option '%s'",count));
                Information i = new Information(r);
                l.add(i);
                System.out.println(i.printBoatDetails() + "\n");
                count++;
            }

            //c = l.get(0);

            return l;

        }
        catch (Exception e){
            System.out.println(e + " in getBoatInfo (MM)");
        }
        return null;
    }


    public static Information getBoatInfo(SQLManager sql, Scanner in){
        while(true){
            try{
                //String model, len, type, primColor, detailColor, detColor, tarpColor, numSails, numMasts, desc, add1, add2;
                System.out.println("Enter model:");
                String model = in.nextLine();

                System.out.println("Enter length:");
                String len = in.nextLine();

                System.out.println("Enter type:");
                String type = in.nextLine();

                System.out.println("Enter primary color:");
                String primColor = in.nextLine();

                System.out.println("Enter detail color:");
                String detailColor = in.nextLine();

                System.out.println("Enter detail color 2:");
                String detColor = in.nextLine();

                System.out.println("Enter tarp color:");
                String tarpColor = in.nextLine();

                System.out.println("Enter number of sails:");
                String numSails = in.nextLine();

                System.out.println("Enter number of masts:");
                String numMasts = in.nextLine();

                System.out.println("Enter description:");
                String desc = in.nextLine();

                System.out.println("Enter additional information 1:");
                String add1 = in.nextLine();

                System.out.println("Enter additional information 2:");
                String add2 = in.nextLine();

                ResultSet r = sql.getBoat(model, len, type, primColor, detailColor, tarpColor, numSails, numMasts, desc, add1, add2);


                List<Information> l = new ArrayList<>();
                Information c;
                int count = 0;


                while(r.next()){
                    System.out.println(String.format("Option '%s'",count));
                    Information i = new Information(r);
                    l.add(i);
                    System.out.println(i.printBoatDetails() + "\n");
                    count++;
                }

                if(count > 1){
                    System.out.println("Which boat did you mean (Enter a number)");
                    c = l.get(in.nextInt());
                }
                else c = l.get(0);

                return c;
            }
            catch (Exception e){
                System.out.println(e);
                in.nextLine();
                return null;
            }
        }
    }

    public static boolean newBoat(SQLManager sql, Scanner in){
        try{
            System.out.println("Please enter a HIN code");
            String hin = in.nextLine();

            System.out.println("Enter a name for the boat");
            String name = in.nextLine();

            Information I = new Information();


            boolean boat = sql.newBoat(hin,name,I);

            sql.printResultSet(sql.getBoatInfo(I.getId()));

            boolean boatInfo = updateBoatInfo(sql,I);

            //IF either fail, clean data
            if(!boat || !boatInfo) sql.cleanBoatData(I.getId());

        }
        catch (Exception e){
            System.out.println(e + " In newBoat (middleman)");
        }
        return false;
    }


    public static Notice newNotice(SQLManager sql, Scanner in, Information i){
        String boatId = i.getId();
        //MAYBE ADD A WHILE LOOP?
        Owner own = getOwnerInfo(sql,in);
        if(own == null)return null;

        try {
            System.out.println("Which group is this from? \n   VPD (0) ,RCMP (1) ,VPA (2) ,CoastGuard (3) ,TransportCanada (4)");

            int G = 0;
            while (true) {

                String input = in.nextLine();
                if (!input.isEmpty()) {
                    try {
                        G = Integer.parseInt(input);
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                }
            }

            Group g = null;
            switch (G){
                case 0:
                    g = Group.VPD;
                    break;
                case 1:
                    g = Group.RCMP;
                    break;
                case 2:
                    g = Group.VPA;
                    break;
                case 3:
                    g = Group.CCG;
                    break;
                case 4:
                    g = Group.TC;
                    break;
                default:
                    g = Group.VPD;
                    break;
            }


            System.out.println("Please input a type");
            String Type = in.nextLine();

            System.out.println("Please input a incident number");
            String IncNum = in.nextLine();



            System.out.println("Please input a date of issue (YYYY-MM-DD)");
            LocalDate dateOfIssue;
            while (true) {
                try {
                    String dateString = in.nextLine();
                    dateOfIssue = stringToDate(dateString);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.print("Invalid date format. Please enter in YYYY-MM-DD format: ");
                }
            }


            Notice n = new Notice(boatId, own.getId(),g,Type,IncNum,dateOfIssue);

            System.out.println("Is this correct: \n " + n.getString());
            sql.newNotice(n);


        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static Owner newOwner(SQLManager sql, Scanner in){
        Owner o = null;
        try{
            System.out.println("Please enter a first name");
            String fn = in.nextLine();

            System.out.println("Please enter a last name");
            String ln = in.nextLine();

            System.out.println("Please enter their phone number");
            String pn = in.nextLine();

            System.out.println("Please enter their license number");
            String lic = in.nextLine();

            o = new Owner(fn,ln,pn,lic);

        }
        catch (Exception e){
            System.out.println(e + " IN newOwner");
        }
        return o;
    }

    public static Ownership AddOwnership(SQLManager sql,Scanner in, Owner o, Information i){
        Ownership own = null;
        try{
            String f = String.format("Current owner selected is: %s %s \n Current boat selected is the: %s %s %s",
                    o.getFirstname(), o.getLastname(), i.getPrimColor(), i.getmodel(),i.getType());
            System.out.println(f + "\n is this correct?\n");

            System.out.println("Enter the date owned from (yyyy-mm-dd): ");
            LocalDate oF= stringToDate(in.nextLine());

            System.out.println("Enter the date owned to (yyyy-mm-dd): ");
            LocalDate oT = stringToDate(in.nextLine());

            own = new Ownership(i.getId(), o.getId(),oF,oT);

        }
        catch (Exception e){
            System.out.println(e + "In AddOwnership (MM)");
        }
        return own;
    }



    //HELPER FUNCTIONS
    public static LocalDate stringToDate(String d) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(d);
    }

}


