import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;


public class RestAPICounties {

    private static Scanner scanner;
    private static String scannedValue;

    public static void main(String[] args) throws IOException, JSONException {

        scannedValue = myScanner();
        while (!scannedValue.equalsIgnoreCase ("exit") ) {
            try { // catch JSONException
            parseCountriesJson(scannedValue);
            } catch (JSONException e) {
                //e.printStackTrace();
                System.out.println("country does not exists");
            }

            scannedValue = myScanner();
        }

    }


    public static String myScanner() {
        System.out.println("Please type Country name or type exit to finish the program");
        scanner = new Scanner(System.in);
        scannedValue = scanner.next();
        return scannedValue;
    }


    public static void parseCountriesJson(String scannedValue) throws IOException {
        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://restcountries.eu/rest/v2/name/" + scannedValue + "?fullText=true")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        //parse JSON
        JSONArray arr = new JSONArray(jsonData);
        JSONObject mainJsonObject = (JSONObject) arr.get(0);

        String countryName = arr.getJSONObject(0).getString("name");
        String region = arr.getJSONObject(0).getString("region");
        JSONArray callingCodes = mainJsonObject.getJSONArray("callingCodes");
        JSONArray borders = mainJsonObject.getJSONArray("borders");

        JSONArray currencies = mainJsonObject.getJSONArray("currencies");
        String symbol = currencies.getJSONObject(0).getString("symbol");
        System.out.println(countryName + " " + region + " " + callingCodes + " " + borders + " " + symbol);
    }

}

