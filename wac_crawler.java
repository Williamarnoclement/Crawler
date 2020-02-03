import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URLConnection;
import java.net.URL;
import java.lang.Object;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class wac_crawler

{

  public static int power( int crawler_deep, String wac_url ) {

    if ( crawler_deep == 0 ) return 1;



    int i,j;

    ArrayList<String> returned_url;

    returned_url = crawling(wac_url);


    System.out.println(returned_url);


    for (i = 0; i < returned_url.size() ; i++) {


      String my = returned_url.get(i);


      if (my.contains("http://") || my.contains("https://")) {


        wac_robot_txt robot_checker = new wac_robot_txt();
        robot_checker.setUrl(my);

        for (j = 0; j< i ; j++) {
          //si on a deja visite la page
          if (my == returned_url.get(j)) {
            break;
          } else {
            power(crawler_deep-1, my ) ;
            System.out.println("crawl deep: " + crawler_deep);
          }

        }



      }



    }



    return crawler_deep;



  }





  public static ArrayList<String> crawling(String my_url){



    String content = null;

    String title = null;

    ArrayList<String> ar = new ArrayList<String>();



    URLConnection connection = null;

    try {

      connection =  new URL(my_url).openConnection();

      Scanner scanner = new Scanner(connection.getInputStream());

      scanner.useDelimiter("\\Z");

      content = scanner.next();

      scanner.close();

    }catch ( Exception ex ) {

      System.out.println("Impossible de parser " + my_url);

    }

    //System.out.println(content);



    Pattern pattern_title = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);

    Pattern pattern_links = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);



    Matcher matcher_title = pattern_title.matcher(content);

    while (matcher_title.find()) {

      title = matcher_title.group(1);

    }



    Matcher matcher_links = pattern_links.matcher(content);





    System.out.println("Titre page : " + title);
    System.out.println("Url page : " + my_url);

    System.out.println("+--- Liste des liens de la page ---+");



    while (matcher_links.find()) {



      ar.add(matcher_links.group(1));

      //matcher_links.group(1);

    }



    return ar;

  }



  public static void main(String[] args) {



    if (args.length == 0) {

      System.out.println("OU EST L' URL ?");
      System.exit(1);

    }


    System.out.println( power( 5, args[0]));



  }



}
