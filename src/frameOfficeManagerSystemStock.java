import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frameOfficeManagerSystemStock {
    private final Main main;
    private final JFrame frame;
    private JPanel panelOfficeManagerSystemStock;
    private JButton buttonReallocateBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;
    private JScrollPane scrollPane;

    public frameOfficeManagerSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSystemStock);
        main.setUpBlankTopLabels(panelSecondary);
        setUpButtons();
        frame.pack();
        buttonReallocateBlanks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameOfficeManagerAllocatedBlanks(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameOfficeManager(main);
            }
        });
    }

    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints buttonConstraints = main.setBlankButtonConstraints();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        for (int i=0; i<blankArrayList.size(); i++){
            buttonConstraints.gridy = i+1;
            labelConstraints.gridy = i+1;
            Blank blank = blankArrayList.get(i);
            JButton blankButton = blank.getButton();
            main.setUpBlankDataLabels(labelConstraints, blank, panelSecondary);
            blankButton.setText("Allocate");
            blankButton.setBackground(Color.GREEN);
            blankButton.setForeground(Color.BLACK);
            if (blank.getSellerUserID()!=0){
                blankButton.setEnabled(false);
            } else{
                blankButton.setEnabled(true);
            }
            panelSecondary.add(blankButton, buttonConstraints);
            if (blankButton.getActionListeners().length ==0){
                blankButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.removeBlankActionListeners();
                        new frameOfficeManagerAllocateBlank(main, blank);
                    }
                });
            }
        }
        labelConstraints.gridy+=1;
        panelSecondary.add(Box.createVerticalStrut(300), labelConstraints);

    }
}
