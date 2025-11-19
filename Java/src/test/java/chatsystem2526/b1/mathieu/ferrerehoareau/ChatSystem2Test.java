package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the disconnection of user from the appliaction
 */

public class ChatSystem2Test
{
    ChatSystem chat = new ChatSystem();

    /**
     * Test the method changeUserlogin witch change de local user login and broadcast it to the other user connected
     */
    @Test
    public void disconnectTest(){
        System.out.println("********************************disconnection test********************************");
        main_clt clt1 = new main_clt();
        main_clt clt2 = new main_clt();
        main_clt clt3 = new main_clt();

        try {
            UDPListenerThread Listener_disconnect = new UDPListenerThread(1751, clt1); //disconnect
                Listener_disconnect.start();

            User user1 = new User();
            User user2 = new User();
            User user5 = new User();

            user1.setLogin("user1");
            user2.setLogin("user2");
            user5.setLogin("user5");

            clt1.set_local_user(user1);
            clt2.set_local_user(user2);
            clt3.set_local_user(user5);

            clt1.add_user(user1);
            clt1.add_user(user2);
            clt1.add_user(user5);

            clt2.add_user(user1);
            clt2.add_user(user2);
            clt2.add_user(user5);

            clt3.add_user(user1);
            clt3.add_user(user2);
            clt3.add_user(user5);

            chat.disconnect(user5.getName(), clt3);
            assertEquals(null, clt3.get_local_user());
            assertEquals(new ArrayList<User>(), clt3.get_user_list());
            Thread thread = Thread.currentThread();
            thread.sleep(2000);
            assertEquals(false, chat.find_user(user5, clt1.get_user_list()));

            Listener_disconnect.stopListening();
        } catch (IOException ioe) {
            System.err.println("Error reading userlist: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error listening disconnection: " + cnfe.getMessage());
        } catch (InterruptedException Inte){
            System.err.println("Error sleep failed: " + Inte.getMessage());
        }

    }
}
