package CSCI3381Project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TweetCollection {
	
	private HashMap<Long, Tweet> TweetCollection;
	
	public TweetCollection(String fileName) {
		TweetCollection = new HashMap<Long, Tweet>();
		this.readIn(fileName);
	}

	public void readIn(String fileName) {
		int i = 0;
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String[] input = line.split(",");
				int polarity = Integer.parseInt(input[0]);
				long ID = Long.parseLong(input[1]);
				String user = input[2];
				String content = input[3];
				this.addTweet(new Tweet(polarity, ID, user, content));
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
					long ID = Long.parseLong(input[1]);
					String user = input[2];
					String content = input[3];
					this.addTweet(new Tweet(polarity, ID, user, content));
					i++;
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
		System.out.println("\n!---Read " + i + " Tweets From " +  fileName + ", TweetCollection Now Contains " + TweetCollection.size() + " Unique Tweets---!\n");

	}
	
	public void writeOut(String fileName) {
		try
		{
			long i = 0;
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter myOutfile = new BufferedWriter(fw);		
			Iterator<Entry<Long, Tweet>> twitterator = TweetCollection.entrySet().iterator();
			while(twitterator.hasNext()){
				HashMap.Entry<Long, Tweet> tweet = twitterator.next();
				myOutfile.write(tweet.getValue().getPolarity() + "," + tweet.getValue().getID() + "," + tweet.getValue().getUser() + "," + tweet.getValue().getContent() + "\n");
			}
			myOutfile.flush();
			myOutfile.close();
			System.out.println("\n!---Wrote " + i + " New Tweets To " +  fileName + ", " + fileName + " Now Contains " + TweetCollection.size() + " Unique Tweets---!\n");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n!---Did Not Save To " + fileName + "---!\n");
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
	
	public Tweet searchByID(long ID) {
		Tweet tweet = TweetCollection.get(ID);
		return tweet;
	}
	
	public String toString() {
		String toReturn = "";
		Iterator<Entry<Long, Tweet>> twitterator = TweetCollection.entrySet().iterator();
		int i = 0;
		while(twitterator.hasNext() && i < 100){
			HashMap.Entry<Long, Tweet> tweet = twitterator.next();
			toReturn += (tweet.getValue() + "\n");
			i++;
		}
		return toReturn;
	}
	
	public ArrayList<Tweet> searchByUser(String User) {	
		ArrayList<Tweet> userTweets = new ArrayList<Tweet>();
		Iterator<Entry<Long, Tweet>> twitterator = TweetCollection.entrySet().iterator();
		while(twitterator.hasNext()){
			HashMap.Entry<Long, Tweet> tweet = twitterator.next();
			if (tweet.getValue().getUser().equals(User)) {
				userTweets.add(tweet.getValue());
			}
		}
		return userTweets;
	}
	
	public ArrayList<Long> retriveAll(){
		ArrayList<Long> allIDs = new ArrayList<Long>();
		Iterator<Entry<Long, Tweet>> twitterator = TweetCollection.entrySet().iterator();
		while(twitterator.hasNext()){
			HashMap.Entry<Long, Tweet> tweet = twitterator.next();
			allIDs.add(tweet.getKey());
		}
		return allIDs;
 	}
	
//	public int predict(Tweet tweet) {
//		return 0;
//		
//	}
	
//	public double judgeAccuracy(TweetCollection tweets) {
//		return 0.0;
//	}
	
	
}
