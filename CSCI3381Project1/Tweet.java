package CSCI3381Project1;

public class Tweet {
	
	private int polarity;
	private long ID;
	private String user;
	private String content;
	
	public Tweet() {
		polarity = -1;
		ID = -1;
		user = "";
		content = "";
	}
	
	public Tweet(int polarity, long ID, String user, String content) {
		this.polarity = polarity;
		this.ID = ID;
		this.user = user;
		this.content = content;
	}

	public int getPolarity() {
		return polarity;
	}

	public long getID() {
		return ID;
	}

	public String getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}
	
	public String toString() {
		return (this.polarity + "," + this.ID + "," + this.user + "," + this.content);
	}
	
	public boolean equals(Tweet rhs) {
		if (this.getID() != rhs.getID()) {
			return false;
		}
		else return true;
	}
}
