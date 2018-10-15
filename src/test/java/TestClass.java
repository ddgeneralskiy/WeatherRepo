import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class TestClass extends Assert {



    @BeforeClass
    public void setUp (){
        WeatherTest wt = new WeatherTest();
        try {
            wt.sendGet(WeatherTest.getUrlYandex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isCorrectDate (JSONObject jsonObject) {
        Date current_date = new Date();
        String cd = current_date.toString();
        String date = jsonObject.getString("now_dt").replace("T", " ");

        assertEquals(cd, date);

    }

    @Test
    public void isTemp (JSONObject jsonObject) {
        int TEMP = jsonObject.getJSONObject("fact").getInt("temp");
        assertEquals(TEMP, 9);
    }

    @Test
    public  void isPressure (JSONObject jsonObject) {
        int pressure = jsonObject.getJSONObject("fact").getInt("pressure_mm");
        assertEquals(pressure,756);

    }


}
