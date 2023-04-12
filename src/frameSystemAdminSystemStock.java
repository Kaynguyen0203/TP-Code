import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
this class allows the system admin to see the list of blanks currently in the system
the method sets up the rows of labels according to the blanks in the system under the top labels
 */
public class frameSystemAdminSystemStock {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminSystemStock;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameSystemAdminSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminSystemStock);
        main.setUpBlankTopLabels(panelSecondary);
        setUpLabels();

        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new frameSystemAdmin(main);
            }
        });
    }
    private void setUpLabels(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<blankArrayList.size(); i++){
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);
    }
}
