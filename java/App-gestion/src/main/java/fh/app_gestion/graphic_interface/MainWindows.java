package fh.app_gestion.graphic_interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainWindows{
    
    private static MainWindows currentInstance;
    private static Path log_file;
    public MainWindows(Path file){
        currentInstance = this;
        this.log_file = file;
    }

    /**
     * get and return current instance of the window
     * @return MainWindow
     */
    public static MainWindows getCurrentInstance(){
        return currentInstance;
    }

    /**
     * Reset the current instance of the window
     */
    public static void clearCurrentInstance(){
        currentInstance = null;
    }

    public static void createAndShowGUI() {
        try{
            String TextFile = Files.readString(log_file);

            JFrame frame = new JFrame("Main page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel LabelTask = new JLabel("Task: ");
            JLabel LabelDescription = new JLabel("Description: ");
            JLabel LabelStart = new JLabel("Start: ");
            JLabel LabelEnd = new JLabel("End: ");

            JTextPane paneFile = new JTextPane();
            paneFile.setContentType("text/html");
            paneFile.setEditable(false);
            paneFile.setText("<html><body style='font-family:Arial; font-size:14px;'>" + TextFile + "</body></html>");
                    
            JScrollPane Project_Log = new JScrollPane(paneFile);

            
            JTextField fieldTask = new JTextField(15);
            JTextField fieldDescription = new JTextField(30);
            JTextField fieldStart = new JTextField(7);
            JTextField fieldEnd = new JTextField(7);

            JButton ValidateButton = new JButton("Validate");
            //JButton DeleteButton = new JButton("Delete");

            JPanel Panel1 = new JPanel();
            JPanel Panel2 = new JPanel();

            Panel1.add(LabelTask);
            Panel1.add(fieldTask);
            Panel1.add(LabelDescription);
            Panel1.add(fieldDescription);
            Panel1.add(LabelStart);
            Panel1.add(fieldStart);
            Panel1.add(LabelEnd);
            Panel1.add(fieldEnd);
            Panel1.add(ValidateButton);

            Panel2.add(Project_Log);

            ValidateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        String new_task = ""
                        + "<b>Name:</b> " + fieldTask.getText() + "<br>"
                        + "<b>Description:</b> " + fieldDescription.getText() + "<br>"
                        + "<b>Start:</b> " + fieldStart.getText() + "<br>"
                        + "<b>End:</b> " + fieldEnd.getText() + "<br><br>";

                        Files.write(
                            log_file,
                            new_task.getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.APPEND
                        );
                        frame.dispose();
                        MainWindows window = new MainWindows(log_file);
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    window.createAndShowGUI();
                                    frame.dispose();
                                } catch (Exception e) {
                                    System.err.println("error with the creation of the window: " + e.getMessage());
                                }
                            }
                        });
                    } catch(IOException exc){
                        System.err.println("error, writing file " + exc.getMessage());
                    }
                }
            });

            frame.getContentPane().add(Panel1, BorderLayout.PAGE_START);
            frame.getContentPane().add(Project_Log, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        } catch(IOException e){
            System.err.println("error reading file " + e.getMessage());
        }
    }
}