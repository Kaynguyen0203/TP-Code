import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class frameSystemAdminEditContactDetails {
    private final Main main;
    private final JFrame frame;
    private JPanel panelSystemAdminEditContactDetails;
    private JTextField fieldName;
    private JTextField fieldEmail;
    private JTextField fieldAddress;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JComboBox fieldRole;
    private JPanel panelSecondary;
    private final User user;

    public frameSystemAdminEditContactDetails(Main main, User user) {
        this.main = main;
        this.user = user;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminEditContactDetails);
        main.setUpUserTopLabels(panelSecondary);
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        main.setUpUserDataLabels(labelConstraints, user, panelSecondary);
        frame.pack();
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContactInfo();
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeUserActionListeners();
                for (ActionListener listener :buttonConfirm.getActionListeners()) {
                    buttonConfirm.removeActionListener(listener);
                }
                new frameSystemAdminStaffList(main);
            }
        });
    }
    private void updateContactInfo(){
        String name = fieldName.getText();
        String email = fieldName.getText();
        String address = fieldAddress.getText();
        String role = (String) fieldRole.getSelectedItem();
        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || role.isEmpty()){
            JOptionPane.showMessageDialog(frame, "One or more empty fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int userID = user.getUserID();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE users SET name=?, email=?, address=?, role=? "
                    + "WHERE userID=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, role);
            preparedStatement.setInt(5, userID);
            preparedStatement.executeUpdate();
            con.close();
            preparedStatement.close();
            user.setName(name);
            user.setEmail(email);
            user.setAddress(address);
            user.setRole(role);
            JOptionPane.showMessageDialog(frame, "Staff details have changed", "Success", JOptionPane.INFORMATION_MESSAGE);
            main.removeUserActionListeners();
            for (ActionListener listener :buttonConfirm.getActionListeners()) {
                buttonConfirm.removeActionListener(listener);
            }
            new frameSystemAdminStaffList(main);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
