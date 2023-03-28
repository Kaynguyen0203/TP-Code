import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerIndividualReports {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerIndividualReports;
    private JButton buttonDetails;
    private JButton buttonGoBack;
    private JScrollBar scrollBar1;

    public frameOfficeManagerIndividualReports(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerIndividualReports);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerTravelAdvisors(main);
            }
        });
        buttonDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerIndividualReportDetail(main);
            }
        });
    }
}
