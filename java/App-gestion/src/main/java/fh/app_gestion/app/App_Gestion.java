package fh.app_gestion.app;

import fh.app_gestion.graphic_interface.MainWindows;

/**
 * Hello world!
 *
 */
public class App_Gestion
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println("Bonjour tous le monde !");

        MainWindows window = new MainWindows();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    window.createAndShowGUI();
                } catch (Exception e) {
                    System.err.println("error with the creation of the window: " + e.getMessage());
                }
            }
        });
    }
}
