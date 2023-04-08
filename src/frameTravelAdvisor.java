import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
this class allows the travel advisor to be able to choose what they want to do
the buttons include:
validating a blank, generating an individual report and logging out
 */
public class frameTravelAdvisor {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisor;
    private JButton buttonCreateTicketsFromBlank;
    private JButton buttonGenerateViewIndivReports;
    private JButton buttonLogOut;

    public frameTravelAdvisor(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisor);
        frame.pack();
        buttonCreateTicketsFromBlank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameTravelAdvisorEmptyBlanks(main);
            }
        });
        buttonGenerateViewIndivReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorGenerateReport(main);
            }
        });
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameLogin(main);
            }
        });
    }
}
