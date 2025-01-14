package practicum;

import com.github.javafaker.Faker;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Utils {

    public static Long getTimestamp(){
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

    public static Integer getRandomInt(Integer lowerBound, Integer upperBound){
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static String generateFirstName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateLastName(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String generateAddress(){
        Faker faker = new Faker();
        StringBuilder sb = new StringBuilder(faker.address().cityName());
        sb.append(" city, ");
        sb.append(faker.address().streetAddress());
        sb.append(" street, ");
        sb.append(faker.address().buildingNumber());
        return sb.toString();
    }

    public static String generatePhone(){
        StringBuilder sb = new StringBuilder("+7");
        sb.append(getRandomInt(900, 1000));
        sb.append(getRandomInt(99, 900));
        for (int i=0;i<4;i++){sb.append(getRandomInt(0,10));}
        return sb.toString();
    }

    public static String getDate(Integer plusDays){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date todayDate = new Date();
        long beforeTime = ((todayDate.getTime() / 1000) + 60 * 60 * 24 * plusDays)*1000; // прибавляем дни
        return sdf.format(beforeTime);
    }
}
