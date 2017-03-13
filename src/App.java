import javax.swing.*;

public class App {
    public static void main(String[] args) {
        AppGUI appGUI = new AppGUI();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                appGUI.createAndShowGUI();
            }
        });
    }
}
