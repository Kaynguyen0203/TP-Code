import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorTicketSelection {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorTicketSelection;
    private JButton buttonSell;
    private JButton buttonGoBack;
    private JScrollBar scrollBar1;

    public frameTravelAdvisorTicketSelection(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorTicketSelection);
        frame.pack();
        buttonSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorSaleConfirmation(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorCustomers(main);
            }
        });
    }
}
