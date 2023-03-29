import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frameLogin {
    private Main main;
    private JFrame frame;
    private JTextField fieldEmail;
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
                frameCreateAccount form = new frameCreateAccount(main);
                User user =  form.user;
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
    private void login(){
        boolean valid = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            String sql = "SELECT password, email, role FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, fieldEmail.getText());
            statement.setString(2, String.valueOf(fieldPassword.getPassword()));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                if (email.equals(fieldEmail.getText()) && password.equals(String.valueOf(fieldPassword.getPassword()))){
                    valid = true;
                    switch (role) {
                        case "Customer" -> new frameCustomer(main);
                        case "Office Manager" -> new frameOfficeManager(main);
                        case "Travel Advisor" -> new frameTravelAdvisor(main);
                        case "System Admin" -> new frameSystemAdmin(main);
                        case "AirVia Staff" -> new frameAirVia(main);
                    }
                }
            }
            statement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        if (!valid){
            JOptionPane.showMessageDialog(frame, "Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
}
