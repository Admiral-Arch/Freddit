import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection.Response;
import java.io.IOException;
import java.util.*;


public class Webscraper {
	static ArrayList<Post> replies = new ArrayList<Post>();
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
  public static void getAllRepliesRecur(String url){
	url = processUrl(url);
	Document doc = getDoc(url, getCookies(url));
	getRepliesDown(doc, 0, 1, "/html/body/div[3]/div[2]/div[3]/div[", 1);
  }
  public static void getRepliesDown(Document doc, int childOf, int depth, String xPath, int num){
///html/body/div[3]/div[2]/div[3]/div[
///html/body/div[3]/div[2]/div[3]/div[1]/div[2]/form
		String temp = xPath + num + "]/div[2]/form";
		num = num + 2;
				
		if(!doc.selectXpath(temp).text().matches(".*[^a-z].*")){
			return;
		}
		//System.out.println(doc.selectXpath(temp).text());
		System.out.println(temp);
		Post p1 = new Post(doc.selectXpath(temp).text());
		replies.add(p1);
		getRepliesDown(doc, childOf, depth, xPath, num);
		xPath = xPath + (num - 2) + "]/div[3]/";
		getRepliesDeep(doc, childOf, depth + 1, xPath, 1);
  }
public static void getRepliesDeep(Document doc, int childOf, int depth, String xPath, int num){
///html/body/div[3]/div[2]/div[3]/div[3]/div[3]/div/div[1]/div[3]/div/div[1]/div[2]/form/
	  xPath = xPath + "div/div[";
	  String temp = xPath + num + "]/div[2]/form";
	 try{ 
	  System.out.println(temp);
	  System.out.println(doc.selectXpath(temp).text());
	  if(!doc.selectXpath(temp).text().matches(".*[^a-z].*")){
		  System.out.println("Nothing found here?");
		  return;
	  }
	 } catch(Error e){
		 return;
	 } catch(IllegalStateException e){
		 System.out.println("Illegal State Exception");
		return;
	 }
	  //	Post p1 = new Post(doc.selectXpath(temp).text());
	//	replies.add(p1);
	  xPath = xPath + num + "]/";
	  System.out.println("\n We got to the end wirhout returning" + temp);
	  getRepliesDown(doc, childOf, depth + 1, xPath, num);

  }
  public static Post getMainPost(String url){
	Post temp = new Post(getMainText(url), getMainPostTitle(url));
	return temp;
  }

  
}
//In the form of main text  \n poster name
//
///html/body/div[3]/div[2]/div[3]/div[1]/div[2]/p/a[2]
///html/body/div[3]/div[2]/div[3]/div[1]/div[2]/form
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
