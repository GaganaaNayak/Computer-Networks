01  import java.util.*;                                      // Import Scanner and utilities
02
03  public class RSA {                                      // RSA class begins
04
05      public static void main(String args[]) {            // Main method
06
07          String msg;                                     // To store input message
08          int pt[] = new int[100];                        // Plaintext ASCII array
09          int ct[] = new int[100];                        // Ciphertext array
10          int p, q, n, z, e, d;                           // RSA variables
11
12          Scanner in = new Scanner(System.in);            // Scanner object
13
14          // Input two prime numbers
15          do {
16              System.out.println("Enter the two large prime numbers for p and q:");
17              p = in.nextInt();                           // Input p
18              q = in.nextInt();                           // Input q
19          } while (prim(p) == 0 || prim(q) == 0);         // Repeat until both are prime
20
21          n = p * q;                                      // n = p × q
22          z = (p - 1) * (q - 1);                          // z = Φ(n) = (p-1)(q-1)
23
24          System.out.println("Value of n: " + n);         // Display n
25          System.out.println("Value of z is: " + z);      // Display φ(n)
26
27          // Key generation – Encryption key e
28          for (e = 2; e < z; e++) {                       // Start from 2 and find gcd(e,z)=1
29              if (gcd(e, z) == 1)
30                  break;                                  // Found e
31          }
32
33          System.out.println("Encryption key e is: " + e);
34          System.out.println("Public key is (e,n) => " + e + "," + n);
35
36          // Key generation – Decryption key d
37          for (d = 2; d < z; d++) {
38              if ((e * d) % z == 1)                        // e*d ≡ 1 (mod φ(n))
39                  break;                                  // Found d
40          }
41
42          System.out.println("Decryption key d is: " + d);
43          System.out.println("Private key is (d,n) => " + d + "," + n);
44
45          in.nextLine();                                   // Clear newline from buffer
46
47          System.out.println("Enter the message for encryption:");
48          msg = in.nextLine();                             // Read message as string
49
50          int mlen = msg.length();                         // Store message length
51
52          // Convert message to ASCII values
53          for (int i = 0; i < mlen; i++) {                  
54              pt[i] = msg.charAt(i);                       // Extract each character’s ASCII
55          }                                                //pt - plain text array storing ASCII value of each char in the array 
56
57          System.out.println("ASCII value of PT array is:");
58          for (int i = 0; i < mlen; i++)
59              System.out.println(pt[i]);                   // Print ASCII values
60
61          // Encryption: ct[i] = pt[i]^e mod n
62          System.out.println("Encryption: Cipher Text Obtained:");
63          for (int i = 0; i < mlen; i++) {
64              ct[i] = mult(pt[i], e, n);                   // Encrypt using modular exponent
65          }
66
67          for (int i = 0; i < mlen; i++) {
68              System.out.print(ct[i] + " ");               // Print ciphertext
69          }
70          System.out.println();
71
72          // Decryption: pt[i] = ct[i]^d mod n
73          System.out.println("Decryption: Plain Text Obtained:");
74          int ptt[] = new int[100];                        // Array to store decrypted ASCII
75
76          for (int i = 0; i < mlen; i++) {
77              ptt[i] = mult(ct[i], d, n);                  // Decrypt each value
78          }
79
80          for (int i = 0; i < mlen; i++) {
81              System.out.println(ptt[i] + " : " + (char) ptt[i]);
82          }
83      }
84
85      // Method to calculate GCD using recursion
86      public static int gcd(int x, int y) {
87          if (y == 0)
88              return x;                                    // Base case
89          else
90              return gcd(y, x % y);                        // Recursive call
91      }
92
93      // Method to check prime number
94      public static int prim(int num) {
95          int i;
96          for (i = 2; i <= num / 2; i++) {                 // Check divisibility
97              if (num % i == 0)
98                  return 0;                                // Not prime
99          }
100         return 1;                                        // Prime
101     }
102
103     // Modular exponentiation: (base^exp) mod n
104     public static int mult(int base, int exp, int n) {
105         int res = 1;
106         for (int j = 1; j <= exp; j++) {                 // Multiply exp times
107             res = ((res * base) % n);                    // Apply mod n each time
108         }
109         return res;
110     }
111 }
