import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminContactDetails {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminContactDetails;
    private JButton buttonChangeDetails;
    private JScrollBar scrollBar1;
    private JButton buttonGoBack;

    public frameSystemAdminContactDetails(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminContactDetails);
        frame.pack();
        buttonChangeDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminEditContactDetails(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminStaffList(main);
            }
        });
    }
}
