package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implement the User window, and the method to update it when a change occured in the user connected to the application
 */
public class MainWindow {

    private static MainWindow currentInstance;
    public MainWindow(){
        currentInstance = this;
    }
    /**
     * Get and return the current instance of the window of the client
     * @return MainWindow
     */
    public static MainWindow getCurrentInstance(){
        return currentInstance;
    }

    /**
     * Reset the current instance of the window of the client
     */
    public static void clearCurrentInstance(){
        currentInstance = null;
    }

    private static JPanel local_panel;
    private static main_clt local_clt;
    private static JFrame local_frame;
    
    /**
     * Update the current instance of the graphic window
     */
    public void Update_window(){
        UWindow(local_panel, local_clt, local_frame);
    }

    /**
     * Update the user window when a change occured in the user list (connection, disconnection or change login)
     * @param panel Local panel of the window witch contain the user list
     * @param clt Instance of the current client
     * @param frame Local frame of the window
     */
    private void UWindow(JPanel panel, main_clt clt, JFrame frame){
        ArrayList<User> User_list = clt.get_user_list();
        frame.getContentPane().remove(panel);
        JPanel new_panel = new JPanel();
        for (User user : User_list) {
            JLabel User_list_Label = new JLabel(user.getName());
            new_panel.add(User_list_Label);
        }
        frame.getContentPane().add(new_panel, BorderLayout.CENTER);
        frame.pack();
        frame.revalidate();
        frame.repaint();
        local_frame = frame;
        local_panel = new_panel;
    }

    /**
     * Create the user window in witch one the user can disconnect and change login with buttons. And see the user list of the user connected on the application
     * @param clt Instance of the current client
     */
    public static void createAndShowGUI(main_clt clt) {
        ChatSystem chat = new ChatSystem();
        User local_user = clt.get_local_user();

        // Create and set up the window.
        JFrame frame = new JFrame("Main Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel UserName_label = new JLabel("Current user: " + local_user.getName());

        JPanel User_panel = new JPanel();
        ArrayList<User> User_list = clt.get_user_list();
        for (User user : User_list) {
            JLabel User_list_Label = new JLabel(user.getName());
            User_panel.add(User_list_Label);
        }
        local_panel = User_panel;
        local_clt = clt;
        local_frame = frame;

        JPanel Main_panel = new JPanel(); 
        Main_panel.add(new JLabel("Disconnection:"));
        JButton DisconnectButton = new JButton("disconnect");
        Main_panel.add(DisconnectButton);
        JButton ChangeLoginButton = new JButton("change Login");
        Main_panel.add(ChangeLoginButton);

        DisconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                WindowLogin windowlog = new WindowLogin();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        chat.disconnect(local_user.getName(), clt);
                        windowlog.createAndShowGUI(clt);
                    } catch (Exception e) {
                        System.err.println("error, creating window: " + e.getMessage());
                    }
                }
            });
                frame.dispose();
                clearCurrentInstance();
            }
        });

        ChangeLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ChangeLogin changeLogin = new ChangeLogin();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        changeLogin.createAndShowGUI(clt);
                    }
                });
                frame.dispose();
                clearCurrentInstance();
            }
        });

        frame.getContentPane().add(UserName_label, BorderLayout.PAGE_START);
        frame.getContentPane().add(Main_panel, BorderLayout.PAGE_END);
        frame.getContentPane().add(User_panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }


}



