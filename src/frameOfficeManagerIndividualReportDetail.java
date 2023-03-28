import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerIndividualReportDetail {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerIndividualReportDetail;
    private JButton buttonGoBack;

    public frameOfficeManagerIndividualReportDetail(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerIndividualReportDetail);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerIndividualReports(main);
            }
        });
    }
}
