import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminStaffList {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminStaffList;
    private JButton tempViewDetailsButton;
    private JScrollBar scrollBar1;
    private JButton goBackButton;

    public frameSystemAdminStaffList(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminStaffList);
        frame.pack();
        tempViewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminContactDetails(main);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdmin(main);
            }
        });
    }
}
