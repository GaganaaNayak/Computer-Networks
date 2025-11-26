01//Develop a program for congestion control using a leaky bucket algorithm.  
  
    import java.util.Scanner;     // Import Scanner class for user input
02  
03  public class LeakyBucket {
04  
05      public static void main(String[] args) {
06  
07          Scanner sc = new Scanner(System.in);   // Scanner object for input
08  
09          System.out.print("Enter the number of packets: ");
10          int n = sc.nextInt();                  // Total number of packets
11  
12          System.out.print("Enter the bucket capacity: ");
13          int bucket_cap = sc.nextInt();         // Max bucket capacity
14  
15          System.out.print("Enter the output rate: ");
16          int outRate = sc.nextInt();            // How many packets leak per cycle
17  
18          int[] packet = new int[n];             // Array to store packet sizes
19  
20          for (int i = 0; i < n; i++) {          // Input packet sizes
21              System.out.print("Enter the size of packet " + (i + 1) + ": ");
22              packet[i] = sc.nextInt();
23          }
24  
25          int bucket = 0;                        // Current bucket content
26  
27          System.out.println("\nCLOCK\tPACKET\tACCEPT\tSENT\tREMAINING");
28  
29          for (int t = 0; t < n; t++) {          // For each time cycle
30  
31              int incoming = packet[t];          // Incoming packet size
32              int accept = 0, sent = 0;
33  
34              // Check if the packet can be accepted
35              if (bucket + incoming > bucket_cap) {
36                  accept = -1;                   // Mark packet as dropped when it excedds bucket_cap
37              } else {
38                  accept = incoming;             // Accept packet
39                  bucket += incoming;            // Add to bucket
40              }
41  
42              // Sending data according to output rate
43              if (bucket != 0) {
44                  if (bucket >= outRate) {
45                      sent = outRate;          // If bucket has enough → leak exactly outRate
46                      bucket -= outRate;
47                  } else {
48                      sent = bucket;             // bucket has less → leak whatever is left
49                      bucket = 0;
50                  }
51              } else {
52                  sent = 0;                  //If empty → nothing leaks
53              }
54  
55              // Print output per cycle
56              if (accept == -1) {    //when a packet is dropped 
57                  System.out.println(t + "\t" + incoming + "\tdropped\t" +
58                                     sent + "\t" + bucket);
59              } else {
60                  System.out.println(t + "\t" + incoming + "\t" + accept +
61                                     "\t" + sent + "\t" + bucket);
62              }
63          }
64  
65          sc.close();     // Close scanner
66      }
67  }
