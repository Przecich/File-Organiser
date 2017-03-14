import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

public class AppGUI {
    JPanel pane;
    JButton confirmButton,targetButton;
    JTextField sourcePathFiled,targetPathField;
    JProgressBar progressBar;
    JRadioButton radioButton ;
    JFileChooser fc = new JFileChooser();
    JFileChooser fc2 = new JFileChooser();
    FileOperations fileOperations;


    private class GuiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Source")) {
                int returnVal = fc.showOpenDialog(pane);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    sourcePathFiled.setText(file.toPath().toString());
                    confirmButton.setEnabled(true);
                }
            }
            if (e.getActionCommand().equals("Target")) {
                int returnVal = fc2.showOpenDialog(pane);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc2.getSelectedFile();

                    targetPathField.setText(file.toPath().toString());
                    confirmButton.setEnabled(true);
                }
            }

            if(e.getActionCommand().equals("change")){
                if(radioButton.isSelected()) {
                    targetPathField.setEditable(true);
                    targetButton.setEnabled(true);

                }
                else {
                    targetPathField.setEditable(false);
                    targetButton.setEnabled(false);
                }

            }


            if (e.getActionCommand().equals("Organise")) {
                fileOperations=new FileOperations(Paths.get(sourcePathFiled.getText()));
                System.out.println(targetPathField.getText());
                if(radioButton.isSelected())
                    fileOperations.setTargetPath(Paths.get(targetPathField.getText()));

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
        createSwingComponents();
    }
    public void createAndShowGUI() {

        JFrame frame = new JFrame("File Organiser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 200));
        frame.add(pane);
        frame.pack();
        frame.setVisible(true);
    }
    private void createSwingComponents(){


        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Directories");
        pane = new JPanel();
        pane.setBorder(title);
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

        radioButton= new JRadioButton();
        radioButton.setActionCommand("change");
        radioButton.addActionListener(new GuiListener());
        c.weightx = 1;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(radioButton, c);

        targetPathField = new JTextField();
        targetPathField.setEnabled(false);
        targetPathField.setEditable(false);

        c.weightx = 10;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(targetPathField, c);


        targetButton = new JButton("Target");
        targetButton.addActionListener(new GuiListener());
        targetButton.setEnabled(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 3;
        pane.add(targetButton, c);


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
    private void initComponents(){

    }


}
