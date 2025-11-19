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
 * Implement and create the login windows
 */
public class WindowLogin {
    
    /**
     * Create the login window and set the action of the user: connect button then open the user window if the connection is allowed.
     * @param clt Instance of the current client
     * @throws IOException
     */
    public static void createAndShowGUI(main_clt clt) throws IOException {
         ChatSystem chat = new ChatSystem();
        // Create and set up the window.
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(); 
        panel.add(new JLabel("Login:"));
        JTextField loginField = new JTextField(10); 
        panel.add(loginField);
        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e){
                try{
                    String login = loginField.getText();

                    int result = chat.connection(login, clt);

                    frame.dispose();
                    if(result == 0){
                        MainWindow main_window = new MainWindow();
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                main_window.createAndShowGUI(clt);
                            }
                        });
                    } else if(result == 1){
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    clt.reset_list();
                                    WindowLogin windowlog = new WindowLogin();
                                    windowlog.createAndShowGUI(clt);
                                    
                                    System.out.println("Failed to connect. login might be taken.");
                                    JOptionPane.showMessageDialog(frame, "Login is already taken or invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                                } catch (Exception e) {
                                    System.err.println("Error creating the window: " + e.getMessage());
                                }
                            }
                        });
                    }
                } catch (IOException exc){
                    System.out.println("error connection failed: " + exc.getMessage());
                }

            }
        });

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
