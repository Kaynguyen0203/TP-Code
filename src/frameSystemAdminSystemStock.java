import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        frameOfficeManagerSystemStock.setUpLabels(panelSecondary);
        setUpLabels();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdmin(main);
            }
        });
    }
    private void setUpLabels(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        for (int i=0; i<blankArrayList.size(); i++){
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            frameOfficeManagerSystemStock.setUpMoreLabels(labelConstraints, blank, panelSecondary);
        }
    }
}
