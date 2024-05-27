import java.sql.ResultSet;
import java.util.Scanner;

public class main {

    public static void main (String[] args){
        SQLManager sql = new SQLManager();
        Scanner in = new Scanner(System.in);
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
                 sql.printResultSet(r);
            }
            catch (Exception e){
                in.nextLine();
            }
        }

    }

}
