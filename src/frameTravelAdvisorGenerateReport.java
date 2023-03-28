import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorGenerateReport {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorGenerateReport;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JScrollBar scrollBar1;

    public frameTravelAdvisorGenerateReport(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorGenerateReport);
        frame.pack();
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorIndividualReports(main);
            }
        });
    }
}
