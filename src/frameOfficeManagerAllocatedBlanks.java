import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerAllocatedBlanks {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerAllocatedBlanks;
    private JScrollBar scrollBar1;
    private JButton buttonReallocate;
    private JButton buttonGoBack;

    public frameOfficeManagerAllocatedBlanks(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerAllocatedBlanks);
        frame.pack();
        buttonReallocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocateBlank(main);
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
