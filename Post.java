public class Post{
	private String mainText;
	private int id;
	static int count = 1;
	private int posterId;
	private int childOfId;

	public Post(String mainText){
		this.mainText = mainText;
	}
	public Post(String mainText, int childOfId){
		this.mainText = mainText;
		id = count;
		posterId = id;
		count++;
	}

	public String getMainText(){
		return mainText;
	}
}
