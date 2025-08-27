package demo2.src.main.java.com.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class Main{
    // Simple city -> [lat, lon] map (add more if you like)
    private static final Map<String, double[]> CITY_COORDS = new HashMap<>();
    static {
        CITY_COORDS.put("mumbai",     new double[]{19.0760, 72.8777});
        CITY_COORDS.put("delhi",      new double[]{28.6139, 77.2090});
        CITY_COORDS.put("hyderabad",  new double[]{17.3850, 78.4867});
        CITY_COORDS.put("bengaluru",  new double[]{12.9716, 77.5946});
        CITY_COORDS.put("pune",       new double[]{18.5204, 73.8567});
        CITY_COORDS.put("chennai",    new double[]{13.0827, 80.2707});
    }
    public static void main(String[] args) {
        String city = (args.length > 0) ? args[0].toLowerCase() : "mumbai";
        double[] coords = CITY_COORDS.getOrDefault(city, CITY_COORDS.get("mumbai"));
        String url = String.format(
            "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true&timezone=auto",
            coords[0], coords[1]
        );
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.err.println("HTTP Error: " + response.statusCode());
                System.err.println(response.body());
                return;
            }
            // Parse JSON
            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject cw = root.getAsJsonObject("current_weather");
            if (cw == null) {
                System.err.println("Unexpected response: 'current_weather' not found.");
                System.out.println(response.body());
                return;
            }
            double temperature = cw.get("temperature").getAsDouble();    // °C
            double windspeed   = cw.get("windspeed").getAsDouble();      // km/h
            double winddir     = cw.get("winddirection").getAsDouble();  // degrees
            String time        = cw.get("time").getAsString();           // ISO time
            int isDay          = cw.get("is_day").getAsInt();            // 1 = day, 0 = night
            // Pretty table output
            printHeader("Current Weather");
            printRow("City", capitalize(city));
            printRow("Observed At", toLocal(time));
            printRow("Temperature (°C)", String.format("%.1f", temperature));
            printRow("Wind Speed (km/h)", String.format("%.1f", windspeed));
            printRow("Wind Direction (°)", String.format("%.0f", winddir));
            printRow("Day/Night", isDay == 1 ? "Day" : "Night");
            printFooter();
            System.out.println("\nTip: Run with a city name argument, e.g. `Delhi` or `Hyderabad`.");
        } catch (Exception e) {
            System.err.println("Failed to fetch/parse weather: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Utilities for formatting
    private static void printHeader(String title) {
        String line = "+----------------------+---------------------------+";
        System.out.println(line);
        System.out.printf("| %-20s | %-25s |\n", title, "");
        System.out.println(line);
    }
    private static void printRow(String key, String value) {
        System.out.printf("| %-20s | %-25s |\n", key, value);
    }
    private static void printFooter() {
        System.out.println("+----------------------+---------------------------+");
    }
    private static String toLocal(String iso) {
        try {
            return ZonedDateTime.parse(iso).toString();
        } catch (Exception e) {
            return iso;
        }
    }
    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
