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
    public static void main(String[] args) {new Main();}
    public Main(){
        this.main = this;
        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpBlanks();
        new frameLogin(main);
    }

    public Main getMain(){return main;}
    public JFrame getMainFrame(){return mainFrame;}
    public User getUser() {return user;}
    public void setUser(User newUser) {this.user = newUser;}
    public ArrayList<Blank> getBlankArrayList() {return blankArrayList;}
    public void setBlankArrayList(ArrayList<Blank> blankArrayList) {this.blankArrayList = blankArrayList;}
    private void setUpBlanks(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "SELECT blankNumber, dateIssued, dateValidated, ticketType, destination, " +
                    "flightDate, seatNumber, ticketPrice, sellerName, customerName, dateSold FROM blanks");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Blank> blankArrayList = new ArrayList<Blank>();
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
            main.setBlankArrayList(blankArrayList);
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}