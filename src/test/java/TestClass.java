import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class TestClass extends Assert {



    @Test
    public void isCorrectDate () {
        try {
            String date = WeatherTest.sendGet(WeatherTest.getUrlYandex()).getString("now_dt").replace("T", " ");
            Date current_date = new Date();
            String cd = current_date.toString();


            assertEquals(cd, date);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void isTemp () {
        int TEMP = 0;
        try {
            TEMP = WeatherTest.sendGet(WeatherTest.getUrlYandex()).getJSONObject("fact").getInt("temp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(TEMP, 7);
    }

    @Test
    public  void isPressure () throws Exception {
        int pressure = WeatherTest.sendGet(WeatherTest.getUrlYandex()).getJSONObject("fact").getInt("pressure_mm");
        assertEquals(pressure,756);

    }


}
