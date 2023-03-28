import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminGenerateBackup {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminGenerateBackup;
    private JScrollBar scrollBar1;
    private JButton buttonGenerateBackup;
    private JButton buttonGoBack;

    public frameSystemAdminGenerateBackup(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminGenerateBackup);
        frame.pack();
        buttonGenerateBackup.addActionListener(new ActionListener() {
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
