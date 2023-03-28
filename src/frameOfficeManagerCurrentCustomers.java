import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerCurrentCustomers {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerCurrentCustomers;
    private JButton buttonSetDiscount;
    private JButton buttonGoBack;
    private JScrollBar scrollBar1;

    public frameOfficeManagerCurrentCustomers(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerCurrentCustomers);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
        buttonSetDiscount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerSetDiscount(main);
            }
        });
    }
}
