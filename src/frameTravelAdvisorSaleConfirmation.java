import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorSaleConfirmation {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorSaleConfirmation;
    private JButton buttonSellTicket;
    private JButton buttonGoBack;

    public frameTravelAdvisorSaleConfirmation(Main main) {
        buttonSellTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorTicketSelection(main);
            }
        });
    }
}
