import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdmin {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdmin;
    private JButton buttonLogOut;
    private JButton buttonBackUpAndRestore;
    private JButton buttonAccessSystemStock;
    private JButton buttonTravelAgentContactDetails;


    public frameSystemAdmin(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdmin);
        frame.pack();
        buttonTravelAgentContactDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminStaffList(main);
            }
        });
        buttonAccessSystemStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminSystemStock(main);
            }
        });
        buttonBackUpAndRestore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminCurrentBackups(main);
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
