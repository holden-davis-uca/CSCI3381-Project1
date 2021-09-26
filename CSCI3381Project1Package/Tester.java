package CSCI3381Project1Package;

import java.util.ArrayList;
import java.util.HashMap;

public class Tester {
	
	public static void main(String[] args) {
		
		System.out.println("\n!---Starting---!\n");
//		
//		
//		
//		
		//Start timer
		long startTime = System.nanoTime();
		
		//Designate read in and read out file(s)
		//Testing configuration (where trainingProcessed contains 1.6 million tweets, and testProcessed contains 498 tweets):
		//	Create TweetCollection from trainingProcessed, generate testing data from same collection, test on TweetCollection created from testProcessed
		//	Contents of TweetCollection (basically trainingProcessed with duplicates removed + 3 created tweets) will be written to out.txt
		//	Contents of tweetstotest will then be appended to out.txt
		String inFile = "./CSCI3381Project1Package/trainingProcessed.txt";
		String inFile2 = "./CSCI3381Project1Package/testProcessed.txt";
		String outFile = "./CSCI3381Project1Package/out.txt";
		
		//Create new TweetCollection from given file
		TweetCollection allData = new TweetCollection(inFile);
		
		//Manually read from file
		System.out.println(allData.readIn(inFile));
		
		//Print TweetCollection
		System.out.println("200 random Tweets (if they exist): \n\n" + allData);
		
		//Manually add Tweets
		Tweet tweet1 = allData.addTweet(new Tweet(4,1876543211,"hdavis13","CSCI 3381 is a very fun class!"));
		System.out.println("Added Tweet: " + tweet1);
		Tweet tweet2 = allData.addTweet(new Tweet(0,2132154874,"hdavis13","Why is it still 80+ degrees Fahrenheit in September..."));
		System.out.println("Added Tweet: " + tweet2);
		Tweet tweet3 = allData.addTweet(new Tweet(0,1213542069,"hdavis13","Dr. Doderer why are you reading this Tweet?"));
		System.out.println("Added Tweet: " + tweet3);
		System.out.println("Added Tweet: " + allData.addTweet(new Tweet(4,1213542061,"hdavis13","This is quite possibly the most exciting Tweet ever written!")));
		
		//Manually remove Tweet
		System.out.println("Removed Tweet: " + allData.removeTweet(tweet3));
		
		//Retrieve all Tweets by given user
		ArrayList<Tweet> userTweets = allData.searchByUser("hdavis13");
		System.out.println("\nFound " + userTweets.size() + " Tweets by user: " + userTweets);

		//Retrieve single Tweet by ID
		Tweet foundTweet = allData.searchByID(1213542061);
		System.out.println("\nFound a Tweet by an ID: " + foundTweet);
		
		//Compare Tweets by ID
		System.out.println("\nAre two different Tweets equal? " + tweet1.equals(tweet2));
		System.out.println("\nIs the same Tweet equal to itself? " + tweet2.equals(tweet2));
		
		//Retrieve a collection of all IDs in TweetCollection
		ArrayList<Long> IDs = allData.retriveAll();
		System.out.println("\nFound " + IDs.size() + " Unique IDs in TweetCollection");
		
		//Create prediction data
		System.out.println("\nCreating prediction data...");
		HashMap<String, ArrayList<Integer>> testData = allData.createPredictionData();
		System.out.println("Prediction data created!\n");		
		
		//Test generating random Tweets
		Tweet randtweet1 = allData.randomTweet();
		System.out.println("Random tweet: " + randtweet1);
		Tweet randtweet2 = allData.randomTweet();
		System.out.println("Random tweet: " + randtweet2);
		Tweet randtweet3 = allData.randomTweet();
		System.out.println("Random tweet: " + randtweet3);
		
		//Test prediction on single Tweet
		System.out.println("\nPredicting a " + allData.predict(randtweet1, testData) + " polarity for tweet: " + randtweet1);
		System.out.println("Tweet's actual polarity: " + randtweet1.getPolarity());
		System.out.println("\nPredicting a " + allData.predict(randtweet2, testData) + " polarity for tweet: " + randtweet2);
		System.out.println("Tweet's actual polarity: " + randtweet2.getPolarity());
		System.out.println("\nPredicting a " + allData.predict(randtweet3, testData) + " polarity for tweet: " + randtweet3);
		System.out.println("Tweet's actual polarity: " + randtweet3.getPolarity());
		
		//Judge overall prediction accuracy
		TweetCollection tweetstotest = new TweetCollection(inFile2);
		System.out.println(tweetstotest.judgeAccuracy(testData));
		
		//Write out all training data to file
		System.out.println(allData.writeOut(outFile));
		//Write out all testing data to file
		System.out.println(tweetstotest.writeOutAppend(outFile));
		
		//Stop timer
		long stopTime = System.nanoTime();
		long nanos = stopTime - startTime;
		long seconds = nanos / 1_000_000_000;
		long mins = seconds / 60;
		
		//Show time
		System.out.println("\n!---Entire program took: " + nanos + " nanoseconds = " + seconds + " seconds = " + mins + " mins---!\n");
//		
//		
//		
//		
		System.out.println("\n!---Stopping---!\n");
	}

}
