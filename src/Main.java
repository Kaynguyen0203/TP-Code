import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {
    private Main main;
    private JFrame mainFrame;
    private User user;
    private ArrayList<Blank> blankArrayList;
    private ArrayList<User> userArrayList;
    public static void main(String[] args) {new Main();}
    public Main(){
        this.main = this;
        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpBlanks();
        setUpUsers();
        new frameLogin(main);
    }

    public Main getMain(){return main;}
    public JFrame getMainFrame(){return mainFrame;}
    public User getUser() {return user;}
    public void setUser(User newUser) {this.user = newUser;}
    public ArrayList<Blank> getBlankArrayList() {return blankArrayList;}
    public ArrayList<User> getUserArrayList() {return userArrayList;}
    private void setUpBlanks(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "SELECT blankNumber, dateIssued, dateValidated, ticketType, destination, " +
                    "flightDate, seatNumber, ticketPrice, sellerName, customerName, dateSold FROM blanks");
            ResultSet resultSet = preparedStatement.executeQuery();
            this.blankArrayList = new ArrayList<Blank>();
            while (resultSet.next()){
                int blankNumber = resultSet.getInt("blankNumber");
                int dateIssued = resultSet.getInt("dateIssued");
                int dateValidated = resultSet.getInt("dateValidated");
                int ticketType = resultSet.getInt("ticketType");
                String destination = resultSet.getString("destination");
                int flightDate = resultSet.getInt("flightDate");
                int seatNumber = resultSet.getInt("seatNumber");
                int ticketPrice = resultSet.getInt("ticketPrice");
                String sellerName = resultSet.getString("sellerName");
                String customerName = resultSet.getString("customerName");
                int dateSold = resultSet.getInt("dateSold");
                Blank newBlank = new Blank(blankNumber, dateIssued, dateValidated,ticketType,destination,
                        flightDate,seatNumber,ticketPrice,sellerName,customerName,dateSold);
                blankArrayList.add(newBlank);
            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setUpUsers(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT userID, name, password, email, address, role FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            this.userArrayList = new ArrayList<User>();
            while (resultSet.next()){
                int userID = resultSet.getInt("userID");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String role = resultSet.getString("role");
                User newUser= new User(userID, name, password, email, address, role);
                userArrayList.add(newUser);
            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}