import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
this is the class for the frame that comes after logging into a role of office manager
there are buttons here that go to accessing system stock, accessing individual reports, setting discounts,
generating ATS reports and finally logout
 */
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
                main.removeBlankActionListeners();
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
                main.removeUserActionListeners();
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
                new frameLogin(main);
            }
        });
    }
}
