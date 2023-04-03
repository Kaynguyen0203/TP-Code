import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameSystemAdminStaffList {
    private final Main main;
    private final JFrame frame;
    private JPanel panelSystemAdminStaffList;
    private JButton goBackButton;
    private JPanel panelSecondary;

    public frameSystemAdminStaffList(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminStaffList);
        main.setUpUserTopLabels(panelSecondary);
        setUpStaffList();
        frame.pack();
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeUserActionListeners();
                new frameSystemAdmin(main);
            }
        });
    }
    private void setUpStaffList(){
        ArrayList<User> userArrayList = main.getUserArrayList();
        GridBagConstraints buttonConstraints = main.setUserButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<userArrayList.size(); i++) {
            buttonConstraints.gridy =i+1;
            labelConstraints.gridy =i+1;
            User user = userArrayList.get(i);
            JButton userButton = user.getButton();
            main.setUpUserDataLabels(labelConstraints, user, panelSecondary);
            userButton.setText("Change Details");
            userButton.setBackground(Color.GREEN);
            userButton.setForeground(Color.BLACK);
            panelSecondary.add(userButton, buttonConstraints);
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userButton.removeActionListener(this);
                    new frameSystemAdminEditContactDetails(main, user);
                }
            });
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);

    }
}
