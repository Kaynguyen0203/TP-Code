import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorCustomers {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorCustomers;
    private JButton tempSellTicketButton;
    private JScrollBar scrollBar1;
    private JButton goBackButton;

    public frameTravelAdvisorCustomers(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorCustomers);
        frame.pack();
        tempSellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorTicketSelection(main);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisor(main);
            }
        });
    }
}
