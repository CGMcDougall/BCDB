import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ownerSearchGUI extends GUI {
    private JList ownerList;
    private JPanel MainMenu;
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField contactTextField;
    private JTextField licenseTextField;
    private JTextField IDTextField;
    private JButton searchButton;
    private JList ownership;
    private JList Notices;
    private DefaultListModel<String> DefaultOwnerList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultNoticeList = new DefaultListModel<>();
    private DefaultListModel<String> DefaultOwnershipList = new DefaultListModel<>();


    public ownerSearchGUI(SQLManager sql){
        super(sql);
        ownerList.setModel(DefaultOwnerList);
        ownership.setModel(DefaultOwnershipList);
        Notices.setModel(DefaultNoticeList);
        setVisible(true);

    }


    public void main(SQLManager sql){

        setContentPane(MainMenu);
        revalidate();
        repaint();


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiddleMan mm = new MiddleMan();
                String ID = IDTextField.getText();
                String fn = firstnameTextField.getText();
                String ln = lastnameTextField.getText();
                String c = contactTextField.getText();
                String lic = licenseTextField.getText();

                ArrayList<Owner> own = mm.getOwnerInfo(sql,ID,fn,ln,c,lic);

                for(Owner o : own)DefaultOwnerList.addElement(o.getString());

                if(ID != null)ownerHistory(sql,ID);



            }
        });
    }


    public void ownerHistory(SQLManager sql, String id){
        MiddleMan mm = new MiddleMan();
        ArrayList<Notice> n = mm.getNoticeHist(sql,"",id);
        ArrayList<Ownership> o = mm.getOwnershipHist(sql,"",id);

        for(Notice not : n)DefaultNoticeList.addElement(not.getString());
        for(Ownership own : o)DefaultOwnershipList.addElement(own.getInfoString());

    }




}
