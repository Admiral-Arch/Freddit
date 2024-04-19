public class Post{
	private String mainText;
	private int id;
	static int count = 1;
	private int posterId;
	private int childOfId;
	private String title;


	public Post(String mainText, String title){
		this.mainText = mainText;
		id = count;
		posterId = 0;
		id = count;
		this.title = title;
		childOfId = 0;
		count++;
	}
	public Post(String mainText, int childOfId){
		this.mainText = mainText;
		id = count;
		count++;
	}
	
	public String getTitle(){
		return title;
	}
	public String getMainText(){
		return mainText;
	}
}
