public class Post{
	private String mainText;
	private int id;
	static int count = 1;
	private int posterId;
	private int childOfId;

	public Post(String mainText){
		this.mainText = mainText;
		id = count;
		posterId = 0;
		id = count;
		childOfId = 0;
		count++;
	}
	public Post(String mainText, int childOfId){
		this.mainText = mainText;
		id = count;
		count++;
	}

	public String getMainText(){
		return mainText;
	}
}
