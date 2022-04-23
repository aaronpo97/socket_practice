import java.io.*;
import java.net.*;

public class Client {

  // Initialize socket and input output streams

  private Socket socket = null;
  private DataInputStream input = null;
  private DataOutputStream out = null;

  // Create client method

  public Client(String address, int port) {
    // Try to establish a connection
    try {
      // Create a socket
      socket = new Socket(address, port);
      System.out.println("Connected to " + address + " on port " + port);
      // Take input from terminal
      input = new DataInputStream(System.in);
      out = new DataOutputStream(socket.getOutputStream());
    } catch (UnknownHostException unknownHostException) {
      System.out.println(unknownHostException);
    } catch (IOException ioException) {
      System.out.println(ioException);
    }
    // Initialize an empty string value for input
    String line = "";

    // Keep reading user input until "over" is input
    while (!line.toLowerCase().equals("over")) {
      try {
        line = input.readLine();
        out.writeUTF(line);
      } catch (IOException ioException) {
        System.out.println(ioException);
      }
    }

    try {
      input.close();
      out.close();
      socket.close();
    } catch (IOException ioException) {
      System.out.println(ioException);
    }
  }

  public static void main(String[] args) {
    Client client = new Client("127.0.0.1", 5000);
  }
}
