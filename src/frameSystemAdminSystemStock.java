import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameSystemAdminSystemStock {
    private Main main;
    private JFrame frame;
    private JPanel panelSystemAdminSystemStock;
    private JScrollBar scrollBar1;
    private JButton buttonGoBack;

    public frameSystemAdminSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelSystemAdminSystemStock);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameSystemAdmin(main);
            }
        });
    }
}
