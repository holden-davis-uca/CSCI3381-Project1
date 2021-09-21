package CSCI3381Project1;

public class Tester {
	
	public static void main(String[] args) {
		
		System.out.println("\n!---Starting---!\n");
//		
//		
//		
//		
		TweetCollection allData = new TweetCollection();
		allData.readTweets("./CSCI3381Project1/trainingProcessed.txt");
		System.out.println(allData);
		
//		
//		
//		
//		
		System.out.println("\n!---Stopping---!\n");
	}

}
