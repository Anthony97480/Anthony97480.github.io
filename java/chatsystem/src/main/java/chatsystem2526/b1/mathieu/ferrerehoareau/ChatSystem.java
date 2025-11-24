package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Implement all the system function: connect, disconnect, changelogin, checklogin
 */
class ChatSystem{

    /**
     * Check if a username is already taken/invalid or not.
     * An invalide username is null, a particular expression or contain spaces or ":".
     * Return true if the login is valid, false if not.
     * @param login User login to test
     * @param clt Instance of the current client
     * @return boolean
     */
    private boolean Check_login(String login, main_clt clt){
        ArrayList<User> User_list = clt.get_user_list();
        if((login.equals("")) || (login.equals("CHANGELOGIN:")) || (login.equals("DISCONNECT:"))){
            return false;
        }
        String[] ary = login.split("");
        for(String caractere : ary){
            if((caractere.equals(":")) || (caractere.equals(" "))){
                return false;
            }
        }
        for (User user : User_list) {
            if(user.getName().equals(login)){
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a user is in a list or not.
     * Return true if the user is in the list, false if not.
     * @param user The user we search
     * @param list_rec The list in witch one we search a user
     * @return boolean
     */
    public boolean find_user(User user, ArrayList<User> list_rec){
        if(list_rec.isEmpty() || user == null || user.getName() == null){
            return false;
        }
        for (User user_rec : list_rec) {
            if(user.getName().equals(user_rec.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Connect a user to the application, getting the user list in the network amoung the other user already connected, check the validity of the username with the Check_login method and if the username is valide, broadcast the user in the network to update the list and set the local_user.
     * Return 0 if the connection succeeds and 1 if it failed
     * @param login Username of the client to connect
     * @param clt Instance of the current client
     * @return int
     * @throws IOException
     */
    public static int connection(String login, main_clt clt) throws IOException{
        ChatSystem chat = new ChatSystem();
        UDPBroadcaster Broadcast_list = new UDPBroadcaster(4980);
        String message = "getList";
        Broadcast_list.broadcast(message);
        try{
            Thread thread = Thread.currentThread();
            thread.sleep(1000);
        } catch(InterruptedException e){
            System.err.println("error, sleep failed: " + e.getMessage());
        }

        boolean result_login = chat.Check_login(login, clt);
        UDPBroadcaster Broadcast_user = new UDPBroadcaster(1752);
        if (result_login){
            User local_user = new User();
            local_user.setLogin(login);
            clt.set_local_user(local_user);
            clt.add_user(local_user);
            Broadcast_user.broadcast(login);
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Check the validity of a new username and change the current username for the new one and broadcast it on the network to update the list of the other user.
     * Return true if the login is valid and chance correctly, false if not.
     * @param oldLogin Current username of the user
     * @param newLogin New username of the user to check 
     * @param clt Current instance of the client
     * @return boolean
     * @throws IOException
     */
    public boolean changeUserLogin(String oldLogin, String newLogin, main_clt clt) throws IOException {
        if (Check_login(newLogin, clt)) {
            UDPBroadcaster broadcastChange = new UDPBroadcaster(1750);
            broadcastChange.broadcast("CHANGELOGIN:" + oldLogin + ":" + newLogin);

            try {
                Thread thread = Thread.currentThread();
                thread.sleep(1000);   
            } catch (Exception e) {
                System.err.println("error, sleep failed: " + e.getMessage());
            }
            clt.get_local_user().setLogin(newLogin);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Disconnect the user of the application and broadcast the username in the network to inform the deconnection to the others users.
     * @param login Username of the user deconnected
     * @param clt Current instance of the client
     * @throws IOException
     */
    public void disconnect(String login, main_clt clt) throws IOException{
        UDPBroadcaster Broadcast_user = new UDPBroadcaster(1751);
        Broadcast_user.broadcast("DISCONNECT:" + login);
        clt.reset_list();
        clt.reset_user();
    }
}