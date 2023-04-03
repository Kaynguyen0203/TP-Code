import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameTravelAdvisorValidateBlank {
    private Main main;
    private JFrame frame;
    private JPanel panelTravelAdvisorValidateBlank;
    private JButton buttonGenerateTicket;
    private JButton buttonGoBack;
    private JTextField fieldDestination;
    private JTextField fieldFlightDate;
    private JTextField fieldSeatNumber;
    private Blank blank;
    public frameTravelAdvisorValidateBlank(Main main, Blank blank) {
        this.main = main;
        this.blank = blank;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelTravelAdvisorValidateBlank);
        frame.pack();
        buttonGenerateTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateTicket();
            }
        });
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.removeBlankActionListeners();
                new frameTravelAdvisorEmptyBlanks(main);
            }
        });
    }
    private void generateTicket(){
        String destination = fieldDestination.getText();
        String flightDate = fieldFlightDate.getText();
        String seatNumber = fieldSeatNumber.getText();
        if (destination.isEmpty() || flightDate.isEmpty() || seatNumber.isEmpty()){
            JOptionPane.showMessageDialog(frame, "One or more empty fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
