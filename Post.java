public class Post{
	private String mainText;
	private int id;
	static int count = 1;
	private int posterId;
	private int childOfId;
	private String title;
  private int depth;
  private String posterName;

	public Post(String mainText, String title){
		this.mainText = mainText;
		id = count;
		posterId = 0;
		id = count;
		this.title = title;
		childOfId = 0;
		count++;
	}
	public Post(String mainText, String title, int childOfId){
		this.mainText = mainText;
		id = count;
		count++;
	}
  public Post(String mainText, int childOfId, int depth, String posterName){
    this.mainText = mainText;
    this.childOfId = childOfId;
    this.depth = depth;
    this.id = count;
    if(posterName.equals("(0 children)")){
      posterName = "[deleted]";
    }
    this.posterName = posterName;
    count++;
  }
	public Post(String mainText){
		this.mainText = mainText;
	}
  public String toString(){
    return "Poster name " + posterName + "\nReply id: " + id + " Reply to: " + childOfId + "\n" + mainText; 
  }
  public String getName(){
    return posterName;
  }
	public String getTitle(){
		return title;
	}
	public String getMainText(){
		return mainText;
	}
	public int getId(){
		return id;
	}
  public int getParent(){
    return childOfId;
  }
}
