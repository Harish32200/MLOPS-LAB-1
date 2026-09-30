import java.net.InetAddress;

public class Ping {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java MyPing <hostname/IP>");
            return;
        }

        String host = args[0];

        try {
            InetAddress address = InetAddress.getByName(host);

            System.out.println("PING " + host +
                    " (" + address.getHostAddress() + ")");

            for (int i = 1; i <= 4; i++) {

                long start = System.currentTimeMillis();

                boolean reachable = address.isReachable(1000);

                long end = System.currentTimeMillis();

                if (reachable) {
                    System.out.println(
                        "Reply from " +
                        address.getHostAddress() +
                        ": icmp_seq=" + i +
                        " time=" + (end - start) + " ms"
                    );
                } else {
                    System.out.println(
                        "Request timeout for icmp_seq " + i
                    );
                }

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
