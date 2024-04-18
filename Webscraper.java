import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection.Response;
import java.io.IOException;
import java.util.*;

public class Webscraper {
	public static Map<String, String> getCookies(String url){
	try {
        return Jsoup
            .connect(url)
            .referrer("http://www.google.com")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
            .followRedirects(true)
            .header("Accept-Language", "*")
            .execute()
	    .cookies();
	} catch(IOException e){
		throw new RuntimeException(e);
	}
	}
  public static String getMainText(String url) {
      try {
        url = url.replace("&", "%26");
        Elements bodyElement;
        Map<String, String> cookies = getCookies(url);
        //System.out.println("Got the cookies, printing them out");
        //for(Map.Entry<String, String> entry : cookies.entrySet()) {
        //  System.out.println(entry.getKey() + " : " + entry.getValue());
        //}
        //System.out.println("End cookies");
        Connection connection = Jsoup
            .connect(url)
            .referrer("http://www.google.com")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
            .followRedirects(true)
            .header("Accept-Language", "*")
            .cookies(cookies);
        //System.out.println("Got the connection");
        Document doc = connection.get();
        //System.out.println("Got the doc");
        //Print out the doc for debugging
	//System.out.println(doc);
        bodyElement = doc.selectXpath("/html/body/div[3]/div[1]/div[1]/div[2]/div[2]/form/div/div");
        //print out the elements
	//System.out.println(bodyElement);
	//System.out.println("I should have the element?");
	//for(Element element : bodyElement){
        //System.out.println("We are in the loop");
        //Print out the elements
	//if(element != null){
        //    System.out.println(element.text());
        //} else{
	//    System.out.println("Null element? Look into it");
	//}
        //}
	//String retState = "";
	//System.out.println(bodyElement.children());
	//for(Element element : bodyElement){
	//      retState += element.text();
	//      retState += "\n";
	//}
        //return retState;
	return bodyElement.text();
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (IndexOutOfBoundsException e) {
        System.out.println("What in the hell happened");
      }
    System.out.println("Returning null. something went wrong");
    return null;
  }

  
}
