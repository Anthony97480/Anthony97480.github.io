package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;


/**
 * Implement the listener of the broadcaster and all the action to do according the message and the port of the broadcast.
 */
public class UDPListenerThread extends Thread {

private int LISTENER_PORT;
private DatagramSocket socket;
private main_clt clt;

/**
 * Constructor of the thread UDPListener
 * Set the diferent Listening port of the broadcast and those operation when received the information of the broadcast
 ** - Port 1750: inform the current user that an other user has change his usernameand then the current user update his user list with the change
 ** - Port 1751: inform the current user that an other user has disconnect from the application and then the current user update his user list with the change
 ** - Port 1752: inform the current user that an other user has connect to the application and then the current user update his user list with the change
 ** - Port 4980: inform the current user that an other user would like to get the user list of the network and then the current user send his user list on the network to the other user
 ** - Port 4981: the current user received the user list broadcasted on the network to update/create his current user list
 * @param Lport Listening port of the broadcast
 * @param clt Instance of the current client
 * @throws ClassNotFoundException
 */
public UDPListenerThread(int Lport, main_clt clt) throws ClassNotFoundException{
    this.LISTENER_PORT = Lport;
    this.clt = clt;

}

@Override
public void run(){
    try {
        socket = new DatagramSocket(LISTENER_PORT);

        socket.setBroadcast(true);

        while (!isInterrupted()) {
            if(LISTENER_PORT == 1750){
                byte[] User_buffer = new byte[1024];
                DatagramPacket user_packet = new DatagramPacket(User_buffer, User_buffer.length);
            
                socket.receive(user_packet);

                String User_message = new String(user_packet.getData(), 0, user_packet.getLength());
                InetAddress User_senderAddress = user_packet.getAddress();
                int User_senderPort = user_packet.getPort();

                System.out.println("Message reçu de " + User_senderAddress + ":" + User_senderPort + " -> " + User_message);

                if (User_message.startsWith("CHANGELOGIN:")) {
                    String[] parts = User_message.split(":");
                    String oldLogin = parts[1];
                    String newLogin = parts[2];

                    ArrayList<User> user_list = clt.get_user_list();
                    for (User user : user_list) {
                        if (user.getName().equals(oldLogin)) {
                            user.setLogin(newLogin);
                            System.out.println("User " + oldLogin + " changed to " + newLogin);
                            break; 
                        }
                    }
                    MainWindow mu = MainWindow.getCurrentInstance();
                    if (mu != null){
                        mu.Update_window();
                    }
                }
                
                } else if(LISTENER_PORT == 1751){
                    byte[] User_buffer = new byte[1024];
                    DatagramPacket user_packet = new DatagramPacket(User_buffer, User_buffer.length);
                    socket.receive(user_packet);

                    String User_message = new String(user_packet.getData(), 0, user_packet.getLength());
                    InetAddress User_senderAddress = user_packet.getAddress();
                    int User_senderPort = user_packet.getPort();

                    System.out.println("Message reçu de " + User_senderAddress + ":" + User_senderPort + " -> " + User_message);
                    if (User_message.startsWith("DISCONNECT:")) {


                        String[] parts = User_message.split(":");
                        String endip = parts[1];

                        Thread thread = Thread.currentThread();
                        thread.sleep(1000);
                        ArrayList<User> user_list = clt.get_user_list();
                        for (User user : user_list){
                            if (user.getName().equals(endip)){
                                clt.remove_user(user);
                                break;
                            }
                        }
                        MainWindow mu = MainWindow.getCurrentInstance();
                        if (mu != null){
                            mu.Update_window();
                        }
                    }
                } else if (LISTENER_PORT == 1752){
                    byte[] User_buffer = new byte[1024];
                    DatagramPacket user_packet = new DatagramPacket(User_buffer, User_buffer.length);

                    socket.receive(user_packet);

                    String User_message = new String(user_packet.getData(), 0, user_packet.getLength());
                    InetAddress User_senderAddress = user_packet.getAddress();
                    int User_senderPort = user_packet.getPort();

                    System.out.println("Message reçu de " + User_senderAddress + ":" + User_senderPort + " -> " + User_message);

                    User local_user = clt.get_local_user();
                    if(!local_user.getName().equals(User_message)){
                        User other_user = new User();
                        other_user.setLogin(User_message);
                        clt.add_user(other_user);
                    }
                    MainWindow mu = MainWindow.getCurrentInstance();
                    if (mu != null){
                        mu.Update_window();
                    }

            } else if(LISTENER_PORT == 4980){
                byte[] list_buffer = new byte[1024];
                DatagramPacket list_packet = new DatagramPacket(list_buffer, list_buffer.length);
                
                socket.receive(list_packet);

                String list_message = new String(list_packet.getData(), 0, list_packet.getLength());
                InetAddress list_senderAddress = list_packet.getAddress();
                int list_senderPort = list_packet.getPort();

                System.out.println("Message reçu de " + list_senderAddress + ":" + list_senderPort + " -> " + list_message);
                ArrayList<User> Local_UserList = clt.get_user_list();
                if((Local_UserList != null) && (!Local_UserList.isEmpty())){
                    UDPBroadcaster list_send_Broadcast = new UDPBroadcaster(4981);
                    list_send_Broadcast.broadcast_list(Local_UserList);
                }

            } else if(LISTENER_PORT == 4981){
                ChatSystem chat = new ChatSystem();

                byte[] buffet_list_rec = new byte[4096];
                DatagramPacket inPacket_list = new DatagramPacket(buffet_list_rec, buffet_list_rec.length);
                socket.receive(inPacket_list);
                ByteArrayInputStream bais = new ByteArrayInputStream(inPacket_list.getData(), 0, inPacket_list.getLength());
                ObjectInputStream ois = new ObjectInputStream(bais);
                ArrayList<User> rec_list = (ArrayList<User>) ois.readObject();

                InetAddress list_senderAddress = inPacket_list.getAddress();
                int list_senderPort = inPacket_list.getPort();

                System.out.println("Message reçu de " + list_senderAddress + ":" + list_senderPort + " -> " + rec_list);
                if(!chat.find_user(clt.get_local_user(), rec_list)){
                    for (User user : rec_list) {
                        clt.add_user(user);
                    }
                }
            }
        }
    } catch (IOException e) {
        if (socket != null && !socket.isClosed()) {
            System.err.println("Error in the listening thread UDP on the port " + LISTENER_PORT + ": " + e.getMessage());
            socket.close();
        }
    } catch (ClassNotFoundException exc){
        System.err.println("error while reading the user list: " + exc.getMessage());
    } catch (InterruptedException Inte){
        System.err.println("Error sleep failed: " + Inte.getMessage());
    }
}


    public void stopListening() {
        interrupt();
        if (socket != null) {
            socket.close();
        }
    }


}
