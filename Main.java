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
      Scanner sc = new Scanner(System.in);
      System.out.println("What url do you want to visit?");
      String url = sc.nextLine();
      //System.out.println("testing...");
	    //System.out.println(Webscraper.testMethod(url));
      //System.out.println("Test end...");
      Webscraper.getAllRepliesRecur(url);

      Post mainPost = Webscraper.getMainPost(url);
      //System.out.println("The title is: ");
      System.out.println("\n" + mainPost.getTitle() + "\n");

      System.out.println(mainPost.getMainText());
      System.out.println("\nHere are the replies:\n");
      Webscraper.sortReplies();
      for(int i = 0; i < Webscraper.replies.size(); i++){
        System.out.println(Webscraper.replies.get(i));
	      System.out.println("\n");
      }
      
      
    // end of main
}
}

//System.out.println(Webscraper.getMainText("https://old.reddit.com/r/webdev/comments/evj3ip/clients_who_dont_understand_what_lorem_ipsum_text/"));
/*
/html/body/shreddit-app/dsa-transparency-modal-provider/div/div[1]/div/main/shreddit-post/div[2]/div/div[1]
//main text
 */
