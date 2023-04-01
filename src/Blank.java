import javax.swing.*;
import java.awt.event.ActionListener;

public class Blank {
    private final int blankNumber;
    private int dateIssued;
    private int dateValidated;
    private int ticketType;
    private String destination;
    private int flightDate;
    private int seatNumber;
    private int ticketPrice;
    private int sellerUserID;
    private int customerUserID;
    private int dateSold;
    private JButton button;
    public Blank(int blankNumber, int dateIssued, int dateValidated, int ticketType, String destination, int flightDate, int seatNumber, int ticketPrice, int sellerUserID, int customerUserID, int dateSold) {
        this.blankNumber = blankNumber;
        this.dateIssued = dateIssued;
        this.dateValidated = dateValidated;
        this.ticketType = ticketType;
        this.destination = destination;
        this.flightDate = flightDate;
        this.seatNumber = seatNumber;
        this.ticketPrice = ticketPrice;
        this.sellerUserID = sellerUserID;
        this.customerUserID = customerUserID;
        this.dateSold = dateSold;
        this.button = new JButton();
    }
    public int getBlankNumber() {return blankNumber;}
    public int getDateIssued() {return dateIssued;}
    public int getDateValidated() {return dateValidated;}
    public int getTicketType() {return ticketType;}
    public String getDestination() {return destination;}
    public int getFlightDate() {return flightDate;}
    public int getSeatNumber() {return seatNumber;}
    public int getTicketPrice() {return ticketPrice;}
    public int getSellerUserID() {return sellerUserID;}
    public int getCustomerUserID() {return customerUserID;}
    public int getDateSold() {return dateSold;}
    public JButton getButton() {return button;}
    public void setDateIssued(int dateIssued) {this.dateIssued = dateIssued;}
    public void setDateValidated(int dateValidated) {this.dateValidated = dateValidated;}
    public void setTicketType(int ticketType) {this.ticketType = ticketType;}
    public void setDestination(String destination) {this.destination = destination;}
    public void setFlightDate(int flightDate) {this.flightDate = flightDate;}
    public void setSeatNumber(int seatNumber) {this.seatNumber = seatNumber;}
    public void setTicketPrice(int ticketPrice) {this.ticketPrice = ticketPrice;}
    public void setSellerUserID(int sellerUserID) {this.sellerUserID = sellerUserID;}
    public void setCustomerUserID(int customerUserID) {this.customerUserID = customerUserID;}
    public void setDateSold(int dateSold) {this.dateSold = dateSold;}
    public void setButton(JButton button) {this.button = button;}
}
