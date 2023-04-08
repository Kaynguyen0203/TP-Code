import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
/*
this class is the frame to display all the interline and domestic sales on a particular day specified on the previous frame
these would be for the travel advisor chosen by the user
 */
public class frameOfficeManagerIndividualReport {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerIndividualReports;
    private JButton buttonGoBack;
    private JLabel text1;
    private JLabel text2;
    private JPanel panelTertiary;
    private JPanel panelSecondary;
    private User user;
    private int date;
    public frameOfficeManagerIndividualReport(Main main, User user, int date) {
        this.main = main;
        this.user = user;
        this.date = date;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerIndividualReports);
        main.setUpBlankTopLabels(panelSecondary);
        main.setUpBlankTopLabels(panelTertiary);
        text1.setText(user.getName()+"'s Interlines Sales");
        text2.setText(user.getName()+"'s Domestic Sales");
        seeReport();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerTravelAdvisors(main);
            }
        });
    }
    private void seeReport(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        ArrayList<Blank> interlineList = new ArrayList<Blank>();
        ArrayList<Blank> domesticList = new ArrayList<Blank>();
        frameTravelAdvisorReportDetails.setUpReportList(blankArrayList, interlineList, domesticList, user, date, main, panelSecondary, panelTertiary);
    }
}
