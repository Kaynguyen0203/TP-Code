import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerTravelAdvisors {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerTravelAdvisors;
    private JButton buttonGoBack;
    private JScrollBar scrollBar1;
    private JButton buttonReports;

    public frameOfficeManagerTravelAdvisors(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerTravelAdvisors);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
        buttonReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerIndividualReports(main);
            }
        });
    }
}
