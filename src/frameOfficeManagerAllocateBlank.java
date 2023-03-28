import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerAllocateBlank {
    private Main main;
    private JFrame frame;
    private JButton buttonAllocate;
    private JScrollBar scrollBar1;
    private JButton buttonGoBack;
    private JPanel panelOfficeManagerAllocateBlank;

    public frameOfficeManagerAllocateBlank(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocateBlank);
        frame.pack();
        buttonAllocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerSystemStock(main);
            }
        });
    }
}
