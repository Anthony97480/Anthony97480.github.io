package fh.app_gestion.app;

import fh.app_gestion.graphic_interface.loginWindow;


public class App_Gestion
{
    public static void main( String[] args ) throws Exception
    {
        loginWindow log_window = new loginWindow();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    log_window.createAndShowGUI();
                } catch (Exception e) {
                    System.err.println("error with the creation of the window: " + e.getMessage());
                }
            }
        });
    }
}
