import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
this class allows the office manager to see the current customers in the system
the set up customers list method sets up the rows of labels and the corresponding buttons under the top labels
the buttons will change to another frame to set a discount for the selected customer/user
 */
public class frameOfficeManagerCurrentCustomers {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerCurrentCustomers;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerCurrentCustomers(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerCurrentCustomers);
        setUpCustomerList();

        main.setUpUserTopLabels(panelSecondary);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeUserActionListeners();
                new frameOfficeManager(main);
            }
        });
    }

    private void setUpCustomerList(){
        ArrayList<User> customerArrayList = new ArrayList<User>();
        ArrayList<User> userArrayList = main.getUserArrayList();
        for (User user : userArrayList) {
            if (user.getRole().equals("Customer")) {
                customerArrayList.add(user);
            }
        }
        GridBagConstraints buttonConstraints = main.setUserButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<customerArrayList.size(); i++) {
            buttonConstraints.gridy = i + 1;
            labelConstraints.gridy = i + 1;
            User user = customerArrayList.get(i);
            JButton userButton = user.getButton();
            main.setUpUserDataLabels(labelConstraints, user, panelSecondary);
            userButton.setText("Set Discount");
            userButton.setBackground(Color.GREEN);
            userButton.setForeground(Color.BLACK);
            panelSecondary.add(userButton, buttonConstraints);
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.removeUserActionListeners();
                    new frameOfficeManagerSetDiscount(main, user);
                }
            });
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
}
