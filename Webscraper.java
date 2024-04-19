import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection.Response;
import java.io.IOException;
import java.util.*;


public class Webscraper {
	static ArrayList<Post> = new ArrayList();
	public static String processUrl(String url){
		url = url.replace("&", "%26");
		url = url.replaceFirst("www", "old");
		return url;
	}
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
	public static Document getDoc(String url, Map<String, String> cookies){
	try{	
        	//System.out.println("Got the connection");
        	Connection connection = Jsoup
            		.connect(url)
            		.referrer("http://www.google.com")
            		.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
            		.followRedirects(true)
            		.header("Accept-Language", "*")
            		.cookies(cookies);
		return connection.get();
	} catch(IOException e) {
		throw new RuntimeException(e);
	}
	}
  public static String getMainText(String url) {
      try {
        url = processUrl(url);
        Elements bodyElement;
        Document doc = getDoc(url, getCookies(url));
        bodyElement = doc.selectXpath("/html/body/div[3]/div[1]/div[1]/div[2]/div[2]/form/div/div");
	return bodyElement.text();
      } catch (IndexOutOfBoundsException e) {
        System.out.println("What in the hell happened");
      }
    System.out.println("Returning null. something went wrong");
    return null;
  }
  public static String getMainPostTitle(String url){
	  url = processUrl(url);
	  Elements postTitle;
	  Document doc = getDoc(url, getCookies(url));
	  postTitle = doc.selectXpath("/html/body/div[3]/div[1]/div[1]/div[2]/div[1]/p[1]/a");
	  //System.out.println(postTitle.text());
	  return postTitle.text();
  }
  public static void getAllRepliesRecur(String url, int childOf, int depth){
	url = processUrl(url);
	Document doc = getDoc(url, getCookies(url));
	String mainTextXpath = "/html/body/div[3]/div[2]/div[3]/div[";
	
		

  }
  public static void repliesRecurMethod(Document doc, String mainTextXpath, int childOf, int num1, int num2){
	mainTextXpath += num1 + "]/";
	String stringoo = mainTextXpath + "div[" + num2 + "]/form/;
	try{
		doc.selectXpath(stringoo);

  }

  public static Post getMainPost(String url){
	Post temp = new Post(getMainText(url), getMainPostTitle(url));
	return temp;
  }

  
}
//In the form of main text  \n poster name
//
///html/body/div[3]/div[2]/div[3]/div[1]/div[2]/form
///html/body/div[3]/div[2]/div[3]/div[1]/div[2]/p/a[2]
//
///html/body/div[3]/div[2]/div[3]/div[3]/div[2]/form
//
///html/body/div[3]/div[2]/div[3]/div[3]/div[3]/div/div[1]/div[2]/form
//
///html/body/div[3]/div[2]/div[3]/div[3]/div[3]/div/div[1]/div[3]/div/div[1]/div[2]/form/div
//
//
//
//
//
///html/body/div[3]/div[2]/div[3]/div[7]/div[2]/form
//
///html/body/div[3]/div[2]/div[3]/div[7]/div[3]/div/div[1]/div[2]/form
//
///html/body/div[3]/div[2]/div[3]/div[7]/div[3]/div/div[3]/div[2]/form
