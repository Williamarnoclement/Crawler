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

  private  ArrayList<String> visited_url = new ArrayList<String>();

  private Connect pingr;

  public  int power( int crawler_deep, String wac_url ) {

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


        //si on a deja visite la page
        if (this.visited_url.contains(my)) {
          System.out.println("page déjà visité !-----------------------------------------------+" + my);
          /**int iniBLN = this.pingr.getIniBLN(my);
          System.out.println("bln =" + iniBLN);
          this.pingr.setNEWBLN(my, iniBLN + 1);**/
          break;
        } else if (!this.visited_url.contains(my)) {

          //si c'est bien un fichier HTML
          char moinsun = my.charAt(my.length() - 1);
          char moinsdeux = my.charAt(my.length() - 2);
          char moinstrois = my.charAt(my.length() - 3);
          char moinsquatre = my.charAt(my.length() - 4);
          char moinscinq = my.charAt(my.length() - 5);

          if (moinsun == 'l' && moinsdeux == 'm' && moinstrois == 't' && moinsquatre=='h' && moinscinq == '.') {
            visited_url.add(my);
            power(crawler_deep-1, my ) ;
            System.out.println("crawl deep: " + crawler_deep);
          } else if (moinsun == 'm' && moinsdeux == 't' && moinstrois == 'h' && moinsquatre=='.' ) {
            visited_url.add(my);
            power(crawler_deep-1, my ) ;
            System.out.println("crawl deep: " + crawler_deep);
          } else if (moinsun == 'p' && moinsdeux == 'h' && moinstrois == 'p' && moinsquatre=='.' ) {
            visited_url.add(my);
            power(crawler_deep-1, my ) ;
            System.out.println("crawl deep: " + crawler_deep);
          } else if (moinsun == 's' && moinsdeux == 's' && moinstrois == 'c' && moinsquatre=='.' ) {
            //ne fait rien si c'est un .css
          } else if (moinsun == 's' && moinsdeux == 'j' && moinstrois == '.') {
            //ne fait rien si c'est un .js
          } else if (my == "#" || my.contains("linkedin.com")) {
            //do nothing

          } else {
            visited_url.add(my);
            power(crawler_deep-1, my );
            System.out.println("crawl deep: " + crawler_deep);
          }

        }



      }



    }



    return crawler_deep;



  }





  public  ArrayList<String> crawling(String my_url){



    String content = null;

    String title = null;

    ArrayList<String> ar = new ArrayList<String>();



    URLConnection connection = null;

    try {

      connection =  new URL(my_url).openConnection();

      Scanner scanner = new Scanner(connection.getInputStream());
      System.out.println("Connexion ouverte sur le site " + my_url);
      scanner.useDelimiter("\\Z");

      content = scanner.next();

      scanner.close();

    }catch ( Exception ex ) {

      System.out.println("Impossible de parser " + my_url);
      //ar.add("nope");

    }

    //System.out.println(content);



    Pattern pattern_title = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);

    Pattern pattern_links = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);

    Matcher matcher_title;
    Matcher matcher_links = null;
    Boolean isMatcherBugging = false;

    try{
      matcher_title = pattern_title.matcher(content);

      while (matcher_title.find()) {

        title = matcher_title.group(1);

      }



      matcher_links = pattern_links.matcher(content);
    } catch ( Exception ex) {
      System.out.println("Matcher is bugging !");
      isMatcherBugging = true;
      title = my_url;
    }





    System.out.println("Titre page : " + title);
    System.out.println("Url page : " + my_url);

    this.pingr.newPage(my_url, "contenu",title );

    System.out.println("+--- Liste des liens de la page ---+");



    if (isMatcherBugging == false) {
      while (matcher_links.find()) {



        ar.add(matcher_links.group(1));

        //matcher_links.group(1);

      }
    }



    return ar;

  }



  public void initialisation(String letsgo){

    visited_url.add(letsgo);

    this.pingr = new Connect();
    this.pingr.initialisation();

    System.out.println( power( 5, letsgo));
    System.out.println("END ..");
    System.out.println(Arrays.toString(this.visited_url.toArray()));

  }



}
