import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
this class allows the system admin to be able to choose what he wants to do
there are buttons which allow him to:
see travel agent contact details, access system stock, receive blanks from air via and finally log out
 */
public class frameSystemAdmin {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdmin;
    private JButton buttonLogOut;
    private JButton buttonAccessSystemStock;
    private JButton buttonTravelAgentContactDetails;
    private JButton receiveBlanksFromAirViaButton;

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
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameLogin(main);
            }
        });
        receiveBlanksFromAirViaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminReceiveBlanks(main);
            }
        });
    }
}
