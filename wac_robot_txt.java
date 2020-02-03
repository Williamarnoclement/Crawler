import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URLConnection;
import java.net.URL;
import java.lang.Object;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;
import java.net.URISyntaxException;



public class wac_robot_txt  {

	public  boolean wac_robot_txt() {



		try {
			URI uri = new URI(getUrl());
			String domain = uri.getHost();

			URLConnection connection = null;

			String content = null;

			try {

				connection =  new URL(domain+"/robots.txt").openConnection();

				Scanner scanner = new Scanner(connection.getInputStream());

				scanner.useDelimiter("\\Z");

				content = scanner.next();

				scanner.close();

			}catch ( Exception ex ) {

				System.out.println("lol");
				return false;

			}
			System.out.println("+--- robots.txt ---+");
			System.out.println(content);
			return true;

		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}




	}

	public String current_url;

	public void setUrl( String go )
	{
		current_url = go;
	}

	public String getUrl(){
		return current_url;
	}

}
