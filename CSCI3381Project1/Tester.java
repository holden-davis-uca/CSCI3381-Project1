package CSCI3381Project1;

import java.util.ArrayList;

public class Tester {
	
	public static void main(String[] args) {
		
		System.out.println("\n!---Starting---!\n");
//		
//		
//		
//		
		
		//Create new TweetCollection from given file
		TweetCollection allData = new TweetCollection("./CSCI3381Project1/Data.txt");
		
		//Print 100 random Tweets
		System.out.println("100 random Tweets: \n\n" + allData);
		
		//Manually add Tweets
		Tweet tweet1 = allData.addTweet(new Tweet(4,7274,"hdavis13","CSCI 3381 is a very fun class!"));
		System.out.println("Added Tweet: " + tweet1);
		Tweet tweet2 = allData.addTweet(new Tweet(0,7355608,"hdavis13","Why is it still 80+ degrees Fahrenheit in September..."));
		System.out.println("Added Tweet: " + tweet2);
		Tweet tweet3 = allData.addTweet(new Tweet(0,42069,"hdavis13","Dr. Doderer why are you reading this Tweet?"));
		System.out.println("Added Tweet: " + tweet3);
		System.out.println("Added Tweet: " + allData.addTweet(new Tweet(0,913750,"hdavis13","This is quite possibly the most exciting Tweet ever written!")));
		
		//Manually remove Tweet
		allData.removeTweet(tweet3);
		
		//Retrieve all Tweets by given user
		ArrayList<Tweet> userTweets = allData.searchByUser("hdavis13");
		System.out.println("\nFound " + userTweets.size() + " Tweets by user: " + userTweets);

		//Retrieve single Tweet by ID
		Tweet foundTweet = allData.searchByID(7355608);
		System.out.println("\nFound a Tweet by an ID: " + foundTweet);
		
		//Compare Tweets by ID
		System.out.println("\nAre two different Tweets equal? " + tweet1.equals(tweet2));
		System.out.println("\nIs the same Tweet equal to itself? " + tweet2.equals(tweet2));
		
		//Retrieve a collection of all IDs in TweetCollection
		ArrayList<Long> IDs = allData.retriveAll();
		System.out.println("\nFound " + IDs.size() + " Unique IDs in TweetCollection");
		
		//Write out all data in TweetCollection to file
		allData.writeOut("./CSCI3381Project1/Data.txt");
		
//		
//		
//		
//		
		System.out.println("\n!---Stopping---!\n");
	}

}
