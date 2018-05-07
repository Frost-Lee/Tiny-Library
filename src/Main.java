import javax.swing.*;

public class Main {

    static Main sharedInstance;
    private JPanel currentPanel;
    private JFrame mainFrame;

    public static void main(String args[]) {
        Main main = new Main();
        main.setSharedInstance();
        main.mainFrame = new JFrame();
        main.mainFrame.setSize(800, 600);
        main.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.mainFrame.setTitle("Tiny Library");

        main.currentPanel = new LoginView();
        main.reloadView(main.currentPanel);
    }

    void reloadView(JPanel jPanel) {
        if (mainFrame != null) {
            removeCurrentView();
        }
        currentPanel = jPanel;
        assert mainFrame != null;
        mainFrame.add(currentPanel);
        mainFrame.setVisible(true);
    }

    private void removeCurrentView() {
        mainFrame.remove(currentPanel);
    }

    private void setSharedInstance() {
        Main.sharedInstance = this;
    }

}
