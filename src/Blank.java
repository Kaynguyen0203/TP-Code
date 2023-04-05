import javax.swing.*;
import java.awt.event.ActionListener;

public class Blank {
    private final int blankNumber;
    private int dateIssued;
    private int dateValidated;
    private int ticketType;
    private String destination;
    private String origin;
    private int seatNumber;
    private int ticketPrice;
    private int discountedTicketPrice;
    private int sellerUserID;
    private int customerUserID;
    private int dateSold;
    private JButton button;
    private String cashCard;
    private int commissionRate;
    public Blank(int blankNumber, int dateIssued, int dateValidated, int ticketType, String destination, String origin, int seatNumber, int ticketPrice, int discountedTicketPrice, int sellerUserID, int customerUserID, int dateSold, String cashCard, int commissionRate) {
        this.blankNumber = blankNumber;
        this.dateIssued = dateIssued;
        this.dateValidated = dateValidated;
        this.ticketType = ticketType;
        this.destination = destination;
        this.origin = origin;
        this.seatNumber = seatNumber;
        this.ticketPrice = ticketPrice;
        this.discountedTicketPrice = ticketPrice;
        this.sellerUserID = sellerUserID;
        this.customerUserID = customerUserID;
        this.dateSold = dateSold;
        this.cashCard = cashCard;
        this.commissionRate = commissionRate;
        this.button = new JButton();
    }
    public int getBlankNumber() {return blankNumber;}
    public int getDateIssued() {return dateIssued;}
    public int getDateValidated() {return dateValidated;}
    public int getTicketType() {return ticketType;}
    public String getDestination() {return destination;}
    public String getOrigin() {return origin;}
    public int getSeatNumber() {return seatNumber;}
    public int getTicketPrice() {return ticketPrice;}
    public int getDiscountedTicketPrice() {return discountedTicketPrice;}
    public int getSellerUserID() {return sellerUserID;}
    public int getCustomerUserID() {return customerUserID;}
    public int getDateSold() {return dateSold;}
    public JButton getButton() {return button;}
    public String getCashCard() {return cashCard;}
    public int getCommissionRate() {return commissionRate;}
    public void setDateIssued(int dateIssued) {this.dateIssued = dateIssued;}
    public void setDateValidated(int dateValidated) {this.dateValidated = dateValidated;}
    public void setTicketType(int ticketType) {this.ticketType = ticketType;}
    public void setDestination(String destination) {this.destination = destination;}
    public void setOrigin(String origin) {this.origin = origin;}
    public void setSeatNumber(int seatNumber) {this.seatNumber = seatNumber;}
    public void setTicketPrice(int ticketPrice) {this.ticketPrice = ticketPrice;}
    public void setDiscountedTicketPrice(int discountedTicketPrice) {this.discountedTicketPrice = discountedTicketPrice;}
    public void setSellerUserID(int sellerUserID) {this.sellerUserID = sellerUserID;}
    public void setCustomerUserID(int customerUserID) {this.customerUserID = customerUserID;}
    public void setDateSold(int dateSold) {this.dateSold = dateSold;}
    public void setButton(JButton button) {this.button = button;}
    public void setCashCard(String cashCard) {this.cashCard = cashCard;}
    public void setCommissionRate(int commissionRate) {this.commissionRate = commissionRate;}
}
