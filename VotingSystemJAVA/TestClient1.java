import java.net.*;
import java.io.*;
import java.util.*;

public class TestClient1 {
	private static InetAddress host;
	private static final int PORT = 8541;

	public static void main(String[] args) throws Exception {
		host = InetAddress.getLocalHost();
		accessServer();
	}

	static int voteRes;

	private static void accessServer() {

		Socket link = null;
		try {
			link = new Socket(host, PORT);

			DataInputStream FromServer = new DataInputStream(link.getInputStream());
			DataOutputStream ToServer = new DataOutputStream(link.getOutputStream());
			// Set up stream for keyboard entry
			Scanner userEntry = new Scanner(System.in);

			int a = 0, b = 0, c = 0, total, first, second, third;

			char winner;
			int tempa = 0, tempb = 0, tempc = 0, temptotal = 0;
			do {
				System.out.println("\n****VOTING SYSTEM****");
				System.out.println("Candidates");
				System.out.println("\n1: A");
				System.out.println("2: B");
				System.out.println("3: C");
				System.out.println("----------------------");
				System.out.print("Enter your choice: ");
				voteRes = userEntry.nextInt();

				if (voteRes == 1) {
					first = voteRes;
					ToServer.writeInt(first);
				} else if (voteRes == 2) {
					second = voteRes;
					ToServer.writeInt(second);
				} else {
					third = voteRes;
					ToServer.writeInt(third);
				}

				total = FromServer.readInt();
				temptotal = temptotal + total;
				a = FromServer.readInt();
				tempa = tempa + a;
				b = FromServer.readInt();
				tempb = tempb + b;
				c = FromServer.readInt();
				tempc = tempc + c;
				winner = FromServer.readChar();

				// getting the answer from the server
				System.out.println("\n****VOTING SYSTEM****");
				System.out.println("\nCandidates\tResult");
				System.out.println("\nA: A\t\t" + tempa);
				System.out.println("B: B\t\t" + tempb);
				System.out.println("C: C\t\t" + tempc);
				System.out.println("\nTOTAL VOTES: " + temptotal);
				System.out.println("\nWinner is: " + winner);

			} while (true);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("\n* Closing connection... *");
				link.close();
			} catch (IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
