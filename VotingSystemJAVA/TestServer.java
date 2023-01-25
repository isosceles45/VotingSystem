import java.io.*;
import java.net.*;
import java.util.*;

public class TestServer
{
	private static ServerSocket serverSocket;
	private static final int PORT = 8541;

	public static void main(String[] args) throws Exception {
		System.out.println("Opening port...\n");
		serverSocket = new ServerSocket(PORT);
		handleClient();
	}

	private static void handleClient() {
		Socket link = null; // Step 2
		try {
			link = serverSocket.accept();
			DataInputStream InFromClient = new DataInputStream(link.getInputStream());
			DataOutputStream OutToClient = new DataOutputStream(link.getOutputStream());
			int choice = InFromClient.readInt();

			int total;
			
			while (choice != 0) {
				int a = 0, b = 0, c = 0;

				char winner = 0;
				switch (choice) {
				case 1:
					a++;
					break;
				case 2:
					b++;
					break;
				case 3:
					c++;

					break;
				default:
					System.out.println("Invalid Option!");
				}

				if (a > b && a > c)
					winner = 'A';
				else if (b > a && b > c)
					winner = 'B';
				else
					winner = 'C';
				total = a + b + c;
				
				//Server returns the result here
				OutToClient.writeInt(total);
				OutToClient.writeInt(a);
				OutToClient.writeInt(b);
				OutToClient.writeInt(c);
				OutToClient.writeChar(winner);
				choice = InFromClient.readInt();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				System.out.println("Closing connection...");
				link.close();
			} catch (IOException ie) {
				System.out.println("Unable to close connection");
				System.exit(1);
			}
		}
	}
}