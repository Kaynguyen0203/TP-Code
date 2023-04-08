import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frameLogin {
    private final Main main;
    private final JFrame frame;
    private JTextField fieldName;
    private JPasswordField fieldPassword;
    private JButton buttonEnter;
    private JButton buttonCreateAccount;
    private JPanel panelLogin;
    private JButton buttonExit;

    public frameLogin(Main main){
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelLogin);
        frame.pack();
        buttonCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCreateAccount(main);
            }
        });
        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    /*
    this method will allow the user to login
    takes the field of name and password, compares them to the database and logs into a frame depending on the role
     */
    private void login(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement statement = con.prepareStatement(
                    "SELECT UserID, name, password, email, address, role FROM users WHERE name = ? AND password = ?");
            statement.setString(1, fieldName.getText());
            statement.setString(2, String.valueOf(fieldPassword.getPassword()));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int userID = resultSet.getInt("userID");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                int discount = 0;
                if (role.equals("Customer")){
                    PreparedStatement statement2 = con.prepareStatement("SELECT discountPercent FROM usersCustomers " +
                            "WHERE UserID = ?");
                    statement2.setInt(1, userID);
                    ResultSet resultSet2 = statement2.executeQuery();
                    if (resultSet2.next()){
                        discount = resultSet2.getInt("discountPercent");
                    }
                }
                User user = new User(userID,name, password, email, address, role, discount );
                main.setUser(user);
                switch (role) {
                        case "Customer" -> new frameCustomer(main);
                        case "Office Manager" -> new frameOfficeManager(main);
                        case "Travel Advisor" -> new frameTravelAdvisor(main);
                        case "System Admin" -> new frameSystemAdmin(main);
                }
            } else{
                JOptionPane.showMessageDialog(frame, "Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
