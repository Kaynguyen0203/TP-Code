import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameCustomerTickets {
    private Main main;
    private JFrame frame;
    private JButton buttonGoBack;
    private JPanel panelCustomerTickets;
    private JPanel panelSecondary;

    public frameCustomerTickets(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerTickets);
        main.setUpBlankTopLabels(panelSecondary);
        setUpTickets();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameCustomer(main);
            }
        });
    }
    /*
    this method sets up the panel with rows of labels and corresponding buttons next to them
    these buttons and labels are under the top labels
    the buttons allow you to cancel the ticket selected
     */
    private void setUpTickets(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        ArrayList<Blank> hasTicketArray = new ArrayList<Blank>();
        for (Blank blank : blankArrayList){
            if (main.getUser().getUserID() == blank.getCustomerUserID()){
                hasTicketArray.add(blank);
            }
        }
        GridBagConstraints buttonConstraints = main.setBlankButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<hasTicketArray.size(); i++){
            buttonConstraints.gridy =i+1;
            labelConstraints.gridy =i+1;
            Blank blank = hasTicketArray.get(i);
            JButton blankButton = blank.getButton();
            main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
            blankButton.setText("Refund");
            blankButton.setEnabled(true);
            blankButton.setBackground(Color.GREEN);
            blankButton.setForeground(Color.BLACK);
            panelSecondary.add(blankButton, buttonConstraints);
            if (blankButton.getActionListeners().length ==0){
                blankButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.removeBlankActionListeners();
                        new frameCustomerCancel(main, blank);
                    }
                });
            }
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
}
