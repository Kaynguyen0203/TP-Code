import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameCustomerCancel {
    private Main main;
    private JFrame frame;
    private JButton buttonConfirm;
    private JButton buttonGoBack;
    private JPanel panelCustomerCancel;
    private JScrollPane panelSecondary;
    private Blank blank;
    public frameCustomerCancel(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelCustomerCancel);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                for(ActionListener listener: buttonConfirm.getActionListeners()){
                    buttonConfirm.removeActionListener(listener);
                }
                new frameCustomerTickets(main);
            }
        });
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmCancel();
            }
        });
    }
    private void confirmCancel(){

    }
}
