import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frameOfficeManagerATSReport {
    private Main main;
    private JFrame frame;
    private JPanel panelOfficeManagerATSReport;
    private JButton buttonGenerateReport;
    private JButton buttonGoBack;
    private JTextField fieldDate;

    public frameOfficeManagerATSReport(Main main) {
        this.main = main;
        frame = main.getMain().getMainFrame();
        frame.setContentPane(panelOfficeManagerATSReport);
        frame.pack();
        buttonGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ActionListener listener:buttonGenerateReport.getActionListeners()){
                    buttonGenerateReport.removeActionListener(listener);
                }
                new frameOfficeManager(main);
            }
        });
        buttonGenerateReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int date = Integer.parseInt(fieldDate.getText());
                    buttonGenerateReport.removeActionListener(this);
                    new frameOfficeManagerATSReportView(main, date);
                } catch(Exception a){
                    a.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Invalid input", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
