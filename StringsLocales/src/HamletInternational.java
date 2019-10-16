package by.training.demolocal;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HamletInternational {


    public static void main(String[] args) {
        System.out.println("1 — английский \n 2 — белорусский \n 3 — русский ");

        char i = 0;
        try {
            i = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String country = "UK";//Locale.getDefault().getCountry(); //
        //использует текущую локаль машины
        String language = "en"; //Locale.getDefault().getLanguage();
        switch (i) {
            case '1':
                country = "UK";
                language = "en";
                break;
            case '2':
                country = "BY";
                language = "be";
                break;
            case '3':
                country = "RU";
                language = "ru";
                break;
        }

        Locale current = new Locale(language, country);


        ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
        String s1 = rb.getString("str1");
        System.out.println("s1 = " + s1);
        /*
        now you can use iso-8859-1 coding of properties files.

        try {
            String coorectStr = new String(s1.getBytes("ISO-8859-1"));
            System.out.println("coorectStr = " + coorectStr);
        } catch (UnsupportedEncodingException newE) {
            newE.printStackTrace();
        }
        try {
            String s = new String(rb.getString("str1").getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException newE) {
            newE.printStackTrace();
        }*/
        //String s2 = rb.getString("str2");
        //System.out.println(s2);
    }
}

