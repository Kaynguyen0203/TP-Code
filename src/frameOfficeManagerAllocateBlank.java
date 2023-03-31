import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerAllocateBlank {
    private Main main;
    private JFrame frame;
    private Blank blank;
    private JButton buttonGoBack;
    private JPanel panelOfficeManagerAllocateBlank;
    private JPanel panelSecondary;
    private JPanel panelTertiary;

    public frameOfficeManagerAllocateBlank(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocateBlank);
        setUpTopLabels();
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        setUpBlankLabels(labelConstraints, blank);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerSystemStock(main);
            }
        });
    }
    private void setUpTopLabels(){
        frameOfficeManagerSystemStock.setUpLabels(panelSecondary);
    }
    private void setUpBlankLabels(GridBagConstraints labelConstraints, Blank blank){
        frameOfficeManagerSystemStock.setUpMoreLabels(labelConstraints, blank, panelSecondary);
    }
}
