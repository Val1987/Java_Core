package Lesson_09;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №9 (Stream API)
 */


import java.util.*;

public class HW_09_Compare implements Comparator<HW_09_Student> {

    public int compare (HW_09_Student a, HW_09_Student b) {
        int c1 = a.getAllCourses().size();
        int c2 = b.getAllCourses().size();
        return c2 - c1;
    }

}
