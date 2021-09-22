package CSCI3381Project1;

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
		
		//Create new TweetCollection from given file
		TweetCollection allData = new TweetCollection("./CSCI3381Project1/olddata.txt");
		
		//Print 100 random Tweets
		System.out.println("100 random Tweets: \n\n" + allData);
		
		//Manually add Tweets
		Tweet tweet1 = allData.addTweet(new Tweet(4,1876543211,"hdavis13","CSCI 3381 is a very fun class!"));
		System.out.println("Added Tweet: " + tweet1);
		Tweet tweet2 = allData.addTweet(new Tweet(0,2132154874,"hdavis13","Why is it still 80+ degrees Fahrenheit in September..."));
		System.out.println("Added Tweet: " + tweet2);
		Tweet tweet3 = allData.addTweet(new Tweet(0,1213542069,"hdavis13","Dr. Doderer why are you reading this Tweet?"));
		System.out.println("Added Tweet: " + tweet3);
		System.out.println("Added Tweet: " + allData.addTweet(new Tweet(0,1213542061,"hdavis13","This is quite possibly the most exciting Tweet ever written!")));
		
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
		System.out.println("Prediction data created!");		
		
		//Test prediction methodology on single Tweet
		Tweet sadtweet = allData.addTweet(new Tweet(0,1,"hdavis13","hate bad evil angry rage terrible horrid awful waste dissapointed dissapointing horrid"));
		Tweet happytweet = allData.addTweet(new Tweet(4,2,"hdavis13","like love amazing wonderful cool neat awesome admire worth good great incredible"));
		System.out.println("\nPredicting a " + allData.predict(happytweet, testData) + " polarity for tweet: " + happytweet);
		System.out.println("Tweet's actual polarity: " + happytweet.getPolarity());
		System.out.println("\nPredicting a " + allData.predict(sadtweet, testData) + " polarity for tweet: " + sadtweet);
		System.out.println("Tweet's actual polarity: " + sadtweet.getPolarity());
		
		//Judge overall prediction accuracy
		allData.judgeAccuracy(testData);
		
		//Write out all data in TweetCollection to file
		allData.writeOut("./CSCI3381Project1/newdata.txt");
		
		//Stop timer
		long stopTime = System.nanoTime();
		long duration = (stopTime - startTime) / 1_000_000_000;
		long mins = duration / 60;
		
		//Show time
		System.out.println("\n!---Entire program took: " + duration + " seconds (" + mins + " mins)---!\n");
//		
//		
//		
//		
		System.out.println("\n!---Stopping---!\n");
	}

}
