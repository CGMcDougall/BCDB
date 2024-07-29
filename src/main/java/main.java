import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        SQLManager sql = new SQLManager();
        Scanner in = new Scanner(System.in);

        Boat b = null;
        Information i = null;

//        boatSearchGUI boatSearchGui = new boatSearchGUI();
//        boatSearchGui.main(sql);

        ownerSearchGUI ownerSearchGUI = new ownerSearchGUI();
        ownerSearchGUI.main(sql);

        //gui.mainMenu();

        //i = MiddleMan.getBoatInfo(sql,in);
        //Owner o = MiddleMan.newOwner(sql,in);
        //sql.newOwner(o);
        //Owner o = MiddleMan.getOwnerInfo(sql,in);

        //Ownership own = MiddleMan.AddOwnership(sql,in,o,i);

        //sql.newOwnership(own);

        //System.out.println(o.getString() + "\n" + own.getInfoString());


        //MiddleMan.newBoat(sql,in);

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

