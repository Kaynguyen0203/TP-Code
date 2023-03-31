import javax.swing.*;

public class Blank {
    private final int blankNumber;
    private final int dateIssued;
    private final int dateValidated;
    private final int ticketType;
    private final String destination;
    private final int flightDate;
    private final int seatNumber;
    private final int ticketPrice;
    private final String sellerName;
    private final String customerName;
    private final int dateSold;
    private final JButton button;
    private final JLabel label;
    public Blank(int blankNumber, int dateIssued, int dateValidated, int ticketType, String destination, int flightDate, int seatNumber, int ticketPrice, String sellerName, String customerName, int dateSold) {
        this.blankNumber = blankNumber;
        this.dateIssued = dateIssued;
        this.dateValidated = dateValidated;
        this.ticketType = ticketType;
        this.destination = destination;
        this.flightDate = flightDate;
        this.seatNumber = seatNumber;
        this.ticketPrice = ticketPrice;
        this.sellerName = sellerName;
        this.customerName = customerName;
        this.dateSold = dateSold;
        this.button = new JButton();
        this.label = new JLabel();
    }
    public int getBlankNumber() {return blankNumber;}
    public int getDateIssued() {return dateIssued;}
    public int getDateValidated() {return dateValidated;}
    public int getTicketType() {return ticketType;}
    public String getDestination() {return destination;}
    public int getFlightDate() {return flightDate;}
    public int getSeatNumber() {return seatNumber;}
    public int getTicketPrice() {return ticketPrice;}
    public String getSellerName() {return sellerName;}
    public String getCustomerName() {return customerName;}
    public int getDateSold() {return dateSold;}
    public JButton getButton() {return button;}
    public JLabel getLabel() {return label;}
}
