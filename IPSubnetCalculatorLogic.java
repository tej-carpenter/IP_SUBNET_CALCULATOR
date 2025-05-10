import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class IPSubnetCalculatorLogic {

    // IPv4 Calculation Logic
    public static String[] calculateIPv4(String ipAddress, String subnetMask) throws UnknownHostException {
        try {
            InetAddress ip = InetAddress.getByName(ipAddress);
            InetAddress mask = InetAddress.getByName(subnetMask);

            byte[] ipBytes = ip.getAddress();
            byte[] maskBytes = mask.getAddress();

            byte[] networkBytes = new byte[4];
            byte[] broadcastBytes = new byte[4];

            for (int i = 0; i < 4; i++) {
                networkBytes[i] = (byte) (ipBytes[i] & maskBytes[i]); // Network address
                broadcastBytes[i] = (byte) (networkBytes[i] | ~maskBytes[i]); // Broadcast address
            }

            InetAddress networkAddress = InetAddress.getByAddress(networkBytes);
            InetAddress broadcastAddress = InetAddress.getByAddress(broadcastBytes);

            byte[] firstUsableBytes = networkBytes.clone();
            byte[] lastUsableBytes = broadcastBytes.clone();

            firstUsableBytes[3] += 1; // Increment the last byte for the first usable IP
            lastUsableBytes[3] -= 1; // Decrement the last byte for the last usable IP

            InetAddress firstUsableAddress = InetAddress.getByAddress(firstUsableBytes);
            InetAddress lastUsableAddress = InetAddress.getByAddress(lastUsableBytes);

            return new String[]{
                networkAddress.getHostAddress(),
                broadcastAddress.getHostAddress(),
                firstUsableAddress.getHostAddress() + " - " + lastUsableAddress.getHostAddress()
            };
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Invalid IP Address or Subnet Mask", "Error", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // IPv6 Calculation Logic
    public static String[] calculateIPv6(String ipv6Address, int prefixLength) throws UnknownHostException {
        try {
            if (prefixLength < 1 || prefixLength > 128) {
                JOptionPane.showMessageDialog(null, "Invalid Prefix Length. It must be between 1 and 128.", "Error", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Invalid Prefix Length");
            }

            InetAddress ipv6InetAddress = InetAddress.getByName(ipv6Address);
            byte[] ipv6Bytes = ipv6InetAddress.getAddress();
            byte[] networkBytes = ipv6Bytes.clone();

            int fullBytes = prefixLength / 8;
            int remainingBits = prefixLength % 8;

            for (int i = fullBytes; i < 16; i++) {
                if (i == fullBytes && remainingBits > 0) {
                    networkBytes[i] &= (byte) (0xFF << (8 - remainingBits));
                } else if (i > fullBytes) {
                    networkBytes[i] = 0;
                }
            }

            InetAddress networkAddress = InetAddress.getByAddress(networkBytes);

            // Calculate the last usable IP in the range
            byte[] broadcastBytes = networkBytes.clone();
            for (int i = fullBytes; i < 16; i++) {
                if (i == fullBytes && remainingBits > 0) {
                    broadcastBytes[i] |= (byte) ~(0xFF << (8 - remainingBits));
                } else if (i > fullBytes) {
                    broadcastBytes[i] = (byte) 0xFF;
                }
            }

            InetAddress lastUsableAddress = InetAddress.getByAddress(broadcastBytes);

            return new String[]{
                networkAddress.getHostAddress(),
                lastUsableAddress.getHostAddress()
            };
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Invalid IPv6 Address", "Error", JOptionPane.ERROR_MESSAGE);
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}