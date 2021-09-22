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
	
	public HashMap<String, ArrayList<Integer>> createPredictionData(){
		HashMap<String, ArrayList<Integer>> allWords = new HashMap<String, ArrayList<Integer>>();		
		Iterator<Entry<Long, Tweet>> twitterator = TweetCollection.entrySet().iterator();
		while(twitterator.hasNext()){
			HashMap.Entry<Long, Tweet> tweet = twitterator.next();
			String[] stufftobreak = tweet.getValue().getContent().split(" ");
			for (String thing : stufftobreak) {
				if (allWords.containsKey(thing)){
					allWords.get(thing).add(tweet.getValue().getPolarity());
				}
				else {
					ArrayList<Integer> newAListInt = new ArrayList<Integer>();
					newAListInt.add(tweet.getValue().getPolarity());
					allWords.put(thing, newAListInt);
				}
			}
		}
		return allWords;
	}
	
	public int predict(Tweet predictionTweet, HashMap<String, ArrayList<Integer>> predictionData) {	
		String[] tweetBroken = predictionTweet.getContent().split(" ");
		double words = 0.0;
		double totalscore = 0.0;
		for (String thing2: tweetBroken) {
			words++;
			if (predictionData.containsKey(thing2)) {
				int totalpolarity = 0;
				for (int polarities : predictionData.get(thing2)) {
					totalpolarity += polarities;
				}
				double averagepolarity = totalpolarity / predictionData.get(thing2).size();
				totalscore += averagepolarity;
			}
			else totalscore += 0;
		}
		int averagescore = 0;
		averagescore = (int) (words/totalscore);
		if (averagescore < 2) {
			return 0;
		}
		else if (averagescore > 2) {
			return 4;
		}
		else return 0;
	}
	
	public double judgeAccuracy(HashMap<String, ArrayList<Integer>> predictionData) {
		int negativeguess = 0;
		int positiveguess = 0;
		int negativereal = 0;
		int positivereal = 0;
		int correct = 0;
		int incorrect = 0;
		Iterator<Entry<Long, Tweet>> twitterator = TweetCollection.entrySet().iterator();
		while(twitterator.hasNext()){
			HashMap.Entry<Long, Tweet> tweet = twitterator.next();
			int prediction = this.predict(tweet.getValue(), predictionData);
			if (prediction == 0) {
				negativeguess++;
			}
			else if (prediction == 4) {
				positiveguess++;
			}
			if (tweet.getValue().getPolarity() == 0) {
				negativereal++;
			}
			else if (tweet.getValue().getPolarity() == 4)
			{
				positivereal++;
			}
			if (prediction == tweet.getValue().getPolarity()) {
				correct++;
			}
			else if (prediction != tweet.getValue().getPolarity()) {
				incorrect++;
			}
		}
		System.out.println("\nOverall model prediction accuracy: " + correct + " correct, " + incorrect + " incorrect, " + (double)correct/(double)incorrect * 100 + " % accuracy");
		System.out.println("\tExpected number of polarity 4 Tweets: " + positiveguess + "\tActual number of polarity 4 Tweets: " + positivereal);
		System.out.println("\tExpected number of polarity 0 Tweets: " + negativeguess + "\tActual number of polarity 0 Tweets: " + negativereal);
		return (correct / incorrect);
	}
	
	
}
