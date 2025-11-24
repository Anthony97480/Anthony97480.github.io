package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit test for sending and getting the user list on the network by broadcast
 */

public class BroadcastTest
{
    /**
     * Test the broadcast of the user list on the network
     */
    @Test
    public void get_user_listtest(){
        System.out.println("********************************get user list test********************************");
        try{
            main_clt clt1 = new main_clt();
            main_clt clt2 = new main_clt();
            ChatSystem chat = new ChatSystem();

            UDPListenerThread Listener_connect1 = new UDPListenerThread(1752, clt1); //connect
            Listener_connect1.start();
            UDPListenerThread Listener_list_send1 = new UDPListenerThread(4980, clt1); //send list
            Listener_list_send1.start();
            UDPListenerThread Listener_list_get2 = new UDPListenerThread(4981, clt2); // get list
            Listener_list_get2.start();


            User user1 = new User();
            user1.setLogin("User1");
            User user2 = new User();
            user2.setLogin("User2");
            User user3 = new User();
            user3.setLogin("User3");

            clt1.set_local_user(user1);
            clt1.add_user(user1);
            clt1.add_user(user2);
            chat.connection(user3.getName(), clt2);

            ArrayList<User> ref_list = new ArrayList<User>();
            ref_list.add(user1);
            ref_list.add(user2);
            ref_list.add(user3);
            ArrayList<User> User_list3 = clt2.get_user_list();
            System.out.println("Size of ref_list: " + ref_list.size());
            System.out.println("size of User_list: " + User_list3.size());

            assertEquals(true, ref_list.size() == User_list3.size());
            assertEquals(true, chat.find_user(user1, User_list3));
            assertEquals(true, chat.find_user(user2, User_list3));
            assertEquals(true, chat.find_user(user3, User_list3));

            Listener_connect1.stopListening();
            Listener_list_send1.stopListening();
            Listener_list_get2.stopListening();
            clt1.reset_list();
            clt2.reset_list();

        } catch (IOException IOe){
            System.err.println("Error while testing connection: " + IOe.getMessage());
        } catch (ClassNotFoundException CNFe){
            System.err.println("Error class not found: " + CNFe.getMessage());
        }
    }
}
