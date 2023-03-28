import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminCurrentBackups {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminCurrentBackups;
    private JButton buttonGenerateBackup;
    private JButton buttonGoBack;
    private JButton buttonViewBackup;
    private JScrollBar scrollBar1;


    public frameSystemAdminCurrentBackups(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminCurrentBackups);
        frame.pack();
        buttonViewBackup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminBackupView(main);
            }
        });
        buttonGenerateBackup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdminGenerateBackup(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdmin(main);
            }
        });
    }
}
