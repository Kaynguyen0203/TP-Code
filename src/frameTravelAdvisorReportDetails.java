import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorReportDetails {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorReportDetails;
    private JScrollBar scrollBar1;
    private JButton goBackButton;

    public frameTravelAdvisorReportDetails(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorReportDetails);
        frame.pack();
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorIndividualReports(main);
            }
        });
    }
}
