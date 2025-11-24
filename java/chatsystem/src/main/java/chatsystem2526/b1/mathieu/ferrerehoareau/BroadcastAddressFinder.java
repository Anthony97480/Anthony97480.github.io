package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Implement the method to create the broadcast address
 */
public class BroadcastAddressFinder {

    /**
     * Create and return the broadcast address of the network using the localhost address
     * @return InetAddress
     * @throws SocketException
     */
    public static InetAddress getBroadcastAddress() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue;
            }
            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                InetAddress broadcast = interfaceAddress.getBroadcast();
                if (broadcast != null) {
                    System.out.println("Broadcast address found: " + broadcast.getHostAddress());
                    return broadcast;
                }
            }
        }
        return null;
    }
}
