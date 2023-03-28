import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminBackupView {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminBackupView;
    private JScrollBar scrollBar1;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton buttonInitiate;
    private JButton buttonGoBack;

    public frameSystemAdminBackupView(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminBackupView);
        frame.pack();
        buttonInitiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminCurrentBackups(main);
            }
        });
    }
}
