import javax.swing.*;

public class Main {
    private Main main;
    private JFrame mainFrame;
    public static void main(String[] args) {new Main();}

    public Main(){
        this.main = this;
        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new frameLogin(main);
    }

    public Main getMain(){return main;}
    public JFrame getMainFrame(){return mainFrame;}
}