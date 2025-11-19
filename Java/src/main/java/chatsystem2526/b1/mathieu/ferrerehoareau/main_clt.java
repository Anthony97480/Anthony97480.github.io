package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.*;


/**
 * Implement the client, start the application and stock the local user list and the the username of the user
 */
public class main_clt {

        /**
         * Current user list of the user
         */
        private ArrayList<User> User_list = new ArrayList<User>();
        
        /**
         * Get and return the user list of the current client 
         * @return ArrayList<User>
         */
        public ArrayList<User> get_user_list(){
            return this.User_list;
        }

        /**
         * Add a user to the user list of the current client
         * @param user User to add to the list of the client
         */
        public void add_user(User user){
            this.User_list.add(user);
        }

        /**
         * Remove a user from the user list of the current client
         * @param user User to remove from the list of the client
         */
        public void remove_user(User user){
            this.User_list.remove(user);
        }

        /**
         * Reset the user list of the current client
         */
        public void reset_list(){
            this.User_list = new ArrayList<User>();
        }

        /**
         * User name of the current client
         */
        private User Local_user = new User();

        /**
         * Get and return the username of the current client
         * @return User
         */
        public User get_local_user(){
            return this.Local_user;
        }

        /**
         * Set the username of the current client.
         * @param user
         */
        public void set_local_user(User user){
            this.Local_user = user;
        }

        /**
         * Reset the username of the current client
         */
        public void reset_user(){
            this.Local_user = null;
        }

        /**
         * Start the listening of the different broadcasted port
         * @param clt Instance of the current client
         * @throws IOException
         */
        public static void log_srv(main_clt clt) throws IOException{
            try{ 
                UDPListenerThread Listener_changelogin = new UDPListenerThread(1750, clt); //change login
                Listener_changelogin.start();

                UDPListenerThread Listener_disconnect = new UDPListenerThread(1751, clt); //disconnect
                Listener_disconnect.start();

                UDPListenerThread Listener_connect = new UDPListenerThread(1752, clt); //connect
                Listener_connect.start();

                UDPListenerThread Listener_list_send = new UDPListenerThread(4980, clt); //send list
                Listener_list_send.start();

                UDPListenerThread Listener_list_get = new UDPListenerThread(4981, clt); // get list
                Listener_list_get.start();

            } catch(ClassNotFoundException e){
                System.err.println("error reading the user list: " + e.getMessage());
            }
        }


    /**
     * Thread to start the listening of the different broadcasted port
     */
    public static class ChatServer extends Thread {
        main_clt clt;

        /**
         * Constructor of the ChatServer thread
         * @param clt Instance of the current client
         */
        public ChatServer(main_clt clt){
            this.clt = clt;
        }

        @Override
        public void run(){
            try{
                log_srv(clt);
            }
                catch(IOException exc){
                    System.err.println("error with server thread: " + exc.getMessage());
                }
        }
    }


    public static void main(String[] args) throws IOException{
        main_clt clt = new main_clt();

        WindowLogin windowlog = new WindowLogin();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    windowlog.createAndShowGUI(clt);
                } catch (Exception e) {
                    System.err.println("error with the creation of the window: " + e.getMessage());
                }
            }
        });

        Thread thread_srv = new Thread(new ChatServer(clt));
        thread_srv.start();
    }
}