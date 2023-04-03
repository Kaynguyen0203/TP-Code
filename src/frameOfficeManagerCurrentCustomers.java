import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    }
}
