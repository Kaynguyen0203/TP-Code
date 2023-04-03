import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerPurchase {
    private Main main;
    private JFrame frame;
    private JButton buttonPayWithCard;
    private JButton buttonPayWithCash;
    private JButton buttonGoBack;
    private JPanel panelCustomerPurchase;
    private JPanel panelSecondary;
    private Blank blank;
    public frameCustomerPurchase(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerPurchase);
        main.setUpBlankTopLabels(panelSecondary);
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ActionListener listener:buttonPayWithCash.getActionListeners()){
                    buttonPayWithCash.removeActionListener(listener);
                }
                for (ActionListener listener:buttonPayWithCard.getActionListeners()){
                    buttonPayWithCard.removeActionListener(listener);
                }
                new frameCustomer(main);
            }
        });
        buttonPayWithCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPayWithCard.removeActionListener(this);
                for (ActionListener listener:buttonPayWithCash.getActionListeners()){
                    buttonPayWithCash.removeActionListener(listener);
                }
                new frameCustomerCard(main, blank);
            }
        });
        buttonPayWithCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPayWithCard.removeActionListener(this);
                for (ActionListener listener:buttonPayWithCard.getActionListeners()){
                    buttonPayWithCard.removeActionListener(listener);
                }
                new frameCustomerCash(main, blank);
            }
        });
    }
}
