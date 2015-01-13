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


    /**
     * main method, calls FortuneTeller class and sends Strings sign to userInput() to be
     * sent to the Horoscope.com URI
     * @param args
     */
    public static void main(String[] args){
        //start program
        FortuneTeller ft = new FortuneTeller();
        String sign;

        System.out.println("Please enter sign:");
        sign = ft.UserInput();

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

    /**
     * outputs values requested in program.
     * @param date
     */
    private void output(String date) {
        System.out.println("Today's Date: " + date);

        System.out.println("Today's Horoscope:");

        for (int x = 0; x < horoscope.length; x++){
            System.out.println(horoscope[x]);
        }

    }

    /**
     * gets today's date for reference.
     *
     * @return String
     */
    private String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        String todaysDate = format.format(new Date());

        return todaysDate;
    }

    /**
     * scanner class used for simple input.
     *
     * @return String
     */
    private String UserInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    /**
     * getHoroscopeURL()
     *
     * retrieves horoscope information using jSoup import
     * scrape using css query and splits the value by sentence (".")
     * in order to neatly format in terminal.
     *
     * accepts String sign: can be any sign (gemini, pieces, etc.)
     *
     *
     * @param sign
     * @returns String[]
     * @throws IOException
     */
    private String[] getHoroscopeURL(String sign) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect(HOROSCOPE_URL + sign + CLOSE_URL).userAgent("Mozilla/5.0").get();

        Elements content = doc.body().select("div#textline");
        String text = content.text();
        String[] textsplit = text.split("\\.");

        return textsplit;
    }
}
