import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorValidateBlank {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorValidateBlank;
    private JButton buttonGenerateTicket;
    private JButton buttonGoBack;
    private JTextField fieldTicketType;
    private JTextField fieldDestination;
    private JTextField fieldFlightDate;
    private JTextField fieldSeatNumber;
    private JTextField fieldTicketPrice;

    public frameTravelAdvisorValidateBlank(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorValidateBlank);
        frame.pack();
        buttonGenerateTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frameTravelAdvisorEmptyBlanks(main);
            }
        });
    }
}
