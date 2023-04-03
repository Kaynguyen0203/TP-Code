import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameTravelAdvisorEmptyBlanks {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorEmptyBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameTravelAdvisorEmptyBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorEmptyBlanks);
        main.setUpBlankTopLabels(panelSecondary);
        setUpButtons();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameTravelAdvisor(main);
            }
        });
    }
    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        ArrayList<Blank> toBeValidatedArrayList = new ArrayList<Blank>();
        for (Blank blank : blankArrayList){
            if (blank.getDateValidated()==0){
                toBeValidatedArrayList.add(blank);
            }
        }
        GridBagConstraints buttonConstraints = main.setBlankButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<toBeValidatedArrayList.size(); i++){
            buttonConstraints.gridy =i+1;
            labelConstraints.gridy =i+1;
            Blank blank = toBeValidatedArrayList.get(i);
            JButton blankButton = blank.getButton();
            main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
            blankButton.setText("Validate");
            blankButton.setBackground(Color.GREEN);
            blankButton.setForeground(Color.BLACK);
            panelSecondary.add(blankButton, buttonConstraints);
            if (blankButton.getActionListeners().length ==0){
                blankButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.removeBlankActionListeners();
                        new frameTravelAdvisorValidateBlank(main, blank);
                    }
                });
            }
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
}
