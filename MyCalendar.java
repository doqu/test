/**
 COPYRIGHT (C) 2015 Quoc Doan. All Rights Reserved.
 Class to manipulate MyCalendar object.
 Solves CS151 homework assignment #2
 @author Quoc Doan
 @version 1.02 2015/3/10
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyCalendar extends GregorianCalendar
{
    private HashMap<String, ArrayList<Events>> hm;
    protected File newFile;

    /**
     Construct a MyCalendar object to represent a Gregorian Calendar
     @throws FileNotFoundException if file is not found, throw exception
     */
    public MyCalendar() throws FileNotFoundException
    {
        String filename = "events";
        newFile = new File(filename + ".txt");
        if(!newFile.exists())
        {
            try
            {
                newFile.createNewFile();
            }
            catch (IOException e)
            {
                String err = "Exception when finding file";
                System.out.println(err);
            }
        }
    }

    /**
     Method to set a specific date
     @return the specific date
     */
    public String setDate()
    {
        String month = String.valueOf(get(Calendar.MONTH) + 1);
        if(month.length() == 1)
        {
            month = "0" + month;
        }
        String day = String.valueOf(get(Calendar.DAY_OF_MONTH));
        if(day.length() == 1)
        {
            day = "0" + day;
        }
        String year = String.valueOf(get(Calendar.YEAR));
        return year + month + day;
    }

    /**
     Method to check if an event exist in the Calendar
     @return true or false based on hasEvent
     */
    public boolean hasEvent()
    {
        boolean result;
        String month = String.valueOf(get(Calendar.MONTH) + 1);
        if(month.length() == 1)
        {
            month = "0" + month;
        }
        String day = String.valueOf(get(Calendar.DAY_OF_MONTH));
        if(day.length() == 1)
        {
            day = "0" + day;
        }
        String year = String.valueOf(get(Calendar.YEAR));
        String date = year + month + day;
        if(hm.containsKey(date))
        {
            result = hm.containsKey(date);
        }
        else
        {
            result = hm.containsKey(date);
        }
        return result;
    }

    /**
     Method to go to a specific date
     @param date the date to go to
     @return the date to the user
     */
    public ArrayList<Integer> goTo(String date)
    {
        int monthT = Integer.parseInt(date.substring(0, 2));
        int dayT = Integer.parseInt(date.substring(3, 5));
        int yearT = Integer.parseInt(date.substring(6, 10));
        int monthDiff = monthT - (get(Calendar.MONTH) + 1);
        int dayDiff = dayT - get(Calendar.DAY_OF_MONTH);
        int yearDiff = yearT - get(Calendar.YEAR);
        ArrayList<Integer> difference = new ArrayList<Integer>();
        difference.add(yearDiff);
        difference.add(monthDiff);
        difference.add(dayDiff);
        return difference;
    }

    /**
     Method to print events to a .bin file
     @param filename the filename to print to
     */
    @SuppressWarnings("rawtypes")
    public void printToFile(String filename)
    {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            String key;
            ArrayList<Events> temp;
            TreeMap<String, ArrayList<Events>> tm = new TreeMap<String, ArrayList<Events>> (hm);
            ArrayList<Events> storeEvents = new ArrayList<Events>();
            for(Map.Entry entry : tm.entrySet())
            {
                key = entry.getKey().toString();
                temp = tm.get(key);
                for(Events events: temp)
                {
                    storeEvents.add(events);
                }
            }
            os.writeObject(storeEvents);
            os.close();
        } catch (FileNotFoundException e) {
            String err = "File Not Found ---";
            System.out.println(err);
        } catch (IOException e) {
            String err = "IOException ---";
            System.out.println(err);
        }
    }

    /**
     Method to load events from a .bin file
     @param filename the filename to load from
     */
    @SuppressWarnings("unchecked")
    public void populate(String filename)
    {
        try
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            ArrayList<Events> loadEvents;
            loadEvents = (ArrayList<Events>) is.readObject();
            for(Events e : loadEvents)
            {
                create(e);
            }
            System.out.println("All events have been loaded from " + filename + ".");
            is.close();
        }
        catch (IOException e)
        {
            String err = "File Could Not be Found.";
            System.out.println(err);
        }
        catch (ClassNotFoundException e)
        {
            String err = "File Could Not be Found.";
            System.out.println(err);
        }
    }

    /**
     Method to print the events of a specific date
     @param date to print events of
     */
    public void printEventsByDay(String date)
    {
        ArrayList<Events> temp = hm.get(date);

        for(Events c : temp)
        {
            System.out.println(c.printAll());
        }
    }

    /**
     Method to print all events of the calendar
     */
    @SuppressWarnings("rawtypes")
    public void printAllEvents()
    {
        String key;
        String keyYear;
        String event;
        String keyCounter = "";
        ArrayList<Events> temp;
        TreeMap<String, ArrayList<Events>> tm = new TreeMap<String, ArrayList<Events>> (hm);
        for(Map.Entry entry : tm.entrySet())
        {
            key = entry.getKey().toString();
            keyYear = entry.getKey().toString().substring(0, 4);
            if(!keyYear.equals(keyCounter))
            {
                keyCounter = keyYear;
                System.out.println(keyYear);
            }
            temp = tm.get(key);
            for(Events events: temp)
            {
                event = "    " + events.getNameOfDay()
                        + ", " + events.getMonthOfYear()
                        + " " + events.getDay() + " "
                        + events.getTime() + " "
                        + events.getTitle();
                System.out.println(event);
            }
        }
    }

    /**
     Method to create an event and assign it to a hashmap
     @param event the event to put into the hashmap
     */
    public void create(Events event)
    {
        ArrayList<Events> eventList = new ArrayList<Events>();
        String date = event.makeKey();
        int counter = 0;
        if(hm.containsKey(date))
        {
            eventList = hm.get(date);
            for(int i = 0; i < eventList.size(); i++)
            {
                if(eventList.get(i).getTitle().equals(event.getTitle()))
                {
                    System.out.println("The event already exist.");
                    i = eventList.size();
                    counter = eventList.size();
                }
                else
                {
                    counter = -1;
                }
            }
            if(counter != eventList.size())
            {
                eventList.add(event);
                Collections.sort(eventList, new Comparator<Events>()
                {
                    public int compare(Events o1, Events o2)
                    {
                        return o1.getStart() - o2.getStart();
                    }
                });
                hm.put(date, eventList);
                System.out.println("The event has been added.");
            }
        }
        else
        {
            eventList.add(event);
            hm.put(date, eventList);
            System.out.println("The event has been added.");
        }
    }

    /**
     Method to set a hashmap to the calendar
     @param hm the hash map to set
     */
    public void setMap(HashMap<String, ArrayList<Events>> hm)
    {
        this.hm = hm;
    }

    /**
     Method to delete the events of an entire day
     @param date the day to delete events from
     */
    public void deleteDayEvent(String date)
    {
        String monthT = date.substring(0, 2);
        String dayT = date.substring(3, 5);
        String yearT = date.substring(6, 10);
        date = yearT + monthT + dayT;
        if(hm.containsKey(date))
        {
            hm.remove(date);
            System.out.println("The event has been removed.");
        }
    }

    /**
     Method to delete all events of a Calendar
     */
    public void deleteCalEvent()
    {
        hm.clear();
        System.out.println("All events have been removed.");
    }

    /**
     Method to provide a day view for the user
     */
    public void printDay()
    {
        MONTHS[] arrayOfMonths = MONTHS.values();
        FULLDAYS[] arrayOfDays = FULLDAYS.values();
        System.out.print(arrayOfDays[get(Calendar.DAY_OF_WEEK) - 1]);
        System.out.print(", " + arrayOfMonths[get(Calendar.MONTH)]);
        System.out.print(" " + get(Calendar.DAY_OF_MONTH));
        System.out.println(", " + get(Calendar.YEAR));
    }

    /**
     Method to provide a month view for the user
     @param input to determine if the Calendar has been initially displayed or not
     */
    public void printCalendar(String input)
    {
        //set up
        MONTHS[] arrayOfMonths = MONTHS.values();
        int year = get(Calendar.YEAR);
        int month = get(Calendar.MONTH);
        int day = get(Calendar.DAY_OF_MONTH);
        int daysInMonth = getActualMaximum(Calendar.DAY_OF_MONTH);
        int counter = 1;

        StringBuilder builder = new StringBuilder();
        for (DAYS value : DAYS.values())
        {
            builder.append(value + "   ");
        }
        String dayName = builder.toString();

        Calendar calForDay = Calendar.getInstance();
        calForDay.set(Calendar.DATE, day);
        calForDay.set(Calendar.MONTH, month);
        calForDay.set(Calendar.YEAR, year);
        calForDay.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calForDay.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String fDOM = sdf.format(firstDayOfMonth);
        if(fDOM.equalsIgnoreCase("SUNDAY")) {counter = 1;}
        if(fDOM.equalsIgnoreCase("MONDAY")) {counter = 2;}
        if(fDOM.equalsIgnoreCase("TUESDAY")) {counter = 3;}
        if(fDOM.equalsIgnoreCase("WEDNESDAY")) {counter = 4;}
        if(fDOM.equalsIgnoreCase("THURSDAY")) {counter = 5;}
        if(fDOM.equalsIgnoreCase("FRIDAY")) {counter = 6;}
        if(fDOM.equalsIgnoreCase("SATURDAY")) {counter = 7;}

        //printing
        System.out.println(String.format("\n%s \n%s %s \n%s", "C A L E N D A R", arrayOfMonths[get(Calendar.MONTH)],
                get(Calendar.YEAR), dayName));
        for(int j = 1; j < counter; j++)
        {
            System.out.print("      ");
        }
        for(int i = 1; i < daysInMonth + 1; i++)
        {
            String num = String.valueOf(i);
            if(!input.isEmpty())
            {
                String monthT = String.valueOf(month + 1);
                if(monthT.length() == 1)
                {
                    monthT = "0" + monthT;
                }
                String dayT = num;
                if(dayT.length() == 1)
                {
                    dayT = "0" + dayT;
                }
                String yearT = String.valueOf(year);
                String dateComp = yearT + monthT + dayT;
                if(num.length() == 1)
                {
                    if(hm.containsKey(dateComp) && i == day)
                    {
                        System.out.printf("[*" + num + "]  ");
                    }
                    else if(hm.containsKey(dateComp))
                    {
                        System.out.printf("[-" + num + "]  ");
                    }
                    else if(i == day)
                    {
                        System.out.printf("[" + num + "]   ");
                    }
                    else
                    {
                        System.out.printf(num + "     ");
                    }
                }
                else
                {
                    if(hm.containsKey(dateComp) && i == day)
                    {
                        System.out.printf("[*" + num + "] ");
                    }
                    else if(hm.containsKey(dateComp))
                    {
                        System.out.printf("[-" + num + "] ");
                    }
                    else if(i == day)
                    {
                        System.out.printf("[" + num + "]  ");
                    }
                    else
                    {
                        System.out.printf(num + "    ");
                    }
                }
            }
            else
            {
                if(num.length() == 1)
                {
                    if(i == day)
                    {
                        System.out.printf("[" + num + "]  ");
                    }
                    else
                    {
                        System.out.printf(num + "     ");
                    }
                }
                else
                {
                    if(i == day)
                    {
                        System.out.printf("[" + num + "]  ");
                    }
                    else
                    {
                        System.out.printf(num + "    ");
                    }
                }
            }
            if(counter % 7 == 0)
            {
                System.out.println();
            }
            counter++;
        }
        System.out.println("\n");
    }

    /**
     MONTHS that can be used
     */
    public enum MONTHS
    {
        /**
         January Month
         */
        January,

        /**
         February Month
         */
        February,

        /**
         March Month
         */
        March,

        /**
         April Month
         */
        April,

        /**
         May Month
         */
        May,

        /**
         June Month
         */
        June,

        /**
         July Month
         */
        July,

        /**
         August Month
         */
        August,

        /**
         September Month
         */
        September,

        /**
         October Month
         */
        October,

        /**
         November Month
         */
        November,

        /**
         December Month
         */
        December
    }

    /**
     Short names of DAYS that can be used
     */
    public enum DAYS
    {
        /**
         Sun DAYS
         */
        Sun,

        /**
         Mon DAYS
         */
        Mon,

        /**
         Tue DAYS
         */
        Tue,

        /**
         Wed DAYS
         */
        Wed,

        /**
         * Thu DAYS
         */
        Thu,

        /**
         Fri DAYS
         */
        Fri,

        /**
         Sat DAYS
         */
        Sat
    }

    /**
     Full names of DAYS that can be used
     */
    public enum FULLDAYS
    {
        /**
         Sunday FULLDAYS
         */
        Sunday,

        /**
         Monday FULLDAYS
         */
        Monday,

        /**
         Tuesday FULLDAYS
         */
        Tuesday,

        /**
         Wednesday FULLDAYS
         */
        Wednesday,

        /**
         * Thursday FULLDAYS
         */
        Thursday,

        /**
         Friday FULLDAYS
         */
        Friday,

        /**
         Saturday FULLDAYS
         */
        Saturday
    }
}