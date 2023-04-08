import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
/*
this class allows the travel advisor to be able to see the details of the individual report that the user has created
it will display everything sold on a specific day that the user has specified before this frame
the labels are split into interline and domestic sales
 */
public class frameTravelAdvisorReportDetails {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorGenerateReport;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JPanel panelSecondary;
    private JPanel panelTertiary;
    private JLabel text1;
    private JLabel text2;
    private int date;
    public frameTravelAdvisorReportDetails(Main main, int date) {
        this.main = main;
        this.date = date;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorGenerateReport);
        main.setUpBlankTopLabels(panelSecondary);
        main.setUpBlankTopLabels(panelTertiary);
        text1.setText(main.getUser().getName()+"'s Interlines Sales");
        text2.setText(main.getUser().getName()+"'s Domestic Sales");
        seeReport();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorGenerateReport(main);
            }
        });
    }
    private void seeReport(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        ArrayList<Blank> interlineList = new ArrayList<Blank>();
        ArrayList<Blank> domesticList = new ArrayList<Blank>();
        User user = main.getUser();
        setUpReportList(blankArrayList, interlineList, domesticList, user, date, main, panelSecondary, panelTertiary);
    }

    static void setUpReportList(ArrayList<Blank> blankArrayList, ArrayList<Blank> interlineList, ArrayList<Blank> domesticList, User user, int date, Main main, JPanel panelSecondary, JPanel panelTertiary) {
        for (Blank blank :blankArrayList){
            if(Arrays.asList(444, 440, 420, 451, 452).contains(blank.getTicketType()) && user.getUserID() ==
                    blank.getSellerUserID() && date == blank.getDateSold()){
                interlineList.add(blank);
            }
            if(Arrays.asList(201, 101).contains(blank.getTicketType()) && user.getUserID() == blank.getSellerUserID() &&
                    date == blank.getDateSold()){
                domesticList.add(blank);
            }
        }
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<interlineList.size(); i++) {
            labelConstraints.gridy = i + 1;
            Blank blank = interlineList.get(i);
            main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
        }
        GridBagConstraints labelConstraints2 = new GridBagConstraints();
        labelConstraints2.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<domesticList.size(); i++) {
            labelConstraints2.gridy = i + 1;
            Blank blank = domesticList.get(i);
            main.setUpBlankDataLabels(labelConstraints2, blank, panelTertiary);
        }

        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
        labelConstraints2.gridy+=1;
        panelTertiary.add(Box.createVerticalStrut(300), labelConstraints2);
    }
}
