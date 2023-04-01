import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameOfficeManagerSystemStock {
    private final Main main;
    private final JFrame frame;
    private JPanel panelOfficeManagerSystemStock;
    private JButton buttonReallocateBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSystemStock);
        setUpTopLabels();
        setUpButtons();
        frame.pack();
        buttonReallocateBlanks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameOfficeManagerAllocatedBlanks(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameOfficeManager(main);
            }
        });
    }
    static GridBagConstraints setButtonConstraints(){
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonConstraints.gridx = 11;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        return buttonConstraints;
    }
    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints buttonConstraints = setButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        for (int i=0; i<blankArrayList.size(); i++){
            buttonConstraints.gridy = i+1;
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            JButton blankButton = blank.getButton();
            setUpDataLabels(labelConstraints, blank);
            blankButton.setText("Allocate");
            blankButton.setBackground(Color.GREEN);
            blankButton.setForeground(Color.BLACK);
            if (blank.getSellerUserID()!=0){
                blankButton.setEnabled(false);
            }
            panelSecondary.add(blankButton, buttonConstraints);
            if (blankButton.getActionListeners().length ==0){
                blankButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        blankButton.removeActionListener(this);
                        new frameOfficeManagerAllocateBlank(main, blank);
                    }
                });
            }
        }
    }

    public void setUpTopLabels(){
        setUpLabels(panelSecondary);
    }

    static void setUpLabels(JPanel panelSecondary) {
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

    private void setUpDataLabels(GridBagConstraints labelConstraints, Blank blank){
        setUpMoreLabels(labelConstraints, blank, panelSecondary);
    }

    static void setUpMoreLabels(GridBagConstraints labelConstraints, Blank blank, JPanel panelSecondary) {
        for (int i=0; i<11; i++) {
            JLabel col = new JLabel();
            col.setBorder(BorderFactory.createEmptyBorder(0,15,0,5));
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
}
