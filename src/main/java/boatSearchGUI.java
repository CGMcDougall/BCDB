import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class boatSearchGUI extends GUI {
    private JPanel MainMenu;
    private JTextField modelTextField;
    private JTextField typeTextField;
    private JTextField primaryColorTextField;
    private JTextField numberOfSailsTextField;
    private JTextField tarpColorTextField;
    private JTextField detailColorTextField;
    private JTextField numberOfMastsTextField;
    private JTextField descriptionTextField;
    private JButton searchBoatButton;
    private JTextField lengthTextField;
    private JList BoatList;
    private ArrayList<Information> boatInfoList = new ArrayList<>();
    private Information SelectedBoat;
    private JLabel IMG;
    private JPanel subMain;
    private JList ownerList;
    private JTextField IDTextField;
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField contactTextField;
    private JTextField licenseTextField;
    private JList ownership;
    private JList Notices;
    private JButton searchOwnerButton;
    private JTextField modelDetails;
    private JTextField typeDetails;
    private JTextField lenDetails;
    private JTextField primDetails;
    private JTextField detDetails;
    private JTextField tarpDetails;
    private JTextField sailDetails;
    private JTextField mastDetails;
    private JTextField descDetails;
    private JButton saveButton;
    private JLabel activePermit;
    private JLabel boatID;
    private JButton cancelButton;
    private JList detailOwnerList;
    private JList detailNoticeList;
    private JList detailPermitList;
    private JButton clearBoatButton;
    private JButton clearButton1;
    private JTabbedPane Tabs;
    private JPanel boats;
    private JPanel owners;
    private JPanel boatDetails;
    private  ImageIcon imgIcon = null;
    private DefaultListModel<String> DefaultBoatList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultOwnerList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultNoticeList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultOwnershipList = new DefaultListModel<>();


    //For Details tab
    private DefaultListModel<String> DetailNotice = new DefaultListModel<>();
    private DefaultListModel<String> DetailOwner = new DefaultListModel<>();
    private DefaultListModel<String> DetailPermit = new DefaultListModel<>();


    //Tabs::: O = boats, 1 = boatDetails, 2 = owners, 3 = ownerDetails
    int Tab = 0;


    public boatSearchGUI(SQLManager sql){
        super(sql);

        //fullScreen();

        BoatList.setModel(DefaultBoatList);
        ownerList.setModel(DefaultOwnerList);
        ownership.setModel(DefaultOwnershipList);
        Notices.setModel(DefaultNoticeList);

        detailNoticeList.setModel(DetailNotice);
        detailPermitList.setModel(DetailPermit);
        detailOwnerList.setModel(DetailOwner);


        actions();


    }

    public void main(){

        setContentPane(MainMenu);
        revalidate();
        repaint();




        Image();


    }

    private void actions() {
        searchBoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boatSearch();
            }
        });

        searchOwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerSearch();
            }
        });
        BoatList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = BoatList.getSelectedIndex();

                if (index < 0) return;

                SelectedBoat = boatInfoList.get(index);
                detailInstantiate();

            }
        });
        Tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //JPanel jl = (JPanel) Tabs.getSelectedComponent();
                //System.out.println(jl.g);
                //System.out.println(Tabs.getSelectedIndex());
                Tab = Tabs.getSelectedIndex();
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getID() == KeyEvent.KEY_RELEASED) {
                            if(e.getKeyCode() == '\n'){
                                if(Tab == 0)boatSearch();
                                else if(Tab == 2)ownerSearch();

                            }
                        }
                        // Returning false to let the event continue to be dispatched
                        return false;
                    }
                });

        clearBoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoatSearch();
            }
        });
        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOwnerSearch();
            }
        });

    }

    private void boatSearch(){
        MiddleMan mm = new MiddleMan();
        String model = modelTextField.getText();
        String len = lengthTextField.getText();
        String type = typeTextField.getText();
        String primColor = primaryColorTextField.getText();
        String detailColor = detailColorTextField.getText();
        String tarpColor = tarpColorTextField.getText();
        String numSails = numberOfSailsTextField.getText();
        String numMasts = numberOfMastsTextField.getText();
        String desc = descriptionTextField.getText();

        boatInfoList = mm.getBoatInfo(sql, model, len, type, primColor, detailColor, tarpColor, numSails, numMasts, desc);

        DefaultBoatList.removeAllElements();
        if (boatInfoList == null || boatInfoList.isEmpty()){
            SelectedBoat = null;
            return;
        }


        for(Information a : boatInfoList)DefaultBoatList.addElement(a.stringFormat());
        //DefaultBoatList.addElement(I.printBoatDetails());




    }

    private void clearBoatSearch(){
        modelTextField.setText("");
        lengthTextField.setText("");
        typeTextField.setText("");
        primaryColorTextField.setText("");
        detailColorTextField.setText("");
        tarpColorTextField.setText("");
        numberOfSailsTextField.setText("");
        numberOfMastsTextField.setText("");
        descriptionTextField.setText("");
    }

    private void detailInstantiate(){
        if(SelectedBoat.equals(null))return;

        loadDetailText();
        loadExtendedDetail();
    }


    private void loadExtendedDetail(){
        String id = SelectedBoat.getId();

        MiddleMan mm = new MiddleMan();

        ArrayList<Notice> notices = mm.getNoticeHist(sql,id,"-1");
        DetailNotice.removeAllElements();
        if(!(notices == null) && !notices.isEmpty()){
            for(Notice n : notices)DetailNotice.addElement(n.getString());
        }

        ArrayList<Ownership> owns = mm.getOwnershipHist(sql,id,"-1");
        DetailOwner.removeAllElements();
        if(!(owns == null) && !owns.isEmpty()){
            for(Ownership o: owns)DetailOwner.addElement(o.getInfoString());
        }

        boolean active = false;

        ArrayList<Permit> perm = mm.getBoatPermitHist(sql,id);
        DetailPermit.removeAllElements();
        if(!(perm == null) && !perm.isEmpty()){
            for(Permit p : perm){
                DetailPermit.addElement(p.getString());

                //Check for active permit
                active = active || !p.isExpired();
            }

            if(active)activePermit.setText("Has active permit");
            else activePermit.setText("Has no active permit");
        }


    }


    private void loadDetailText(){
        modelDetails.setText(SelectedBoat.getmodel());
        typeDetails.setText(SelectedBoat.getType());
        lenDetails.setText(Float.toString(SelectedBoat.getLen()));
        primDetails.setText(SelectedBoat.getPrimColor());
        detDetails.setText(SelectedBoat.getDetailColor());
        tarpDetails.setText(SelectedBoat.getTarpColor());
        sailDetails.setText(Integer.toString(SelectedBoat.getNumSails()));
        mastDetails.setText(Integer.toString(SelectedBoat.getNumMasts()));
        descDetails.setText(SelectedBoat.getDesc());

        boatID.setText("ID: " + SelectedBoat.getId());

    }



    //// HELPER FUNCTIONS
    private void ownerSearch(){
        MiddleMan mm = new MiddleMan();
        String ID = IDTextField.getText();
        String fn = firstnameTextField.getText();
        String ln = lastnameTextField.getText();
        String c = contactTextField.getText();
        String lic = licenseTextField.getText();

        ArrayList<Owner> own = mm.getOwnerInfo(sql, ID, fn, ln, c, lic);

        for (Owner o : own) DefaultOwnerList.addElement(o.getString());

        if (ID != null) ownerHistory(sql, ID);
    }

    private void clearOwnerSearch(){
        IDTextField.setText("");
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        contactTextField.setText("");
        licenseTextField.setText("");
    }

    private void ownerHistory(SQLManager sql, String id){
        MiddleMan mm = new MiddleMan();
        ArrayList<Notice> n = mm.getNoticeHist(sql,"",id);
        ArrayList<Ownership> o = mm.getOwnershipHist(sql,"",id);

        for(Notice not : n)DefaultNoticeList.addElement(not.getString());
        for(Ownership own : o)DefaultOwnershipList.addElement(own.getInfoString());

    }


    protected void Image(){

        //C:\Users\Conno\AppData\Local\Programs\Java\BoatCopDB\src\img\p1.png

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image I = tk.getImage("src/img/p1.png");
        IMG.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Conno\\AppData\\Local\\Programs\\Java\\BoatCopDB\\src\\img\\p1.png").getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH)));
        //subMain.add(new JLabel(img));





//        File imageFile = new File("src/img/p1.png");
//        System.out.println(imageFile);
//        imgIcon = new ImageIcon(imageFile.getAbsolutePath());
//        IMG.setIcon(imgIcon);
//        System.out.println(IMG);

        //add(IMG);
        //pack();
        //String filename = "src/img/b1.png";
        //imgIcon = new ImageIcon(imageFile.getAbsolutePath());
//
//        try{
//            BufferedImage img = ImageIO.read(new File("src/img/b1.png"));
//            System.out.println(img);
//            Image dimg = img.getScaledInstance(IMG.getWidth(), IMG.getHeight(),
//                    Image.SCALE_SMOOTH);
//            imgIcon = new ImageIcon(dimg);
//            IMG.setIcon(imgIcon);
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }

