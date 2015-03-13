/**
 COPYRIGHT (C) 2015 Quoc Doan. All Rights Reserved.
 Class to display MyCalendar to the user.
 Solves CS151 homework assignment #2
 @author Quoc Doan
 @version 1.02 2015/3/10
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Display
{
    private MyCalendar cal;

    /**
     Construct the display for the user based on Calendar
     @param cal the cal to print display to
     */
    public Display(MyCalendar cal)
    {
        this.cal = cal;
    }

    /**
     Method to show the display to the user
     */
    public void show()
    {
        Scanner sc = new Scanner(System.in);
        String input = "";
        //cal.printCalendar("");
        while(!input.equalsIgnoreCase("Q"))
        {
            if(!input.equalsIgnoreCase("G"))
            {
                cal.printCalendar(input);
                System.out.println("Select One of the following options:");
                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
            }
            else if(!input.equalsIgnoreCase(""))
            {
                System.out.println("Select One of the following options:");
                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
            }
            System.out.printf("Enter: ");
            input = sc.nextLine();
            if(input.equalsIgnoreCase("L"))
            {
                String filename = "events.txt";
                cal.populate(filename);
            }
            else if(input.equalsIgnoreCase("V"))
            {
                System.out.println("[D]ay view or [M]onth view ?]");
                System.out.printf("Enter: ");
                String view = sc.nextLine();
                if(view.equalsIgnoreCase("D"))
                {
                    cal.printDay();
                    if(cal.hasEvent())
                    {
                        String date = cal.setDate();
                        cal.printEventsByDay(date);
                    }
                    String option = "";
                    while(!option.equalsIgnoreCase("M"))
                    {
                        System.out.println("[P]revious or [N]ext or [M]ain Menu ?");
                        System.out.printf("Enter: ");
                        option = sc.nextLine();
                        if(option.equalsIgnoreCase("P"))
                        {
                            cal.add(Calendar.DAY_OF_WEEK, -1);
                            cal.printDay();
                        }
                        else if(option.equalsIgnoreCase("N"))
                        {
                            cal.add(Calendar.DAY_OF_WEEK, 1);
                            cal.printDay();
                        }
                        if(cal.hasEvent())
                        {
                            String date = cal.setDate();
                            cal.printEventsByDay(date);
                        }
                    }
                }
                else if(view.equalsIgnoreCase("M"))
                {
<<<<<<< HEAD
                    cal.printCalendar("M");
                    String option = "";
=======
                    cal.printCalendar(input);
                    String option = "";
                    int counter = 0;
>>>>>>> 9d827fd139512c7651367a2c17e636ef8a185ed0
                    while(!option.equalsIgnoreCase("M"))
                    {
                        System.out.println("[P]revious or [N]ext or [M]ain Menu ?");
                        System.out.printf("Enter: ");
                        option = sc.nextLine();
                        if(option.equalsIgnoreCase("P"))
                        {
                            cal.add(Calendar.MONTH, -1);
<<<<<<< HEAD
                            cal.printCalendar("M");
                        }
                        if(option.equalsIgnoreCase("N"))
                        {
                            cal.add(Calendar.MONTH, 1);
                            cal.printCalendar("M");
                        }
=======
                            cal.printCalendar(input);
                        }
                        else if(option.equalsIgnoreCase("N"))
                        {
                            cal.add(Calendar.MONTH, 1);
                            cal.printCalendar(input);
                        }
                        else
                        {
                            cal.add(Calendar.MONTH, 8);
                            cal.printCalendar(input);
                        }
                        counter++;
>>>>>>> 9d827fd139512c7651367a2c17e636ef8a185ed0
                    }
                }
                else
                {
                    System.out.println("Incorrect Choice.");
                    input = "0";
                }
            }
            else if(input.equalsIgnoreCase("C"))
            {
                System.out.println("Title");
                System.out.printf("Enter: ");
                String title = sc.nextLine();
                System.out.println("Date MM/DD/YYYY");
                System.out.printf("Enter: ");
                String date = sc.nextLine();
                System.out.println("Enter Starting Time and Ending Time (Optional)");
                System.out.println("HH:MM - HH:MM");
                System.out.printf("Enter: ");
                String time = sc.nextLine();
                Events newEvent = new Events(title, date, time);
                cal.create(newEvent);
            }
            else if(input.equalsIgnoreCase("G"))
            {
                System.out.println("Please enter the date to view (MM/DD/YYYY)");
                System.out.printf("Enter: ");
                String date = sc.nextLine();
                ArrayList<Integer> difference = cal.goTo(date);
                cal.add(Calendar.YEAR, difference.get(0));
                cal.add(Calendar.MONTH, difference.get(1));
                cal.add(Calendar.DAY_OF_WEEK, difference.get(2));
                cal.printDay();
                if(cal.hasEvent())
                {
                    date = cal.setDate();
                    cal.printEventsByDay(date);
                }
            }
            else if(input.equalsIgnoreCase("E"))
            {
                cal.printAllEvents();
            }
            else if(input.equalsIgnoreCase("D"))
            {
                System.out.println("[S]elected Day or [A]ll ?");
                System.out.printf("Enter: ");
                String option = sc.nextLine();
                if(option.equalsIgnoreCase("S"))
                {
                    System.out.println("Enter the date.\n");
                    System.out.printf("Date (MM/DD/YYYY)");
                    System.out.printf("Enter: ");
                    String date = sc.nextLine();
                    cal.deleteDayEvent(date);
                }
                else if(option.equalsIgnoreCase("A"))
                {
                    cal.deleteCalEvent();
                }
                else
                {
                    System.out.println("Incorrect Choice.");
                    input = "0";
                }
            }
            else if(input.equalsIgnoreCase("Q"))
            {
                String filename = "events.txt";
                cal.printToFile(filename);
                System.out.println("All events have been saved to " + filename + ".");
                System.out.println("Have a Good Day!");
            }
            else
            {
                System.out.println("Incorrect Choice.");
                input = "0";
            }
            System.out.println("\n");
        }
        sc.close();
    }
}