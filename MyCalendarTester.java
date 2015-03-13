/**
 COPYRIGHT (C) 2015 Quoc Doan. All Rights Reserved.
 Class to run MyCalendar program.
 Solves CS151 homework assignment #2
 @author Quoc Doan
 @version 1.02 2015/3/10
 */

import java.io.FileNotFoundException;
import java.util.*;

public class MyCalendarTester
{
    public static void main(String[] args) throws FileNotFoundException
    {
        HashMap<String, ArrayList<Events>> hm = new HashMap<String, ArrayList<Events>>();
        MyCalendar cal = new MyCalendar();
        cal.setMap(hm);
        Display dis = new Display(cal);
        dis.show();
    }
}