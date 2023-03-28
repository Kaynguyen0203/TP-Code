import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerPurchase {
    private Main main;
    private JFrame frame;
    private JButton buttonPayWithCard;
    private JButton buttonPayWithCash;
    private JButton buttonGoBack;
    private JPanel panelCustomerPurchase;
    public frameCustomerPurchase(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerPurchase);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomer(main);
            }
        });
        buttonPayWithCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerCard(main);
            }
        });
        buttonPayWithCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerCash(main);
            }
        });
    }
}