//

            //imgIcon = new ImageIcon((new ImageIcon(new File("b1.png").getAbsolutePath()).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

//        imgIcon = new ImageIcon();
//
//        System.out.println(imgIcon.getImage());
        //IMG.setIcon(imgIcon);
//        System.out.println(IMG);
//        /add(IMG);
        //setVisible(true);

//        File imageFile = new File("src/img/b1.png");
//        //String filename = "src/img/b1.png";
//        imgIcon = new ImageIcon(imageFile.getAbsolutePath());
//        System.out.println(imgIcon);
//        IMG.setIcon(imgIcon);
//        //pack();
        //Img = new JLabel(icon);
        //System.out.println(imageFile.getAbsolutePath());

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here''

        //Toolkit tk = Toolkit.getDefaultToolkit();
        //Image I = tk.getImage("src/img/p1.png");
        //IMG = new JLabel(new ImageIcon("C:\\Users\\Conno\\AppData\\Local\\Programs\\Java\\BoatCopDB\\src\\img\\b1.png"));//.getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH)));

        //IMG = new JLabel();

        //IMG.setIcon((new ImageIcon(getClass().getClassLoader().getResource("img/b1.png"))));
        IMG = new JLabel();

        ImageIcon img = new ImageIcon(new ImageIcon("C:\\Users\\Conno\\AppData\\Local\\Programs\\Java\\BoatCopDB\\src\\img\\b1.png").getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        System.out.println(img);
                IMG.setIcon(img);

        System.out.println(IMG);

        //subMain.add(new JLabel(img));
        //pack();

    }
}
