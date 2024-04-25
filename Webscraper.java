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
	static int counter = 0;
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
  System.out.println("Starting second recursive method");
  getRepliesDown(doc, 0, 1, "/html/body/div[3]/div[2]/div[7]/div[", 1);
  }
  public static void getRepliesDown(Document doc, int childOf, int depth, String xPath, int num){
///html/body/div[3]/div[2]/div[3]/div[
///html/body/div[3]/div[2]/div[3]/div[1]/div[2]/form
///html/body/div[3]/div[2]/div[7]/div[3]/div[3]/div/div[1]/div[2]/form
    String temp = "NOTHING HAHAHAHA";
  try{
      //System.out.println("We are in the second recursive function.");
      //System.out.println("In getRepliesDown farComment here is temp " + temp);
	  temp = xPath + num + "]/div[2]/form";
		num = num + 2;
				
		if(doc.selectXpath(temp).text().equals("")){
      //System.out.println("Nothing found at " + temp);
      return;
			
			
			
		}
		//System.out.println(doc.selectXpath(temp).text());
		//System.out.println(temp);
		Post p1 = new Post(doc.selectXpath(temp).text(), childOf, depth);
    int id = p1.getId();
		replies.add(p1);
		getRepliesDown(doc, childOf, depth, xPath, num);
    //if(farComment){

    //}
		xPath = xPath + (num - 2) + "]/div[3]/";
		getRepliesDeep(doc, id, depth + 1, xPath, 1);
  } catch(IllegalStateException E){
    System.out.println("Error, xpath is messed up here is temp, xpath " + temp + " , " + xPath);
	return;
  }
  }
public static void getRepliesDeep(Document doc, int childOf, int depth, String xPath, int num){
///html/body/div[3]/div[2]/div[3]/div[3]/div[3]/div/div[1]/div[3]/div/div[1]/div[2]/form/
///html/body/div[3]/div[2]/div[7]/div[1]/div[2]/form
	  xPath = xPath + "div/div[";
    //System.out.println("test1");
    String temp = "nothing NYAHHAHHAAHA";
    //if(farComment) {
      //  temp = xPath +  num + "]/div[1]/div[2]/form";
        //System.out.println("Test 2");
      //System.out.println("We are in the second recursive function.");
      //System.out.println(temp + " is the temp in farComment");
      //System.out.println(xPath + " is the xPath");
    //} else{

		  temp = xPath + num + "]/div[2]/form";
      //System.out.println("Test 3 : " + xPath);
    //}
	 try{ 
	  //System.out.println(temp);
    /*
	  if(!doc.selectXpath(temp).text().matches(".*[^a-z].*")){
		  System.out.println("Nothing found at " + temp);
		  if(farComment){
        System.out.println(testMethod(temp));
      }
		  return;
		  
	  }
    */

		Post p1 = new Post(doc.selectXpath(temp).text());
    //replies.add(p1);
	  //System.out.println(doc.selectXpath(temp).text());
	 } catch(Error e){
		 return;
	 } catch(IllegalStateException e){
		 System.out.println("Illegal State Exception: " + temp + " , " + xPath);
		return;
	 }
	  //	Post p1 = new Post(doc.selectXpath(temp).text());
	//	replies.add(p1);
	  //xPath = xPath + num + "]/";
    //System.out.println("Test 4 : " + xPath);
	  //System.out.println("\n We got to the end without returning" + temp);
    //
	  getRepliesDown(doc, childOf, depth + 1, xPath, num);

  }
  public static Post getMainPost(String url){
	Post temp = new Post(getMainText(url), getMainPostTitle(url));
	return temp;
  }
  public static String testMethod(String url){
	url = processUrl(url);
	Document doc = getDoc(url, getCookies(url));
  //System.out.println(doc);
	return doc.selectXpath("/html/body/div[3]/div[2]/div[7]/div[1]/div[2]/form").text();
  }
  public static void printDocument(String url){
    url = processUrl(url);
    System.out.println(getDoc(url, getCookies(url)));
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
