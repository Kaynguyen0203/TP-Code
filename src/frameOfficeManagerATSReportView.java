import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
/*
this class allows the office manager to see all the sales on a particular day as specified on the previous frame
the method sets up all blanks in rows under the 2 top labels
the labels are split up into interline and domestic sales
 */
public class frameOfficeManagerATSReportView {
    private Main main;
    private JFrame frame;
    private int date;
    private JPanel panelOfficeManagerATSReportView;
    private JButton buttonGoBack;
    private JPanel panelSecondary;
    private JPanel panelTertiary;
    private JLabel text1;
    private JLabel text2;

    public frameOfficeManagerATSReportView(Main main, int date){
        this.main = main;
        this.date = date;
        this.frame = main.getMainFrame();
        frame.setContentPane(panelOfficeManagerATSReportView);
        main.setUpBlankTopLabels(panelSecondary);
        main.setUpBlankTopLabels(panelTertiary);
        String actualDate = String.valueOf(date);
        String year = actualDate.substring(0,4);
        String month = actualDate.substring(4,6);
        String day = actualDate.substring(6,8);
        text1.setText("All Travel Advisor Interlines Sales on "+year+"/"+month+"/"+day);
        text2.setText("All Travel Advisor Domestic Sales on "+year+"/"+month+"/"+day);
        seeReport();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerATSReport(main);
            }
        });
    }

    private void seeReport(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        ArrayList<User> userArrayList = main.getUserArrayList();
        ArrayList<Blank> interlineList = new ArrayList<Blank>();
        ArrayList<Blank> domesticList = new ArrayList<Blank>();
        for (User user : userArrayList){
            if (user.getRole().equals("Travel Advisor")){
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
