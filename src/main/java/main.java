import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        SQLManager sql = new SQLManager();
        Scanner in = new Scanner(System.in);

        //getOwnerInfo(sql,in);
        //getBoatInfo(sql,in);
        //MiddleMan.getOwnerHist(sql, in);
        MiddleMan.getBoatHist(sql,in);
    }


}

