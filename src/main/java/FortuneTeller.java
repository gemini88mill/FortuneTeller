import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by raphael on 1/8/15.
 *
 * Fortune Teller (Horoscope) – A program that checks your horoscope on various
 * astrology sites and puts them together for you each day.
 */
public class FortuneTeller {

    private final String HOROSCOPE_URL = "http://my.horoscope.com/astrology/free-daily-horoscope-";
    private final String CLOSE_URL = ".html";

    public static void main(String[] args){
        //start program
        FortuneTeller ft = new FortuneTeller();
        String sign;

        ft.getDate();
        System.out.println("Please enter sign:");
        sign = ft.UserInput();

        String horoscope[] = new String[0];

        try {
            horoscope = ft.getHoroscopeURL(sign);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        for (int x = 0; x < horoscope.length; x++){
            System.out.println(horoscope[x]);
        }

    }

    private void getDate() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String getDate = format.getCalendar().toString();

        System.out.println();

    }

    private String UserInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private String[] getHoroscopeURL(String sign) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect(HOROSCOPE_URL + sign + CLOSE_URL).userAgent("Mozilla/5.0").get();

        Elements content = doc.body().select("div#textline");
        String text = content.text();
        String[] textsplit = text.split("\\.");

        return textsplit;
    }
}
