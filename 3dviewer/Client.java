import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Client {
    InetAddress iadd;
    int port;
    TextArea ta;
    ServerSocket ss;

    public Client(InetAddress i, int port, TextArea txa) {
	ta = txa;
	iadd = i;
	this.port = port;
	try {
	    ss = new ServerSocket(12345);
	    Thread t = new Thread() {
		    public void run() {
			while(true){
			    try {
				Socket s = Client.this.ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String read = dis.readUTF();
				if (Client.this.ta != null) {
				    Client.this.ta.setText(Client.this.ta.getText()+read);
				} else {
				    System.out.println(read);
				}
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
	try {
	    send(InetAddress.getLocalHost().toString()+"connect");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setServer(InetAddress ia) {
	try {
	    iadd = ia;
	    send(InetAddress.getLocalHost().toString()+"connect");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    public void send(String str) {
	Socket s;
	try {
	    s = new Socket(iadd, port); 
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    dos.writeUTF(str);
	    s.close();
	} catch (Exception e) {
	    e.printStackTrace();
	} 

    }

    public static void main(String args[]) throws Exception  {
	Client c = new Client(InetAddress.getByName(args[0]), Integer.parseInt(args[1]), null);
	for (int i = 2; i < args.length; i++) {
	    c.send(args[i]);
	}
    }
}
