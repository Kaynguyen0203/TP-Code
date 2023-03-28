import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerCancel {
    private Main main;
    private JFrame frame;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JPanel panelCustomerCancel;
    public frameCustomerCancel(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerCancel);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerTickets(main);
            }
        });
    }
}
