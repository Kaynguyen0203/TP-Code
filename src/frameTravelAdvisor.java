import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisor {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisor;
    private JButton buttonCreateTicketsFromBlank;
    private JButton buttonSellTickets;
    private JButton buttonGenerateViewIndivReports;
    private JButton buttonLogOut;

    public frameTravelAdvisor(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisor);
        frame.pack();
        buttonCreateTicketsFromBlank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorEmptyBlanks(main);
            }
        });
        buttonSellTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorCustomers(main);
            }
        });
        buttonGenerateViewIndivReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorIndividualReports(main);
            }
        });
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
