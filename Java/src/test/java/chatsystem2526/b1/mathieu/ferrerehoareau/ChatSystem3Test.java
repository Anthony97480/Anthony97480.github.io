package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for changing username on the application
 */

public class ChatSystem3Test
{
    ChatSystem chat = new ChatSystem();
    /**
     * Test the method changeUserlogin witch change de local user login and broadcast it to the other user connected
     */
    @Test
    public void changeloginTest(){
        System.out.println("********************************change login test********************************");
        main_clt clt1 = new main_clt();
        main_clt clt2 = new main_clt();
        main_clt clt3 = new main_clt();

        try {
            UDPListenerThread Listener_changelogin = new UDPListenerThread(1750, clt1); //change login
            Listener_changelogin.start();

            User user1 = new User();
            User user2 = new User();
            User user3 = new User();

            user1.setLogin("user1");
            user2.setLogin("user2");
            user3.setLogin("user3");

            clt1.set_local_user(user1);
            clt2.set_local_user(user2);
            clt3.set_local_user(user3);

            clt1.add_user(user1);
            clt1.add_user(user2);
            clt1.add_user(user3);

            clt2.add_user(user1);
            clt2.add_user(user2);
            clt2.add_user(user3);

            clt3.add_user(user1);
            clt3.add_user(user2);
            clt3.add_user(user3);

           assertEquals(false, chat.changeUserLogin(user3.getName(), "user2", clt3));
           assertEquals(true, chat.changeUserLogin(user3.getName(), "user4", clt3));
           assertEquals("user4", clt3.get_local_user().getName());
           assertEquals(true, chat.find_user(user3, clt1.get_user_list()));
            Listener_changelogin.stopListening();
        } catch (IOException ioe) {
            System.err.println("Error reading userlist: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error listening changelogin: " + cnfe.getMessage());
        }

    }
}
