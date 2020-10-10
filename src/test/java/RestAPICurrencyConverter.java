import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;


public class RestAPICurrencyConverter {

    private static Scanner scanner;
    private static double scannedValue;
    private static double currencyRateUSDToILS;

    public static void main(String[] args) throws IOException, JSONException {

        scannedValue = myScanner();
        currencyRateUSDToILS = parseRatesJson();
        System.out.println("Conversion result: " + scannedValue*currencyRateUSDToILS);
        System.out.println("Thanks for using our currency converter");


    }


    public static double myScanner() {
        System.out.println("Welcome to currency converter");
        System.out.println("Please enter an amount of Dollars to convert to Shekels");
        scanner = new Scanner(System.in);
        scannedValue = scanner.nextDouble();
        return scannedValue;
    }


    public static double parseRatesJson() throws IOException {
        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.ratesapi.io/api/latest")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        //parse JSON
        JSONObject mainJsonObject = new JSONObject(jsonData);
        // get Json object
        JSONObject resultsJson = mainJsonObject.getJSONObject("rates");
        // get value
        double val = resultsJson.getDouble("ILS");
        return (val);
    }

}

