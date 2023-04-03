import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameCustomer {
    private Main main;
    private JFrame frame;
    private JButton buttonCurrentTickets;
    private JPanel panelCustomer;
    private JButton buttonLogOut;
    private JPanel panelSecondary;

    public frameCustomer(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomer);
        main.setUpBlankTopLabels(panelSecondary);
        setUpButtons();
        frame.pack();
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                for (ActionListener listener:buttonCurrentTickets.getActionListeners()){
                    buttonCurrentTickets.removeActionListener(listener);
                }
                new frameLogin(main);
            }
        });
        buttonCurrentTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                buttonCurrentTickets.removeActionListener(this);
                new frameCustomerTickets(main);
            }
        });
    }
    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        ArrayList<Blank> canBuyArrayList = new ArrayList<Blank>();
        for (Blank blank : blankArrayList){
            if (blank.getDateValidated()!=0 && blank.getCustomerUserID()==0){
                canBuyArrayList.add(blank);
            }
        }
        GridBagConstraints buttonConstraints = main.setBlankButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<canBuyArrayList.size(); i++){
            buttonConstraints.gridy =i+1;
            labelConstraints.gridy =i+1;
            Blank blank = canBuyArrayList.get(i);
            JButton blankButton = blank.getButton();
            main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
            blankButton.setText("Buy");
            blankButton.setEnabled(true);
            blankButton.setBackground(Color.GREEN);
            blankButton.setForeground(Color.BLACK);
            panelSecondary.add(blankButton, buttonConstraints);
            if (blankButton.getActionListeners().length ==0){
                blankButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.removeBlankActionListeners();
                        for (ActionListener listener:buttonCurrentTickets.getActionListeners()){
                            buttonCurrentTickets.removeActionListener(listener);
                        }
                        new frameCustomerPurchase(main, blank);
                    }
                });
            }
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
}
