import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI(){
        setTitle("BCDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);

        setLocationRelativeTo(null);
        // Get the local GraphicsEnvironment
        fullScreen();

    }



    protected void fullScreen(){
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

    }


    public void main(SQLManager sql){


    }



}
