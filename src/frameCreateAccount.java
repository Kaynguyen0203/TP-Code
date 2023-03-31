import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frameCreateAccount {
    private final Main main;
    private final JFrame frame;
    private JTextField fieldName;
    private JTextField fieldPassword;
    private JButton buttonEnter;
    private JComboBox fieldComboBox;
    private JPanel panelCreateAccount;
    private JTextField fieldEmail;
    private JTextField fieldAddress;
    private JButton buttonGoBack;
    private User user;
    public frameCreateAccount(Main main){
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCreateAccount);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameLogin(main);
            }
        });
        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

    }
    private void createAccount(){
        String name = fieldName.getText();
        String password = fieldPassword.getText();
        String email = fieldEmail.getText();
        String address = fieldAddress.getText();
        String role = (String) fieldComboBox.getSelectedItem();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || address.isEmpty() || role.isEmpty()){
            JOptionPane.showMessageDialog(frame, "One or more empty fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToSQL(name, password, email, address, role);
        if (user != null){
            new frameLogin(main);
        }
        else {
            JOptionPane.showMessageDialog(frame, "Failed to register new user", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
    private User addUserToSQL(String name, String password, String email, String address, String role){
        User user = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO users (UserID, name, password, email, address, role VALUES (?,?,?,?,?)");
            int highestUserID = getHighestUserID();
            preparedStatement.setInt(1, highestUserID+1);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, role);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                user = new User(highestUserID +1, name, password, email, address, role);
            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
    private int getHighestUserID(){
        int highest = 1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT MAX(userID) FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                highest = resultSet.getInt("MAX(userID)");
            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return highest;
    }
}
