import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerSystemStock {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerSystemStock;
    private JScrollBar scrollBar1;
    private JButton buttonAllocate;
    private JButton buttonReallocateBlanks;
    private JButton buttonGoBack;

    public frameOfficeManagerSystemStock(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerSystemStock);
        frame.pack();
        buttonReallocateBlanks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocatedBlanks(main);

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManager(main);
            }
        });
        buttonAllocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameOfficeManagerAllocateBlank(main);
            }
        });
    }
}
