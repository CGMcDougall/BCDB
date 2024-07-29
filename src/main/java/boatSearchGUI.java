import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton searchButton;
    private JTextField lengthTextField;
    private JList<String> BoatList;
    private DefaultListModel<String> DefaultBoatList = new DefaultListModel<>();

    public boatSearchGUI(){
        super();

        //fullScreen();

        BoatList.setModel(DefaultBoatList);


    }

    public void main(SQLManager sql){

        setContentPane(MainMenu);
        revalidate();
        repaint();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                ArrayList<Information> list = mm.getBoatInfo(sql, model, len, type, primColor, detailColor, tarpColor, numSails, numMasts, desc);
                System.out.println(list.get(0).printBoatDetails());

                //for(Information a : list)DefaultBoatList.addElement(a.printBoatDetails());
                for(Information a : list)DefaultBoatList.addElement(a.stringFormat());
                //DefaultBoatList.addElement(I.printBoatDetails());



            }
        });



    }

}
