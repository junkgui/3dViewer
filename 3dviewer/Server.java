import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
    private ServerSocket ss;

    public Server(int port) {

	try {
	    ss = new ServerSocket(port);
	    Thread t = new Thread() {
		    public void run() {
			Vector output = new Vector();
			while(true){
			    try {
				Socket s = Server.this.ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String read = dis.readUTF();
				int index = read.indexOf("connect");
				if (index > -1) {
				    output.add(read.substring(0,read.indexOf("/")));
				} else {
				    update(read, output);
				}
				System.out.println(read);
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			}
		    }
		};
	    t.start();
	} catch (Exception e) {
	    e.printStackTrace(); 
	}
    }

    public void update(String out, Vector output) {
	Enumeration e = output.elements();
	while (e.hasMoreElements()) {
	    try {
		Socket soc = new Socket((String) e.nextElement(), 12345); 
		DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
		dos.writeUTF(out+"\n");
		soc.close();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }
    

    public static void main (String args[]) {
	try {
	    System.out.println(InetAddress.getLocalHost());
	    Server s = new Server(1234);
	    
			
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
