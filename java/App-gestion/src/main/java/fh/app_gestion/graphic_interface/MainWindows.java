package fh.app_gestion.graphic_interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindows{
    
    private static MainWindows currentInstance;
    public MainWindows(){
        currentInstance = this;
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

    public static void createAndShowGUI(){
        JFrame frame = new JFrame("Main page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel FirstLabel = new JLabel("hello ");
        JButton FirstButton = new JButton("test button");
        JTextField FirstField = new JTextField(10);
        JPanel FirstPanel = new JPanel();

        FirstPanel.add(FirstLabel);
        FirstPanel.add(FirstField);
        FirstPanel.add(FirstButton);

        FirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = FirstField.getText();
                System.out.println("bonjour " + name);
            }
        });

        frame.getContentPane().add(FirstPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}