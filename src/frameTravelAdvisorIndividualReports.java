import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorIndividualReports {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorIndivReports;
    private JButton generateNewReportButton;
    private JButton goBackButton;
    private JButton tempViewDetailsButton;
    private JScrollBar scrollBar1;

    public frameTravelAdvisorIndividualReports(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorIndivReports);
        frame.pack();
        tempViewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorReportDetails(main);
            }
        });
        generateNewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorGenerateReport(main);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisor(main);
            }
        });
    }
}
