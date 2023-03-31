import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class frameOfficeManagerSystemStock {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerSystemStock;
    private JButton buttonAllocate;
    private JButton buttonReallocateBlanks;
    private JButton buttonGoBack;
    private JPanel panelSecondary;

    public frameOfficeManagerSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSystemStock);
        setUpButtons();
        frame.pack();
        buttonReallocateBlanks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocatedBlanks(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
        //buttonAllocate.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        new frameOfficeManagerAllocateBlank(main);
        //    }
        //});
    }
    private void setUpButtons(){
        ArrayList<Blank> blankArrayList = main.getBlankArrayList();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonConstraints.gridx = 1;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        for (int i=0; i<blankArrayList.size(); i++){
            buttonConstraints.gridy = i;
            Blank blank = blankArrayList.get(i);
            JButton blankButton = blank.getButton();
            blankButton.setText("Allocate");
            panelSecondary.add(blankButton, buttonConstraints);

        }
    }
}
