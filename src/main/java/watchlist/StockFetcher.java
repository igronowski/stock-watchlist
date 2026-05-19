package watchlist;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StockFetcher {
    // Two fields: an HttpClient (handles sending requests) and a String apiKey

    // Constructor: takes the apiKey as a parameter and stores it

    // fetchQuote(symbol): contacts Finnhub and returns a Stock object
    //   1. Build the URL: "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey
    //   2. Create an HttpRequest pointed at that URL
    //   3. Send the request using client.send() and get the response body as a String
    //   4. Call parseField() three times to extract "c" (price), "d" (change), "dp" (percentChange)
    //   5. If price is 0, throw an Exception — the symbol is probably invalid
    //   6. Return a new Stock object with all four values
    public Stock fetchQuote(String symbol) throws Exception {
        return null;
    }

    // parseField(json, key): pulls a number out of a JSON string
    //   1. Find where the key appears in the json string (e.g. "\"c\":")
    //   2. Move past the key to where the value starts
    //   3. Find the end of the value (next comma or closing brace })
    //   4. Parse that substring as a double and return it
    //   5. If anything goes wrong, return 0
    private double parseField(String json, String key) {
        return 0;
    }
}
