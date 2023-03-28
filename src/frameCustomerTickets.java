import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerTickets {
    private Main main;
    private JFrame frame;
    private JScrollBar scrollBar1;
    private JButton buttonGoBack;
    private JPanel panelCustomerTickets;
    private JButton buttonCancel;

    public frameCustomerTickets(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerTickets);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomer(main);
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerCancel(main);
            }
        });
    }
}
