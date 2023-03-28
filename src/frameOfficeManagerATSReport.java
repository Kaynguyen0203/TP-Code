import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerATSReport {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerATSReport;
    private JButton buttonSend;
    private JButton buttonGoBack;

    public frameOfficeManagerATSReport(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerATSReport);
        frame.pack();
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
    }
}
