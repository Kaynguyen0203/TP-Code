import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/*
this sequence of methods sets up the labels and corresponding buttons for blanks that have
already been assigned to a travel advisor
a button can be reallocated if the blank has not been validated yet
 */
public class frameOfficeManagerAllocatedBlanks {
    private final Main main;
    private final JFrame frame;
    private JPanel panelOfficeManagerAllocatedBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerAllocatedBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocatedBlanks);
        main.setUpBlankTopLabels(panelSecondary);
        setUpButtons();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameOfficeManagerSystemStock(main);
            }
        });
    }

    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints buttonConstraints = main.setBlankButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.gridy = 0;
        for (int i=0; i<blankArrayList.size(); i++){
            Blank blank = blankArrayList.get(i);
            if (blank.getSellerUserID()!=0){
                buttonConstraints.gridy =i+1;
                labelConstraints.gridy = i+1;
                JButton blankButton = blank.getButton();
                main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
                blankButton.setText("Reallocate");
                blankButton.setBackground(Color.GREEN);
                blankButton.setForeground(Color.BLACK);
                blankButton.setEnabled(false);
                if (!blank.getIsValidated()){
                    blankButton.setEnabled(true);
                }
                panelSecondary.add(blankButton, buttonConstraints);
                if (blankButton.getActionListeners().length ==0){
                    blankButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeBlankFromTravelAdvisor(blank);
                            main.removeBlankActionListeners();
                            new frameOfficeManagerSystemStock(main);
                            JOptionPane.showMessageDialog(frame, "Blank removed from travel advisor\nBlank can be allocated again", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                }
            }
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
    private void removeBlankFromTravelAdvisor(Blank blank){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE blanks SET sellerUserID = ?" +
                    " WHERE blankNumber = ?");
            preparedStatement.setString(1, null);
            preparedStatement.setInt(2, blank.getBlankNumber());
            preparedStatement.executeUpdate();
            con.close();
            preparedStatement.close();
            blank.setSellerUserID(0);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
