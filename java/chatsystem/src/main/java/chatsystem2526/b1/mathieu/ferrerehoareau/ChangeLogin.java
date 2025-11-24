package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Implement de windows to change login
 */
public class ChangeLogin {
    
    /**
     * Create and set the change login windows with a change login button and a cancel change button
     * @param clt Instance of the current client
     */
    public static void createAndShowGUI(main_clt clt) {
        // Create and set up the window.
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(); 
        panel.add(new JLabel("Login:"));
        JTextField loginField = new JTextField(10); 
        panel.add(loginField);
        JButton ChangeLoginButton = new JButton("Change Login");
        panel.add(ChangeLoginButton);
        JButton CancelButton = new JButton("Cancel Change");
        panel.add(CancelButton);

        ChangeLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    ChatSystem chat = new ChatSystem();
                    String newLogin = loginField.getText();
                    String oldLogin = clt.get_local_user().getName();
                    
                    if (chat.changeUserLogin(oldLogin, newLogin, clt)) {
                        System.out.println("Login changed successfully to: " + newLogin);
                        frame.dispose(); // Close the change login window
                        // Optionally, open the main window again or update it
                        MainWindow Main_Window = new MainWindow();
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                Main_Window.createAndShowGUI(clt);
                            }
                        });
                    } else {
                        System.out.println("Failed to change login. New login might be taken.");
                        JOptionPane.showMessageDialog(frame, "Login is already taken or invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException exc){
                    System.err.println(exc.getMessage());
                    JOptionPane.showMessageDialog(frame, "An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        CancelButton.addActionListener(new ActionListener() {
            @Override
                

            public void actionPerformed(ActionEvent e){
                frame.dispose();
                MainWindow Main_Window = new MainWindow();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Main_Window.createAndShowGUI(clt);
                    }
                });
            }
        });

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }


}


