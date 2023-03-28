import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerCard {
    private Main main;
    private JFrame frame;
    private JButton buttonGoBack;
    private JButton buttonConfirm;
    private JTextField fieldCardNumber;
    private JPanel panelCustomerCard;
    public frameCustomerCard(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerCard);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameCustomerPurchase(main);
            }
        });
    }
}
