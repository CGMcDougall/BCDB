import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoticePopup extends JDialog {

    private JPanel Notice;
    private JButton cancelButton;
    private JButton confirmButton;
    private JTextField group;
    private JTextField NoticeType;
    private JTextField incident;
    private JTextField date;

    SQLManager sql = null;
    Notice n = null;

    public NoticePopup(JFrame f, SQLManager sql, Notice n){
        super(f,"NoticeInfo",false);

        this.sql = sql;
        this.n = n;




        updateNotice();

        add(Notice);
        action();

        pack();
        setVisible(true);
        setLocationRelativeTo(f);

    }


    private void updateNotice(){
        group.setText(n.getGroup().toString());
        NoticeType.setText(n.getType());
        incident.setText(n.getIncidentNumber());
        date.setText(n.getDateOfIssue().toString());

    }

    private void main(){


    }

    private void action(){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n.setIncidentNumber(incident.getText());
                n.setType(NoticeType.getText());
                n.setDateOfIssue(date.getText());

                MiddleMan mm = new MiddleMan();
                boolean worked = mm.updateNotice(sql,n);
                System.out.println(worked);

                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }



}



