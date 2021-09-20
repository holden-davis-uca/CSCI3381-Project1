package CSCI3381Project1;

public class Tweet {
	
	private int polarity;
	private int ID;
	private String user;
	private String content;
	
	public Tweet() {
		polarity = -1;
		ID = -1;
		user = "";
		content = "";
	}
	
	public Tweet(int polarity, int ID, String user, String content) {
		this.polarity = polarity;
		this.ID = ID;
		this.user = user;
		this.content = content;
	}

	public int getPolarity() {
		return polarity;
	}

	public int getID() {
		return ID;
	}

	public String getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}
	
//	public String toString() {
//		return "";
//	}
	
//	public boolean equals() {
//		return false;
//	}
}
