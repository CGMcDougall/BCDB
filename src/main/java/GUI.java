import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI extends JFrame implements KeyListener {

protected SQLManager sql;

    public GUI(SQLManager sql){
        setTitle("BCDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);

        setLocationRelativeTo(null);
        // Get the local GraphicsEnvironment
        fullScreen();

        this.sql = sql;

        this.addKeyListener(this);

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


    public void main(){


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(e.getKeyChar());
    }
}
