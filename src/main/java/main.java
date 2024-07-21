import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        SQLManager sql = new SQLManager();
        Scanner in = new Scanner(System.in);

        Boat b = null;
        Information i = null;



        //i = MiddleMan.getBoatInfo(sql,in);

        MiddleMan.newBoat(sql,in);

        //MiddleMan.newNotice(sql,in,i);
        //MiddleMan.updateBoatInfo(sql,i);
        //MiddleMan.updateBoatInfo(sql,i);
        //sql.updateBoatInfo(i);
        //getOwnerInfo(sql,in);
        //getBoatInfo(sql,in);
        //MiddleMan.getOwnerHist(sql, in);
        //MiddleMan.getBoatHist(sql,in);
    }


}

