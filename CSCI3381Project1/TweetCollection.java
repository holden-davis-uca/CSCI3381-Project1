package CSCI3381Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TweetCollection {
	
	private HashMap<Integer, Tweet> TweetCollection;
	
	public TweetCollection() {
		TweetCollection = new HashMap<Integer, Tweet>();
	}
	
	public TweetCollection(String fileName) {
		TweetCollection = new HashMap<Integer, Tweet>();
		this.readTweets(fileName);
	}

	public void readTweets(String fileName) {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			int i = 1;
			while ((line = lineReader.readLine())!=null) {
				System.out.println("Going to add the " + i + "th tweet!");
				String[] input = line.split(",");
				int polarity = Integer.parseInt(input[0]);
				int ID = Integer.parseInt(input[1]);
				String user = input[2];
				String content = input[3];
				this.addTweet(new Tweet(polarity, ID, user, content));
				System.out.println("Just added the " + i + "th tweet!");
				i++;
				}
		} catch (Exception e) {
			System.err.println("\n!---File Error, Trying Different Read Type---!\n");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String[] input = line.split(",");
					int polarity = Integer.parseInt(input[0]);
					int ID = Integer.parseInt(input[1]);
					String user = input[2];
					String content = input[3];
					this.addTweet(new Tweet(polarity, ID, user, content));
					}
			} catch (Exception e2) {
				System.err.println("\n!---No Such File or Format Error---!\n");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("\\n!---Could Not Close Buffered Reader---!\\n");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("\\n!---Could Not Close Buffered Reader---!\\n");
				}
		}

	}
	
	public Tweet addTweet(Tweet tweet) {
		TweetCollection.put(tweet.getID(), tweet);
		return tweet;
	}
	
	public Tweet removeTweet(Tweet tweet) {
		TweetCollection.remove(tweet.getID());
		return tweet;
	}
	
	public Tweet searchByID(int ID) {
		Tweet tweet = TweetCollection.get(ID);
		return tweet;
	}
	
//	public Tweet searchByUser(String User) {
//		return tweet
//	}
	
//	public int[] retriveAll(){
//		int[] arr = null;
//		return arr;
// 	}
	
//	public int predict(Tweet tweet) {
//		return 0;
//		
//	}
	
//	public double judgeAccuracy(TweetCollection tweets) {
//		return 0.0;
//	}
	
//	public void readIn(String fileName) {
//		
//	}
	
//	public void writeOut(String fileName) {
//		
//	}
	
	
}
