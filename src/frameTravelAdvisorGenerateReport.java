import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
this class allows the travel advisor to be able to generate a report
they can do this by inputting a specific day then clicking on the generate button which sends the user to the next frame
 */
public class frameTravelAdvisorGenerateReport {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorIndivReports;
    private JButton generateNewReportButton;
    private JButton goBackButton;
    private JTextField fieldDate;

    public frameTravelAdvisorGenerateReport(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorIndivReports);
        frame.pack();
        generateNewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int date = Integer.parseInt(fieldDate.getText());
                    System.out.println("WW");
                    new frameTravelAdvisorReportDetails(main, date);
                    generateNewReportButton.removeActionListener(this);
                }catch(Exception a){

                    a.printStackTrace();
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(ActionListener listener:generateNewReportButton.getActionListeners()){
                    generateNewReportButton.removeActionListener(this);
                }
                new frameTravelAdvisor(main);
            }
        });
    }
}
