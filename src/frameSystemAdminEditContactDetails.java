import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminEditContactDetails {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminEditContactDetails;
    private JTextField fieldName;
    private JTextField fieldEmail;
    private JTextField fieldAddress;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JComboBox fieldRole;

    public frameSystemAdminEditContactDetails(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminEditContactDetails);
        frame.pack();
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminContactDetails(main);
            }
        });
    }
}
