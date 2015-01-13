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
    public String[] horoscope;

    public static void main(String[] args){
        //start program
        FortuneTeller ft = new FortuneTeller();
        String sign;


        System.out.println("Please enter sign:");
        sign = ft.UserInput();

        //String horoscope[] = new String[0];

        try {
            ft.horoscope = ft.getHoroscopeURL(sign);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String myDate = ft.getDate();

        ft.output(myDate);



    }

    private void output(String date) {
        System.out.println("Today's Date: " + date);

        System.out.println("Today's Horoscope:");

        for (int x = 0; x < horoscope.length; x++){
            System.out.println(horoscope[x]);
        }

    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        String todaysDate = format.format(new Date());

        return todaysDate;
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
