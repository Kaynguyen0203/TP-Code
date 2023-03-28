import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerSetDiscount {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerSetDiscount;
    private JRadioButton radioButtonFixed;
    private JRadioButton radioButtonFlex;
    private JTextField fieldDiscount;
    private JTextField fieldDiscountP;
    private JButton buttonConfirm;
    private JButton buttonGoBack;

    public frameOfficeManagerSetDiscount(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSetDiscount);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerCurrentCustomers(main);
            }
        });
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
