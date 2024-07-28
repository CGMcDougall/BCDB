import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OLD {

    JFrame jf = null;

    public OLD(){
        jf = new JFrame("GUI Screen");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.setVisible(true);

        // Get the local GraphicsEnvironment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get the default screen device
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        // Check if the screen device supports fullscreen mode
        if (gd.isFullScreenSupported()) {
            // Enter fullscreen mode
            gd.setFullScreenWindow(jf);
        } else {
            System.out.println("Fullscreen mode is not supported.");
            jf.setSize(800, 600); // Set a default size
            jf.setVisible(true); // Make the frame visible
        }
    }



    public void mainMenu(){
        // Create the main JFrame
        JFrame frame = new JFrame("Main Menu");

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager for the frame
        frame.setLayout(new BorderLayout());

        // Create a JPanel for the menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1)); // 3 buttons in a column


        JLabel Title = new JLabel("Does this work", JLabel.CENTER);

        // Create buttons for the menu
        JButton startButton = new JButton("Search");

        JButton exitButton = new JButton("Exit");

        // Add buttons to the panel
        menuPanel.add(Title);
        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        // Add the menu panel to the frame
        frame.add(menuPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Start Game button clicked");
                // You can replace this with code to start the game
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        // Set the size of the frame
        frame.setSize(300, 200);
        // Make the frame visible
        frame.setVisible(true);
    }
}







