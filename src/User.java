import javax.swing.*;

public class User {
    private final int userID;
    private String name;
    private String password;
    private String email;
    private String address;
    private String role;
    private final JButton button;
    private int discount;
    User (int userID, String name, String password, String email, String address, String role, int discount){
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
        this.button = new JButton();
        this.discount = discount;
    }
    public int getUserID() {return userID;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}
    public String getRole() {return role;}
    public JButton getButton() {return button;}
    public int getDiscount() {return discount;}
    public void setName(String name) {this.name = name;}
    public void setPassword(String password) {this.password = password;}
    public void setEmail(String email) {this.email = email;}
    public void setAddress(String address) {this.address = address;}
    public void setRole(String role) {this.role = role;}
    public void setDiscount(int discount) {this.discount = discount;}
}
