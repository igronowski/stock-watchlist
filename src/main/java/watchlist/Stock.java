package watchlist;

public class Stock {
    // Four fields: symbol (String), price, change, percentChange (all double)
    public String symbol;
    public double price;
    public double change;
    public double percentChange;
    // Constructor: takes all 4 fields as parameters and assigns them to the fields above
    public Stock(String symbol, double price, double change, double percentChange) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.percentChange = percentChange;
    }
    // toJson(): returns a String in JSON format so the frontend can read it
    // Example output: {"symbol":"AAPL","price":189.45,"change":1.23,"percentChange":0.65}
    // Hint: use String.format() with \"  to include quotes inside the string
    public String toJson() {
        String sum = "{\"symbol\":\"%s\", \"price\":%.2f,\"change\":%.2f, \"percentChange\":%.2f}";
        return String.format(sum, symbol, price, change, percentChange);
    }
}
