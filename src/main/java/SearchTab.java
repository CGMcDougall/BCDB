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

public class SearchTab extends JPanel {
    private JTabbedPane SearchTabs;
    private JPanel boats;
    private JPanel subMain;
    private JList BoatList;
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
    private JButton clearBoatButton;
    private JPanel owners;
    private JList ownerList;
    private JTextField IDTextField;
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField contactTextField;
    private JTextField licenseTextField;
    private JButton searchOwnerButton;
    private JList ownership;
    private JList Notices;
    private JButton clearOwnerButton;
    private JList NSnotice;
    private JTextField NSincidentNumTextField;
    private JTextField NSdateOfIssueTextField;
    private JComboBox NSgroup;
    private JTextField NSboatIDTextField;
    private JTextField NSownerIDTextField;
    private JButton NSsearchButton;
    private JButton NSclearButton;
    private JPanel SearchTab;


    private DefaultListModel<String> DefaultBoatList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultOwnerList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultNoticeList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultOwnershipList = new DefaultListModel<>();


    //For Details tab
    private DefaultListModel<String> DetailNotice = new DefaultListModel<>();
    private DefaultListModel<String> DetailOwner = new DefaultListModel<>();
    private DefaultListModel<String> DetailPermit = new DefaultListModel<>();

    private DefaultListModel<String> NSNoticeList = new DefaultListModel<>();


    //Notice Dialog
    JDialog jd = null;


    //Stored DB info
    private ArrayList<Information> boatInfoList = new ArrayList<>();
    private ArrayList<Owner> OwnerInfoList = new ArrayList<>();
    private ArrayList<Notice> NoticeInfoList = new ArrayList<>();
    private ArrayList<Notice> NSNoticeInfoList = new ArrayList<>();

    private ArrayList<Ownership> OwnershipInfoList = new ArrayList<>();

    private Information SelectedBoat;
    private Owner SelectedOwner;


    int searchTab = 0;
    private SQLManager sql = null;


    public SearchTab(SQLManager sql){
        super();
        this.sql = sql;
        add(SearchTab);
        setVisible(true);

    }


    public void setSQL(SQLManager sql){
        this.sql = sql;
    }



    //TODO ACTIONS
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
                //boatDetailInstantiate();

            }
        });
        SearchTabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //JPanel jl = (JPanel) Tabs.getSelectedComponent();
                //System.out.println(jl.g);
                //System.out.println(Tabs.getSelectedIndex());
                searchTab = SearchTabs.getSelectedIndex();
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    if(e.getKeyCode() == '\n'){
                        if(searchTab == 0)boatSearch();
                        else if(searchTab == 1)ownerSearch();

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
        clearOwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOwnerSearch();
            }
        });

        ownerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = ownerList.getSelectedIndex();

                if (index < 0) return;

                SelectedOwner = OwnerInfoList.get(index);
                //ownerDetailInstantiate();
                ownerHistory();
            }
        });

        Notices.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int index = Notices.getSelectedIndex();
                Notice n = NoticeInfoList.get(index);

                if(n == null || jd != null)return;

                JFrame j =  (JFrame) SwingUtilities.getWindowAncestor(Notices);

                jd = new NoticePopup(j, sql,n);
                jd.setModal(true);


            }
        });

        NSsearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noticeSearch();
            }
        });
        NSclearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        NSnotice.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = NSnotice.getSelectedIndex();
                Notice n = NSNoticeInfoList.get(index);

                if(n == null || jd != null)return;

                JFrame j =  (JFrame) SwingUtilities.getWindowAncestor(NSnotice);

                jd = new NoticePopup(j, sql,n);
                jd.setModal(true);
            }
        });


        //editActions();

    }

