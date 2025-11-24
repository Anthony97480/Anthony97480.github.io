package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the connection of new user on the application, find user on a list
 */

public class ChatSystem1Test
{
    ChatSystem chat = new ChatSystem();

    /**
     * This test try the connection of three user:username (user1:"user1", user2:"user2", user3:"user2" and user3:"User 3") and check the fact we can't connect if we use a username already taken or an invalide username
     * Test of the function connection and check login
     */
    @Test
    public void Connect_test(){
        System.out.println("********************************Connection test********************************");
        try{
            main_clt clt1 = new main_clt();
            main_clt clt2 = new main_clt();
            main_clt clt3 = new main_clt();

            UDPListenerThread Listener_connect1 = new UDPListenerThread(1752, clt1); //connect
            Listener_connect1.start();
            UDPListenerThread Listener_list_send1 = new UDPListenerThread(4980, clt1); //send list
            Listener_list_send1.start();
            UDPListenerThread Listener_list_get2 = new UDPListenerThread(4981, clt2); // get list
            Listener_list_get2.start();


            User user1 = new User();
            user1.setLogin("User1");
            assertEquals(0, chat.connection(user1.getName(), clt1));


            User user2 = new User();
            user2.setLogin("User2");
            assertEquals(0, chat.connection(user2.getName(), clt2));
            Listener_list_get2.stopListening();

            User user3 = new User();
            user3.setLogin("User2");
            UDPListenerThread Listener_list_get3 = new UDPListenerThread(4981, clt3); // get list
            Listener_list_get3.start();
            assertEquals(1, chat.connection(user3.getName(), clt3));
            user3.setLogin("User 3");
            assertEquals(1, chat.connection(user3.getName(), clt3));

            Listener_connect1.stopListening();
            Listener_list_send1.stopListening();
            Listener_list_get3.stopListening();
            clt1.reset_list();
            clt2.reset_list();
            clt3.reset_list();

        } catch (IOException IOe){
            System.err.println("Error while testing connection: " + IOe.getMessage());
        } catch (ClassNotFoundException CNFe){
            System.err.println("Error class not found: " + CNFe.getMessage());
        }
    }

    /**
     * Test the method find_user whitch determine if a user is in a list or not
     */
    @Test
    public void find_userTest(){
        System.out.println("********************************find_user test********************************");
        ArrayList<User> User_list = new ArrayList<User>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user1.setLogin("user1");
        user2.setLogin("user2");
        user3.setLogin("user3");
        user4.setLogin("user4");
        User_list.add(user1);
        User_list.add(user2);
        User_list.add(user3);

        assertEquals(false, chat.find_user(user4, User_list));
        assertEquals(true, chat.find_user(user2, User_list));
    }
}
