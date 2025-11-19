package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;


/**
 * Implement the broadcaster for the information on the network of the application
 */
public class UDPBroadcaster {

    private int BROADCAST_PORT;
    /**
     * Constructor of the broadcaster
     * @param Bport Port in witch one the broadcast is done
     */
    public UDPBroadcaster(int Bport){
        this.BROADCAST_PORT = Bport;

    }

    /**
     * Broadcast a string message on a specific port in the network
     * @param message String message to broadcast in the network
     */
    public void broadcast(String message) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);

            byte[] buffer = message.getBytes();
            
            // Adresse de broadcast pour le réseau local
            InetAddress broadcastAddress = BroadcastAddressFinder.getBroadcastAddress();
            

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddress, BROADCAST_PORT);
            
            socket.send(packet);
            System.out.println("Message broadcasté : " + message + " " + BROADCAST_PORT);

        } catch (IOException e) {
            System.err.println("Error when broadcasting: " + e.getMessage());
        }
    }

    /**
     * Broadcast a User list message on a specific port in the network
     * @param message ArrayList of User to broadcast in the network
     */
    public void broadcast_list(ArrayList<User> message) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(message);
            oos.close();

            byte[] buffer = baos.toByteArray();
            
            // Adresse de broadcast pour le réseau local
            InetAddress broadcastAddress = BroadcastAddressFinder.getBroadcastAddress();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddress, BROADCAST_PORT);
            
            socket.send(packet);
            System.out.println("Message broadcasté : " + message + " " + BROADCAST_PORT);

        } catch (IOException e) {
            System.err.println("Error when broadcasting: " + e.getMessage());
        }
    }
} 
    

