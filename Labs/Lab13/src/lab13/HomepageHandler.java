package lab13;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.time.LocalDate;
import java.io.FileReader;
import java.io.BufferedReader;

//We NEED this import of httpserver for anything to work!
//Eclipse can be a lot buggier about auto-including this import,
//	so it's best to include it manually in your code.
import com.sun.net.httpserver.*;

public class HomepageHandler implements HttpHandler
{
    //Anything that implements HttpHandler MUST include this method.
    //We state "throws IOException" as a shortcut (maybe a bad one...) for not handling the possibility of an IOException properly.
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
	String method = exchange.getRequestMethod();
	OutputStream outputStream = exchange.getResponseBody();
	
	//The HomepageHandler can ONLY handle GET requests (no one should be posting data to this endpoint)
	if(method.equals("GET"))
	{
	    System.out.println(LocalDate.now().toString() + "_" + LocalTime.now().toString() + ": GET /");
	    //Read the appropriate html file so we can send its contents to the user when they visit this url.
	    BufferedReader br = new BufferedReader(new FileReader("pages/Homepage.html"));
	    String responseString = "";
	    String line = br.readLine();
	    while(line != null)
	    {
		responseString += line + "\n";
		line = br.readLine();
	    }

	    //We send back a response that has the header with response code 200 (a successful request)
	    exchange.sendResponseHeaders(200, responseString.length());
	    //We use the outputStream object to send the data to the user.
	    outputStream.write(responseString.getBytes());
	    
	    //We always flush and close the stream when we're done with it!
	    outputStream.flush();
	    outputStream.close();
	    System.out.println("\tResponse: Homepage.html (200)");
	}
	else
	{
	    //If someone requested something other than a GET request, we send them a response indicating this isn't supported (response code 404)
	    System.out.println(LocalDate.now().toString() + ": " + method + " /");
	    System.out.println("\tResponse: Unsupported operation (404)");
	    exchange.sendResponseHeaders(404, "Unsupported operation".length());
	    outputStream.write("Unsupported operation".getBytes());
	    outputStream.flush();
	    outputStream.close();
	}
    }
}
