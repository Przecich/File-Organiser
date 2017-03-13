import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppGUI {
    JPanel pane;
    JButton confirmButton;
    JTextField sourcePathFiled;
    JProgressBar progressBar;
    JFileChooser fc = new JFileChooser();
    FileOperations fileOperations;

    public class GuiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("Source")) {
                int returnVal = fc.showOpenDialog(pane);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    fileOperations = new FileOperations(file.toPath());
                    sourcePathFiled.setText(file.toPath().toString());
                    confirmButton.setEnabled(true);
                }
            }

            if (e.getActionCommand().equals("Organise")) {
                progressBar.setIndeterminate(true);
                progressBar.setVisible(true);

                MovingFilesTask movingFilesTask = new MovingFilesTask(fileOperations);
                movingFilesTask.addActionListener(this);

                Thread thread = new Thread(movingFilesTask);
                thread.start();

            }
            if (e.paramString().equals("end") || e.getActionCommand().equals("end")) {
                progressBar.setIndeterminate(false);
                progressBar.setVisible(false);
            }
        }
    }

    public AppGUI() {
        initSwingComponents();
    }

    public void createAndShowGUI() {

        JFrame frame = new JFrame("File Organiser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 200));

        frame.add(pane);


        frame.pack();
        frame.setVisible(true);
    }
    private void initSwingComponents(){
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        pane = new JPanel();
        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Source directory: ");
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(label, c);


        sourcePathFiled = new JTextField();
        sourcePathFiled.setEnabled(false);
        c.weightx = 10;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(sourcePathFiled, c);

        button = new JButton("Source");
        button.addActionListener(new GuiListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(button, c);

        JLabel label2 = new JLabel("(Optional) Target directory: ");
        c.gridx = 1;
        c.gridy = 2;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 0;
        pane.add(label2, c);

        JRadioButton radioButton = new JRadioButton();
        c.weightx = 1;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(radioButton, c);

        JTextField textField2 = new JTextField();
        textField2.setEnabled(false);

        c.weightx = 10;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(textField2, c);


        button = new JButton("Target");
        button.setEnabled(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 3;
        pane.add(button, c);


        progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        progressBar.setVisible(false);
        c.gridy = 4;
        c.gridx = 1;
        c.insets = new Insets(10, 0, 0, 0);
        pane.add(progressBar, c);


        confirmButton = new JButton("Organise");
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(new GuiListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10, 5, 5, 5);  //top padding
        c.gridx = 2;       //aligned with button 2
        c.gridwidth = 1;   //2 columns wide

        c.gridy = 5;       //third row
        pane.add(confirmButton, c);

    }


}
