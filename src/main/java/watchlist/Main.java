package watchlist;

public class Main {
    public static void main(String[] args) throws Exception {
        // Step 1: Read the API key from environment variables
        String apiKey = System.getenv("FINNHUB_API_KEY");

        // Step 2: If the key is missing, print an error message and exit the program
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Error: FINNHUB_API_KEY environment variable has not been set.");
            System.exit(1); //exits, 1 indicates something went wrong. 
        }
        // Step 3: Create a new WatchlistApp, passing in the apiKey
        WatchlistApp app = new WatchlistApp(apiKey);
        // Step 4: Call app.start(8080) to launch the server on port 8080
        app.start(8080);
        System.out.println("Server running at http://localhost:8080");
    }
}
