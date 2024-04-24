package lab13;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sun.net.httpserver.*;

public class CipherServer
{
    public HttpServer server;
    private String url;
    
    public CipherServer(String url, int port) throws IOException
    {
	this.url = url + ":" + port;
	server = HttpServer.create(new InetSocketAddress(url, port), 0);
	server.createContext("/", new  HomepageHandler());
	server.createContext("/encrypt", new  EncryptHandler());
	server.createContext("/decrypt", new  DecryptHandler());
    }
    
    public void start()
    {
	server.start();
	System.out.println(LocalDate.now().toString() + "_" + LocalTime.now().toString() + ": Cipher Server is now running at http://" + this.url);
    }
    
    public static void main(String[] args) throws IOException
    {
	CipherServer cs = new CipherServer("localhost", 8007);
	cs.start();
    }

}
