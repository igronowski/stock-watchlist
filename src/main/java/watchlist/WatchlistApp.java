package watchlist;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WatchlistApp {
    List<String> stockSymbols;
    StockFetcher fetcher;
    // takes the apiKey, creates a new StockFetcher with it
    public WatchlistApp(String apiKey) {
        this.stockSymbols = new ArrayList<>();
        this.fetcher = new StockFetcher(apiKey);
    }
    // start(port) creates the web server and registers two URL routes
    //  1: "/api/stocks" calls handleStocks()  (the data API)
    //  2: "/" calls handleStatic()  (serves the HTML page)
    // this means any request to the path runs the respective function
    //   Then calls server.start()
    // inetsocketaddress means listen to all network interfaces on this port (8080).
    public void start(int port) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/stocks", this::handleStocks);
        server.createContext("/", this::handleStatic);
        server.start();
    }

    // handleStatic(): sends the HTML file to the browser
    //   1. Get the URL path from the exchange (e.g. "/index.html")
    //   2. If path is just "/", change it to "/index.html"
    //   3. Load the file from the "web" folder on disk
    //   4. If the file doesn't exist, send a 404 response
    //   5. Otherwise, read the file's bytes and send them back with Content-Type "text/html"
    private void handleStatic(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) {
            path = "/index.html";
        }
        File file = new File("web" + path);
        if (!file.exists()) {
            send(exchange, 404, "text/plain", "Not found");
            return;
        }
        byte[] bytes = Files.readAllBytes(file.toPath());
        exchange.getResponseHeaders().set("Contain")
        exchange.getResponseHeaders(200, bytes)
        exchange.getResponseBody()
        exchange.getResponseBody().close();
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
        String str = "[";
        for(int i = 0; i < stockSymbols.size(); i++){
            Stock stock = fetcher.fetchQuote(stockSymbols.get(i));
            str += stock.toJson();
            if(i != stockSymbols.size()-1){
                str += ", "
            }
            try {
                Stock stock = fetcher.fetchQuote(stockSymbols.get(i));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // send(): a helper to write any response back to the browser
    //   Takes: the exchange, an HTTP status code, a content type, and a body string
    //   Converts the body to bytes, sets headers, and writes everything to the response
    private void send(HttpExchange exchange, int status, String contentType, String body) throws IOException {
        byte[] back2B = body.getBytes();
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(status, back2B.length);
        OutputStream os = exchange.getResponseBody();
        os.write(back2B);
        os.close();
    }
}
