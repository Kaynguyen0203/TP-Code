import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {
    private final Main main;
    private final JFrame mainFrame;
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
    public void removeBlankActionListeners(){
        for (Blank blank: main.getBlankArrayList()){
            for (ActionListener listener: blank.getButton().getActionListeners()){
                blank.getButton().removeActionListener(listener);
            }
        }
    }
    public void removeUserActionListeners(){
        for (User user: main.getUserArrayList()){
            for (ActionListener listener: user.getButton().getActionListeners()){
                user.getButton().removeActionListener(listener);
            }
        }
    }
    public GridBagConstraints setBlankButtonConstraints(){
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonConstraints.gridx = 11;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        return buttonConstraints;
    }
    public GridBagConstraints setUserButtonConstraints(){
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonConstraints.gridx = 5;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        return buttonConstraints;
    }
    public void setUpUserDataLabels(GridBagConstraints labelConstraints, User user, JPanel panelTertiary) {
        for (int i=0; i<5; i++) {
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            labelConstraints.gridx = i;
            switch (i){
                case 0 -> col.setText(String.valueOf(user.getUserID()));
                case 1 -> col.setText(String.valueOf(user.getName()));
                case 2 -> col.setText(String.valueOf(user.getEmail()));
                case 3 -> col.setText(String.valueOf(user.getAddress()));
                case 4 -> col.setText(String.valueOf(user.getRole()));
            }
            panelTertiary.add(col, labelConstraints);
        }
    }
    public void setUpBlankTopLabels(JPanel panelSecondary) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.gridy = 0;

        for (int i=0; i<11; i++){
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            labelConstraints.gridx = i;
            switch (i) {
                case 0 -> col.setText("|Blank Number|");
                case 1 -> col.setText("|Date Issued|");
                case 2 -> col.setText("|Date Validated|");
                case 3 -> col.setText("|Ticket Type|");
                case 4 -> col.setText("|Destination|");
                case 5 -> col.setText("|Flight Date|");
                case 6 -> col.setText("|Seat Number|");
                case 7 -> col.setText("|Ticket Price|");
                case 8 -> col.setText("|Seller UserID|");
                case 9 -> col.setText("|Customer UserID|");
                case 10 -> col.setText("|Date Sold|");
            }
            panelSecondary.add(col, labelConstraints);
        }
    }
    public void setUpBlankDataLabels(GridBagConstraints labelConstraints, Blank blank, JPanel panelSecondary) {
        for (int i=0; i<11; i++) {
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            labelConstraints.gridx = i;
            switch (i){
                case 0 -> col.setText(String.valueOf(blank.getBlankNumber()));
                case 1 -> col.setText(String.valueOf(blank.getDateIssued()));
                case 2 -> col.setText(String.valueOf(blank.getDateValidated()));
                case 3 -> col.setText(String.valueOf(blank.getTicketType()));
                case 4 -> col.setText(String.valueOf(blank.getDestination()));
                case 5 -> col.setText(String.valueOf(blank.getFlightDate()));
                case 6 -> col.setText(String.valueOf(blank.getSeatNumber()));
                case 7 -> col.setText(String.valueOf(blank.getTicketPrice()));
                case 8 -> col.setText(String.valueOf(blank.getSellerUserID()));
                case 9 -> col.setText(String.valueOf(blank.getCustomerUserID()));
                case 10 -> col.setText(String.valueOf(blank.getDateSold()));
            }
            panelSecondary.add(col, labelConstraints);
        }
    }
    public void setUpUserTopLabels(JPanel panel) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.gridy = 0;
        for (int i=0; i<5; i++){
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            labelConstraints.gridx = i;
            switch (i) {
                case 0 -> col.setText("|User ID|");
                case 1 -> col.setText("|Name|");
                case 2 -> col.setText("|Email|");
                case 3 -> col.setText("|Address|");
                case 4 -> col.setText("|Role|");
            }
            panel.add(col, labelConstraints);
        }
    }
    private void setUpBlanks(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g30",
                    "in2018g30_a", "AqZonm86");
            PreparedStatement preparedStatement = con.prepareStatement("" +
                    "SELECT blankNumber, dateIssued, dateValidated, ticketType, destination, " +
                    "flightDate, seatNumber, ticketPrice, sellerUserID, customerUserID, dateSold FROM blanks");
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
                int sellerUserID = resultSet.getInt("sellerUserID");
                int customerUserID = resultSet.getInt("customerUserID");
                int dateSold = resultSet.getInt("dateSold");
                Blank newBlank = new Blank(blankNumber, dateIssued, dateValidated,ticketType,destination,
                        flightDate,seatNumber,ticketPrice,sellerUserID,customerUserID,dateSold);
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