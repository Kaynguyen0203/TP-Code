import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameOfficeManagerAllocatedBlanks {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerAllocatedBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerAllocatedBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocatedBlanks);
        frameOfficeManagerSystemStock.setUpLabels(panelSecondary);
        setUpButtons();
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameOfficeManagerSystemStock(main);
            }
        });
    }
    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints buttonConstraints = frameOfficeManagerSystemStock.setButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        for (int i=0; i<blankArrayList.size(); i++){
            buttonConstraints.gridy = i+1;
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            if (blank.getSellerUserID()!=0){
                JButton blankButton = blank.getButton();
                frameOfficeManagerSystemStock.setUpMoreLabels(labelConstraints, blank, panelSecondary);
                blankButton.setText("Reallocate");
                blankButton.setBackground(Color.GREEN);
                blankButton.setForeground(Color.BLACK);
                blankButton.setEnabled(true);
                panelSecondary.add(blankButton, buttonConstraints);
                if (blankButton.getActionListeners().length ==0){
                    blankButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            blankButton.removeActionListener(this);
                            new frameOfficeManagerAllocateBlank(main, blank);
                        }
                    });
                }
            }
        }
    }
}
