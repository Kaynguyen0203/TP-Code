import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManager {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManager;
    private JButton buttonLogOut;
    private JButton buttonGenerateATSReport;
    private JButton buttonSetDiscounts;
    private JButton buttonAccessSystemStock;
    private JButton buttonAccessIndividualReports;
    public frameOfficeManager(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManager);
        frame.pack();
        buttonAccessSystemStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerSystemStock(main);
            }
        });
        buttonAccessIndividualReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerTravelAdvisors(main);
            }
        });
        buttonSetDiscounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerCurrentCustomers(main);
            }
        });
        buttonGenerateATSReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerATSReport(main);
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
