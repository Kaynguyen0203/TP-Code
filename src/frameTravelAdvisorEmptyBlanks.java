import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorEmptyBlanks {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorEmptyBlanks;
    private JButton buttonValidate;
    private JButton buttonGoBack;
    private JScrollBar scrollBar1;

    public frameTravelAdvisorEmptyBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorEmptyBlanks);
        frame.pack();
        buttonValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorValidateBlank(main);
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisor(main);
            }
        });
    }
}
