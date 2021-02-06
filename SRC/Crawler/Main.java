import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URLConnection;
import java.net.URL;
import java.lang.Object;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//javac -classpath "./mysql-connector-java-8.0.20.jar;." *.java
//java -classpath "./mysql-connector-java-8.0.20.jar;." Main  <url>

class Main

{

  public static void main(String[] args) {

    wac_crawler wac = new wac_crawler();

    if (args.length == 0) {

      System.out.println("OU EST L' URL ?");
      System.exit(1);

    }

    wac.initialisation(args[0]);





  }




}
