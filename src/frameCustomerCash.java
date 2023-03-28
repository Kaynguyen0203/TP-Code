import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerCash {
    private Main main;
    private JFrame frame;
    private JButton buttonGoBack;
    private JButton buttonConfirm;
    private JPanel panelCustomerCash;
    public frameCustomerCash(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerCash);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerPurchase(main);
            }
        });
    }
}
