import java.sql.ResultSet;
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

}


