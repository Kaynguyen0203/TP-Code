import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameAirVia {
    private Main main;
    private JFrame frame;
    private JPanel panelAirVia;
    private JTextField fieldBlankAmount;
    private JButton buttonSend;
    private JButton buttonLogOut;

    public frameAirVia(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelAirVia);
        frame.pack();
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameLogin(main);
            }
        });
    }
}
