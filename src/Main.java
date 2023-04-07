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
        buttonConstraints.gridx = 13;
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
        for (int i=0; i<14; i++){
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            labelConstraints.gridx = i;
            switch (i) {
                case 0 -> col.setText("|Blank Number|");
                case 1 -> col.setText("|Date Issued|");
                case 2 -> col.setText("|Date Validated|");
                case 3 -> col.setText("|Ticket Type|");
                case 4 -> col.setText("|Destination|");
                case 5 -> col.setText("|Origin|");
                case 6 -> col.setText("|Seat Number|");
                case 7 -> col.setText("|Ticket Price|");
                case 8 -> col.setText("|Local Price");
                case 9 -> col.setText("|Seller UserID|");
                case 10 -> col.setText("|Customer UserID|");
                case 11 -> col.setText("|Date Sold|");
                case 12 -> col.setText("|Cash/Card|");
                case 13 -> col.setText("|Commission Rate|");
            }
            panelSecondary.add(col, labelConstraints);
        }
    }
    public void setUpBlankDataLabels(GridBagConstraints labelConstraints, Blank blank, JPanel panelSecondary) {
        for (int i=0; i<14; i++) {
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            labelConstraints.gridx = i;
            String dateIssued = String.valueOf(blank.getDateIssued());
            String dateValidated = String.valueOf(blank.getDateValidated());
            long blankNumber = (blank.getTicketType()* 100000000L)+blank.getBlankNumber();
            if (blank.getDateValidated()!=0) {
                dateValidated = dateValidated.substring(0, 4) + "/" + dateValidated.substring(4, 6) + "/" + dateValidated.substring(6);
            }
            if (blank.getDateIssued()!=0) {
                dateIssued = dateIssued.substring(0, 4)+"/"+dateIssued.substring(4, 6)+"/"+dateIssued.substring(6);
            }
            int actualPrice = blank.getTicketPrice();
            int discountedPrice = blank.getDiscountedTicketPrice();
            if (actualPrice!=discountedPrice && discountedPrice !=0){
                actualPrice = discountedPrice;
            }
            int localPrice = blank.getTicketPriceLocal();
            int discountedPriceLocal = blank.getDiscountedTicketPriceLocal();
            if (localPrice!=discountedPriceLocal && discountedPriceLocal !=0){
                localPrice = discountedPriceLocal;
            }
            switch (i){
                case 0 -> col.setText(String.valueOf(blankNumber));
                case 1 -> col.setText(dateIssued);
                case 2 -> col.setText(dateValidated);
                case 3 -> col.setText(String.valueOf(blank.getTicketType()));
                case 4 -> col.setText(String.valueOf(blank.getDestination()));
                case 5 -> col.setText(String.valueOf(blank.getOrigin()));
                case 6 -> col.setText(String.valueOf(blank.getSeatNumber()));
                case 7 -> col.setText("$"+ actualPrice);
                case 8 -> col.setText(String.valueOf(localPrice));
                case 9 -> col.setText(String.valueOf(blank.getSellerUserID()));
                case 10 -> col.setText(String.valueOf(blank.getCustomerUserID()));
                case 11 -> col.setText(String.valueOf(blank.getDateSold()));
                case 12 -> col.setText(String.valueOf(blank.getCashCard()));
                case 13 -> col.setText(blank.getCommissionRate()+"%");
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
                    "origin, seatNumber, ticketPrice, discountedTicketPrice, ticketPriceLocal, discountedTicketPriceLocal, sellerUserID, customerUserID, dateSold, cashCard, commissionRate FROM blanks");
            ResultSet resultSet = preparedStatement.executeQuery();
            this.blankArrayList = new ArrayList<Blank>();
            while (resultSet.next()){
                int blankNumber = resultSet.getInt("blankNumber");
                int dateIssued = resultSet.getInt("dateIssued");
                int dateValidated = resultSet.getInt("dateValidated");
                int ticketType = resultSet.getInt("ticketType");
                String destination = resultSet.getString("destination");
                String origin = resultSet.getString("origin");
                int seatNumber = resultSet.getInt("seatNumber");
                int ticketPrice = resultSet.getInt("ticketPrice");
                int discountedTicketPrice = resultSet.getInt("discountedTicketPrice");
                int ticketPriceLocal = resultSet.getInt("ticketPriceLocal");
                int discountedTicketPriceLocal = resultSet.getInt("discountedTicketPriceLocal");
                int sellerUserID = resultSet.getInt("sellerUserID");
                int customerUserID = resultSet.getInt("customerUserID");
                int dateSold = resultSet.getInt("dateSold");
                String cashCard = resultSet.getString("cashCard");
                int commissionRate = resultSet.getInt("commissionRate");
                Blank newBlank = new Blank(blankNumber, dateIssued, dateValidated,ticketType,destination,
                        origin,seatNumber,ticketPrice, discountedTicketPrice, ticketPriceLocal, discountedTicketPriceLocal, sellerUserID,customerUserID,dateSold, cashCard, commissionRate);
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
                User newUser= new User(userID, name, password, email, address, role, discount);
                userArrayList.add(newUser);
            }
            preparedStatement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}