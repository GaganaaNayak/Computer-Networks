import java.util.Scanner;     // Import Scanner class for user input

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);   // Scanner object for input

        System.out.print("Enter the number of packets: ");
        int n = sc.nextInt();                  // Total number of packets

        System.out.print("Enter the bucket capacity: ");
        int bucket_cap = sc.nextInt();         // Max bucket capacity

        System.out.print("Enter the output rate: ");
        int outRate = sc.nextInt();            // How many packets leak per cycle

        int[] packet = new int[n];             // Array to store packet sizes

        for (int i = 0; i < n; i++) {          // Input packet sizes
            System.out.print("Enter the size of packet " + (i + 1) + ": ");
            packet[i] = sc.nextInt();
        }

        int bucket = 0;                        // Current bucket content

        System.out.println("\nCLOCK\tPACKET\tACCEPT\tSENT\tREMAINING");

        for (int t = 0; t < n; t++) {          // For each time cycle

            int incoming = packet[t];          // Incoming packet size
            int accept = 0, sent = 0;

            // Check if the packet can be accepted
            if (bucket + incoming > bucket_cap) {
                accept = -1;                   // Mark packet as dropped when it excedds bucket_cap
            } else {
                accept = incoming;             // Accept packet
                bucket += incoming;            // Add to bucket
            }

            // Sending data according to output rate
            if (bucket != 0) {
                if (bucket >= outRate) {
                    sent = outRate;          // If bucket has enough → leak exactly outRate
                    bucket -= outRate;
                } else {
                    sent = bucket;             // bucket has less → leak whatever is left
                    bucket = 0;
                }
            } else {
                sent = 0;                  //If empty → nothing leaks
            }

            // Print output per cycle
            if (accept == -1) {    //when a packet is dropped 
                System.out.println(t + "\t" + incoming + "\tdropped\t" +
                                   sent + "\t" + bucket);
            } else {
                System.out.println(t + "\t" + incoming + "\t" + accept +
                                   "\t" + sent + "\t" + bucket);
            }
        }

        sc.close();     // Close scanner
    }
}
