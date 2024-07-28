import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
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

    public GUI(){
        setTitle("BCDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);

        setLocationRelativeTo(null);
        // Get the local GraphicsEnvironment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get the default screen device
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        // Check if the screen device supports fullscreen mode
        if (gd.isFullScreenSupported()) {
            // Enter fullscreen mode
            gd.setFullScreenWindow(this);
        } else {
            System.out.println("Fullscreen mode is not supported.");
            setSize(800, 600); // Set a default size
            setVisible(true); // Make the frame visible
        }


        BoatList.setModel(DefaultBoatList);

    }

    public void mainMenu(SQLManager sql){
        setContentPane(MainMenu);
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

                for(Information a : list)DefaultBoatList.addElement(a.printBoatDetails());

                //DefaultBoatList.addElement(I.printBoatDetails());



            }
        });



    }

}
