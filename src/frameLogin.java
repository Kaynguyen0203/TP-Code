import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class frameLogin {
    private Main main;
    private JFrame frame;
    private JTextField fieldEmail;
    private JPasswordField fieldPassword;
    private JButton buttonEnter;
    private JButton buttonCreateAccount;
    private JPanel panelLogin;

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
    }
    private void login(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            String query = "select name, password, email, address, role from users";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                if (email.equals(fieldEmail.getText()) && password.equals(String.valueOf(fieldPassword.getPassword()))){
                    switch (role) {
                        case "Customer":
                            new frameCustomer(main);
                            break;
                        case "Office Manager":
                            new frameOfficeManager(main);
                            break;
                        case "Travel Advisor":
                            new frameTravelAdvisor(main);
                            break;
                        case "System Admin":
                            new frameSystemAdmin(main);
                            break;
                        case "AirVia Staff":
                            new frameAirVia(main);
                            break;
                    }
                }
            }
            statement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
