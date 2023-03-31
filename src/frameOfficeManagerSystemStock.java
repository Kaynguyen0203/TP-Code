import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class frameOfficeManagerSystemStock {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerSystemStock;
    private JButton buttonAllocate;
    private JButton buttonReallocateBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSystemStock);
        setUpButtons();
        frame.pack();
        buttonReallocateBlanks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocatedBlanks(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
        //buttonAllocate.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        new frameOfficeManagerAllocateBlank(main);
        //    }
        //});
    }
    private void setUpButtons(){
        setUpTopLabels();
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonConstraints.gridx = 11;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        for (int i=0; i<blankArrayList.size(); i++){
            buttonConstraints.gridy = i+1;
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            JButton blankButton = blank.getButton();
            setUpOtherLabels(labelConstraints, blank);
            blankButton.setText("Allocate");
            panelSecondary.add(blankButton, buttonConstraints);

        }
    }
    private void setUpTopLabels(){
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.gridy = 0;
        for (int i=0; i<11; i++){
            JLabel col = new JLabel();
            labelConstraints.gridx = i;
            switch (i) {
                case 0 -> col.setText("Blank Number|");
                case 1 -> col.setText("Date Issued|");
                case 2 -> col.setText("Date Validated|");
                case 3 -> col.setText("Ticket Type|");
                case 4 -> col.setText("Destination|");
                case 5 -> col.setText("Flight Date|");
                case 6 -> col.setText("Seat Number|");
                case 7 -> col.setText("Ticket Price|");
                case 8 -> col.setText("Seller Name|");
                case 9 -> col.setText("Customer Number|");
                case 10 -> col.setText("Date Sold");
            }
            panelSecondary.add(col, labelConstraints);
        }
    }
    private void setUpOtherLabels(GridBagConstraints labelConstraints, Blank blank){
        for (int i=0; i<11; i++) {
            JLabel col = new JLabel();
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
                case 8 -> col.setText(String.valueOf(blank.getSellerName()));
                case 9 -> col.setText(String.valueOf(blank.getCustomerName()));
                case 10 -> col.setText(String.valueOf(blank.getDateSold()));
            }
            panelSecondary.add(col, labelConstraints);
        }
    }
}
