import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomer {
    private Main main;
    private JFrame frame;
    private JScrollBar scrollBar1;
    private JButton buttonCurrentTickets;
    private JPanel panelCustomer;
    private JButton buttonLogOut;
    private JButton buttonBuy;

    public frameCustomer(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomer);
        frame.pack();
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCurrentTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerTickets(main);
            }
        });
        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerPurchase(main);
            }
        });
    }
}
