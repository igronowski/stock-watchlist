package watchlist;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WatchlistApp {
    // Two fields: a List<String> to store stock symbols, and a StockFetcher

    // Constructor: takes the apiKey, creates a new StockFetcher with it

    // start(port): creates the web server and registers two URL routes
    //   Route 1: "/api/stocks" → calls handleStocks()  (the data API)
    //   Route 2: "/"           → calls handleStatic()  (serves the HTML page)
    //   Then calls server.start()
    public void start(int port) throws Exception {
    }

    // handleStatic(): sends the HTML file to the browser
    //   1. Get the URL path from the exchange (e.g. "/index.html")
    //   2. If path is just "/", change it to "/index.html"
    //   3. Load the file from the "web" folder on disk
    //   4. If the file doesn't exist, send a 404 response
    //   5. Otherwise, read the file's bytes and send them back with Content-Type "text/html"
    private void handleStatic(HttpExchange exchange) throws IOException {
    }

    // handleStocks(): decides what to do based on the HTTP method
    //   GET    → call handleGetStocks() to return all stock prices
    //   POST   → extract the symbol from the URL, add it to the symbols list if not already there
    //   DELETE → extract the symbol from the URL, remove it from the symbols list
    //   anything else → send a 405 "Method not allowed" response
    private void handleStocks(HttpExchange exchange) throws IOException {
    }

    // handleGetStocks(): fetches prices for every symbol and returns them as a JSON array
    //   1. Start building a String: "["
    //   2. Loop through the symbols list
    //   3. For each symbol, call fetcher.fetchQuote() to get a Stock object
    //   4. Append stock.toJson() to the string (add a comma between items)
    //   5. If fetchQuote() throws an exception, print the error and skip that stock
    //   6. Close the array with "]" and send it back with Content-Type "application/json"
    private void handleGetStocks(HttpExchange exchange) throws IOException {
    }

    // send(): a helper to write any response back to the browser
    //   Takes: the exchange, an HTTP status code, a content type, and a body string
    //   Converts the body to bytes, sets headers, and writes everything to the response
    private void send(HttpExchange exchange, int status, String contentType, String body) throws IOException {
    }
}
