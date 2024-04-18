import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
      
      //System.out.println(Webscraper.getMainText("https://old.reddit.com/r/webdev/comments/evj3ip/clients_who_dont_understand_what_lorem_ipsum_text/"));
      System.out.println(Webscraper.getMainText("https://old.reddit.com/r/redditdev/comments/11c2pap/example_of_how_to_get_post_info_from_fullname/"));
    // end of main
}
}
/*
/html/body/shreddit-app/dsa-transparency-modal-provider/div/div[1]/div/main/shreddit-post/div[2]/div/div[1]
//main text
 */
