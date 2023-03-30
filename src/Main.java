import javax.swing.*;
import java.util.ArrayList;

public class Main {
    private Main main;
    private JFrame mainFrame;
    private User user;
    private ArrayList<Blank> blankArrayList;
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
    public User getUser() {return user;}
    public void setUser(User newUser) {this.user = newUser;}
    public ArrayList<Blank> getBlankArrayList() {return blankArrayList;}
    public void setBlankArrayList(ArrayList<Blank> blankArrayList) {this.blankArrayList = blankArrayList;}
}