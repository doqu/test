/**
 COPYRIGHT (C) 2015 Quoc Doan. All Rights Reserved.
 Class to create an manipulate Events objects.
 Solves CS151 homework assignment #2
 @author Quoc Doan
 @version 1.02 2015/3/10
 */

import java.io.Serializable;
import java.util.*;

public class Events implements Serializable
{
    public String start;
    public String end;
    private String title;
    private String date;
    private String dayOfWeek;
    private String monthOfYear;
    private String time;
    private int month;
    private int day;
    private int year;
    private int startTime;
    private int endTime;
    private int dayOfTheYear;
    private static final long serialVersionUID = 1L;

    /**
     Empty Constructor for Events
     */
    public Events()
    {

    }

    /**
     Constructor for Events
     @param title event title
     @param date event date
     @param time event time
     */
    public Events(String title, String date, String time)
    {
        this.title = title;
        this.date = date;
        this.time = time;
        this.month = Integer.parseInt(date.substring(0, 2));
        this.day = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6, 10));
        this.start = time.substring(0, 2) + time.substring(3, 5);
        this.startTime = Integer.parseInt(start);

        if(time.length() > 5)
        {
            this.end = time.substring(8, 10) + time.substring(11, 13);
            this.endTime = Integer.parseInt(end);
        }

        GregorianCalendar cal = new GregorianCalendar(year, month, day);
        cal.add(Calendar.MONTH, -1);
        int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        this.dayOfTheYear = cal.get(Calendar.DAY_OF_YEAR);

        switch(dayOfTheWeek)
        {
            case 1: this.dayOfWeek = "Sunday";
                break;
            case 2: this.dayOfWeek = "Monday";
                break;
            case 3: this.dayOfWeek = "Tuesday";
                break;
            case 4: this.dayOfWeek = "Wednesday";
                break;
            case 5: this.dayOfWeek = "Thursday";
                break;
            case 6: this.dayOfWeek = "Friday";
                break;
            case 7: this.dayOfWeek = "Saturday";
                break;
        }

        switch(month)
        {
            case 1: this.monthOfYear = "January";
                break;
            case 2: this.monthOfYear = "February";
                break;
            case 3: this.monthOfYear = "March";
                break;
            case 4: this.monthOfYear = "April";
                break;
            case 5: this.monthOfYear = "May";
                break;
            case 6: this.monthOfYear = "June";
                break;
            case 7: this.monthOfYear = "July";
                break;
            case 8: this.monthOfYear = "August";
                break;
            case 9: this.monthOfYear = "September";
                break;
            case 10: this.monthOfYear = "October";
                break;
            case 11: this.monthOfYear = "November";
                break;
            case 12: this.monthOfYear = "December";
                break;
        }
    }

    /**
     Method to get start time of the event
     @return start time
     */
    public int getStart()
    {
        return this.startTime;
    }

    /**
     Method to get end time of the event
     @return end time
     */
    public int getEndTime()
    {
        return this.endTime;
    }

    /**
     Method to get day of the year for event
     @return day of the year
     */
    public int getDayOfTheYear()
    {
        return this.dayOfTheYear;
    }

    /**
     Method to get month of the event
     @return month
     */
    public int getMonth()
    {
        return this.month;
    }

    /**
     Method to get day of the event
     @return day
     */
    public int getDay()
    {
        return this.day;
    }

    /**
     Method to get year of the event
     @return year
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     Method to get time of the event
     @return time
     */
    public String getTime()
    {
        return this.time;
    }

    /**
     Method to get month of the year of the event
     @return month of the year
     */
    public String getMonthOfYear()
    {
        return this.monthOfYear;
    }

    /**
     Method to get name of day of the event
     @return name of day
     */
    public String getNameOfDay()
    {
        return this.dayOfWeek;
    }

    /**
     Method to get date of the event
     @return date
     */
    public String getDate()
    {
        return this.date;
    }

    /**
     Method to get title of the event
     @return title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     Method to get title and time of the event
     @return title and time
     */
    public String printAll()
    {
        return this.title + " " + this.time;
    }

    /**
     Method to make a key for the event
     @return key
     */
    public String makeKey()
    {
        String y = String.valueOf(year);
        String m = String.valueOf(month);
        if(m.length() == 1)
        {
            m = "0" + m;
        }
        String d = String.valueOf(day);
        if(d.length() == 1)
        {
            d = "0" + d;
        }
        String keyDate = y + m + d;
        return keyDate;
    }
}