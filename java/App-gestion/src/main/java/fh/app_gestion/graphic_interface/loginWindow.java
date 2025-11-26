package fh.app_gestion.graphic_interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class loginWindow{
    
    private static loginWindow currentInstance;
    public loginWindow(){
        currentInstance = this;
    }

    /**
     * get and return current instance of the window
     * @return MainWindow
     */
    public static loginWindow getCurrentInstance(){
        return currentInstance;
    }

    /**
     * Reset the current instance of the window
     */
    public static void clearCurrentInstance(){
        currentInstance = null;
    }

    public static void createAndShowGUI() {
            JFrame frame = new JFrame("Login page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel LabelProject = new JLabel("Project name: ");
            JButton ValidateButton = new JButton("Valider");
            JTextField FieldProject = new JTextField(10);
            JPanel Panel1 = new JPanel();

            Panel1.add(LabelProject);
            Panel1.add(FieldProject);

            ValidateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        String Project_name = "C:/Users/tonyf/OneDrive/Bureau/Documents Anthony/travail/programmation/github/java/App-gestion/" + FieldProject.getText();
                        if(Project_name.equals("")){
                            Project_name = "default";
                        }
                        Path file = Paths.get(Project_name);
                        if(!Files.exists(file)){
                            Files.createFile(file);
                        }
                        MainWindows window = new MainWindows(file);
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
                        System.err.println("error, reading/creating file " + exc.getMessage());
                    }
                }
            });

            frame.getContentPane().add(Panel1, BorderLayout.PAGE_START);
            frame.getContentPane().add(ValidateButton, BorderLayout.AFTER_LAST_LINE);
            frame.pack();
            frame.setVisible(true);
    }
}