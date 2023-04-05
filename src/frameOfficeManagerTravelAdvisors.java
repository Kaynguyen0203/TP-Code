import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameOfficeManagerTravelAdvisors {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerTravelAdvisors;
    private JButton buttonGoBack;
    private JPanel panelSecondary;
    private JTextField fieldDate;

    public frameOfficeManagerTravelAdvisors(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerTravelAdvisors);
        main.setUpUserTopLabels(panelSecondary);
        setUpTravelAdvisorsList();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeUserActionListeners();
                new frameOfficeManager(main);
            }
        });
    }
    private void setUpTravelAdvisorsList(){
        ArrayList<User> travelAdvisorArrayList = new ArrayList<User>();
        ArrayList<User> userArrayList = main.getUserArrayList();
        for (User user : userArrayList) {
            if (user.getRole().equals("Travel Advisor")) {
                travelAdvisorArrayList.add(user);
            }
        }
        GridBagConstraints buttonConstraints = main.setUserButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<travelAdvisorArrayList.size(); i++) {
            buttonConstraints.gridy =i+1;
            labelConstraints.gridy =i+1;
            User user = travelAdvisorArrayList.get(i);
            JButton userButton = user.getButton();
            main.setUpUserDataLabels(labelConstraints, user, panelSecondary);
            userButton.setText("Generate");
            userButton.setEnabled(true);
            userButton.setBackground(Color.GREEN);
            userButton.setForeground(Color.BLACK);
            panelSecondary.add(userButton, buttonConstraints);
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int date = Integer.parseInt(fieldDate.getText());
                        main.removeUserActionListeners();
                        new frameOfficeManagerIndividualReport(main, user, date);
                    } catch(Exception a){
                        a.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Invalid input", "Try again", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
}
