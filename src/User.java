import javax.swing.*;

public class User {
    private final int userID;
    private final String name;
    private final String password;
    private final String email;
    private final String address;
    private final String role;
    private final JButton button;
    User (int userID, String name, String password, String email, String address, String role){
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
        this.button = new JButton();
    }
    public int getUserID() {return userID;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}
    public String getRole() {return role;}
    public JButton getButton() {return button;}
}