//    private void editActions(){
//        ODsave.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SelectedOwner.setFirstname(ODfn.getText());
//                SelectedOwner.setLastname(ODln.getText());
//                SelectedOwner.setContact(ODcontact.getText());
//                SelectedOwner.setLic(ODlic.getText());
//
//                MiddleMan mm = new MiddleMan();
//
//                boolean worked = mm.updateOwnerInfo(sql,SelectedOwner);
//                System.out.println(worked);
//            }
//        });
//        ODedit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //ODid.setEditable(true);
//                ODfn.setEditable(true);
//                ODln.setEditable(true);
//                ODcontact.setEditable(true);
//                ODlic.setEditable(true);
//            }
//        });
//        ODcancel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                loadOwnerDetailText();
//                //ODid.setEditable(false);
//                ODfn.setEditable(false);
//                ODln.setEditable(false);
//                ODcontact.setEditable(false);
//                ODlic.setEditable(false);
//
//            }
//        });
//        BDsave.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(mastDetails.isEditable())editBoatDetails();
//            }
//        });
//        BDedit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                modelDetails.setEditable(true);
//                typeDetails.setEditable(true);
//                lenDetails.setEditable(true);
//                primDetails.setEditable(true);
//                detDetails.setEditable(true);
//                tarpDetails.setEditable(true);
//                sailDetails.setEditable(true);
//                mastDetails.setEditable(true);
//                descDetails.setEditable(true);
//            }
//        });
//
//        BDcancelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                loadBoatDetailText();
//                modelDetails.setEditable(false);
//                typeDetails.setEditable(false);
//                lenDetails.setEditable(false);
//                primDetails.setEditable(false);
//                detDetails.setEditable(false);
//                tarpDetails.setEditable(false);
//                sailDetails.setEditable(false);
//                mastDetails.setEditable(false);
//                descDetails.setEditable(false);
//            }
//        });
//
//    }




    //TODO SEARCH INFO
    private void noticeSearch(){
        MiddleMan mm = new MiddleMan();
        String incidentNum = NSincidentNumTextField.getText();
        String ownerID = NSownerIDTextField.getText();
        String boatID = NSboatIDTextField.getText();
        String dateOfIssue = NSdateOfIssueTextField.getText();

        // Retrieve selected item from the combo box
        String group = (String) NSgroup.getSelectedItem();
        if (group.equalsIgnoreCase("NA"))group = "";

        ArrayList<Notice> l = mm.getNotice(sql,incidentNum,ownerID,boatID,dateOfIssue,group);

        NSNoticeInfoList = l;

        NSNoticeList.removeAllElements();

        for(Notice n : l){
            System.out.println(n.getString());
            NSNoticeList.addElement(n.getString());
        }

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

    //TODO LOAD INFO

    private void ownerSearch(){
        MiddleMan mm = new MiddleMan();
        String ID = IDTextField.getText();
        String fn = firstnameTextField.getText();
        String ln = lastnameTextField.getText();
        String c = contactTextField.getText();
        String lic = licenseTextField.getText();



        OwnerInfoList = mm.getOwnerInfo(sql, ID, fn, ln, c, lic);

        DefaultOwnerList.removeAllElements();
        if (OwnerInfoList == null || OwnerInfoList.isEmpty()){
            SelectedOwner = null;
            return;
        }


        for (Owner o : OwnerInfoList) DefaultOwnerList.addElement(o.getString());

        //loadOwnerDetailText();

        //loadBoatDetailText();if (ID != null) ownerHistory(sql, ID);
    }

//    private void loadBoatDetailText(){
//        if(SelectedBoat == null)return;;
//        modelDetails.setText(SelectedBoat.getmodel());
//        typeDetails.setText(SelectedBoat.getType());
//        lenDetails.setText(Float.toString(SelectedBoat.getLen()));
//        primDetails.setText(SelectedBoat.getPrimColor());
//        detDetails.setText(SelectedBoat.getDetailColor());
//        tarpDetails.setText(SelectedBoat.getTarpColor());
//        sailDetails.setText(Integer.toString(SelectedBoat.getNumSails()));
//        mastDetails.setText(Integer.toString(SelectedBoat.getNumMasts()));
//        descDetails.setText(SelectedBoat.getDesc());
//
//        boatID.setText("ID: " + SelectedBoat.getId());
//
//    }

    private void ownerHistory(){

        if(SelectedOwner == null)return;

        DefaultNoticeList.removeAllElements();
        DefaultOwnershipList.removeAllElements();

        MiddleMan mm = new MiddleMan();
        NoticeInfoList = mm.getNoticeHist(sql,"",Integer.toString(SelectedOwner.getId()));
        OwnershipInfoList = mm.getOwnershipHist(sql,"",Integer.toString(SelectedOwner.getId()));

        for(Notice not : NoticeInfoList)DefaultNoticeList.addElement(not.getString());
        for(Ownership own : OwnershipInfoList)DefaultOwnershipList.addElement(own.getInfoString());

    }

//    private void loadOwnerDetailText(){
//        if(SelectedOwner == null)return;
//        ODid.setText(Integer.toString(SelectedOwner.getId()));
//        ODfn.setText(SelectedOwner.getFirstname());
//        ODln.setText(SelectedOwner.getLastname());
//        ODcontact.setText(SelectedOwner.getContact());
//        ODlic.setText(SelectedOwner.getLic());
//
//
//
//    }

    //TODO LOAD FUNCTION HELPERS
//    private void boatDetailInstantiate(){
//        if(SelectedBoat.equals(null))return;
//
//        loadBoatDetailText();
//        loadExtendedDetail();
//    }

//    private void ownerDetailInstantiate(){
//        if(SelectedOwner.equals(null))return;
//
//        loadOwnerDetailText();
//        //loadExtendedDetail();
//    }

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

            //if(active)activePermit.setText("Has active permit");
            //else activePermit.setText("Has no active permit");
        }


    }


    //TODO EDIT INFO
//    private boolean editBoatDetails(){
//        try{
//
//            SelectedBoat.updateBoatDetails(modelDetails.getText(),Float.parseFloat(lenDetails.getText()),typeDetails.getText(),
//                    primDetails.getText(),detDetails.getText(),tarpDetails.getText(),Integer.parseInt(sailDetails.getText()),Integer.parseInt(mastDetails.getText()),
//                    descDetails.getText(),"","");
//
//            MiddleMan mm = new MiddleMan();
//            boolean worked = mm.updateBoatInfo(sql,SelectedBoat);
//            System.out.println(worked);
//        }
//        catch (Exception e){
//            System.out.println(e + " In GUI");
//        }
//        return false;
//    }




    //TODO HELPER FUNCTIONS


    private void clearOwnerSearch(){
        IDTextField.setText("");
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        contactTextField.setText("");
        licenseTextField.setText("");
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



}
