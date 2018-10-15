import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherTest {
    public static final String APIKEY = "10b21b14-1a59-48d0-9c33-675251a3630d";
    public static final String URL_YANDEX = "https://api.weather.yandex.ru/v1/forecast?lat=55.75396&lon=37.620393&extra=true";
    private static final String NEW_LINE = "\n";

    public static String getUrlYandex() {
        return URL_YANDEX;
    }

    public static JSONObject sendGet (String URL_Y) throws Exception{

        URL obj = new URL(URL_YANDEX);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Yandex-API-Key", APIKEY);

        int responseCode = connection.getResponseCode();
        System.out.println("Sending 'GET' request to URL : " + URL_YANDEX);
        System.out.println("Response Code : " + responseCode);
        if (responseCode != 200) {
            return null;
        }
        else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append(NEW_LINE);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());

            return jsonObject;
        }

    }

    public static void renderWeather (JSONObject jsonObject){
        int TEMP = jsonObject.getJSONObject("fact").getInt("temp");
        int pressure = jsonObject.getJSONObject("fact").getInt("pressure_mm");
        String condition = jsonObject.getJSONObject("fact").getString("condition");
        String date = jsonObject.getString("now_dt").replace("T", " ");
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("yyyy-MM-dd HH:mm:ss");
        try {
            Date docDate = sf.parse(date);
            Date current_date = new Date();
            System.out.println("Дата запроса: "+docDate + "\nТекущая дата: " + current_date +
                    "\nТемпература: " + TEMP + "\nАтмосферное давление: " + pressure + "\nУсловия: " + condition);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {

            renderWeather (sendGet(URL_YANDEX));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
