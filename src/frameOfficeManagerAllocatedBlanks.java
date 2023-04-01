import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class frameOfficeManagerAllocatedBlanks {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerAllocatedBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerAllocatedBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocatedBlanks);
        frameOfficeManagerSystemStock.setUpLabels(panelSecondary);
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
        GridBagConstraints buttonConstraints = frameOfficeManagerSystemStock.setButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        for (int i=0; i<blankArrayList.size(); i++){
            buttonConstraints.gridy = i+1;
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            if (blank.getSellerUserID()!=0){
                JButton blankButton = blank.getButton();
                frameOfficeManagerSystemStock.setUpMoreLabels(labelConstraints, blank, panelSecondary);
                blankButton.setText("Reallocate");
                blankButton.setBackground(Color.GREEN);
                blankButton.setForeground(Color.BLACK);
                blankButton.setEnabled(true);
                panelSecondary.add(blankButton, buttonConstraints);
                if (blankButton.getActionListeners().length ==0){
                    blankButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeBlankFromTravelAdvisor(blank);
                            blankButton.removeActionListener(this);
                            new frameOfficeManagerSystemStock(main);
                            JOptionPane.showMessageDialog(frame, "Blank removed from travel advisor\nBlank can be allocated again", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                }
            }
        }
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
