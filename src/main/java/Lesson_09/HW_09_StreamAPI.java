package Lesson_09;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №9 (Stream API)
 */

/*
 *   3)  Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
 *   которые посещают этот курс. (Нихера не понял! Но очень интересно! - Что это за формулировка задания?
 *   - Вот что это - "...принимающую на вход список Student и экземпляр Course" - извините, но я просто не понимаю
 *   постановку задачи!
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HW_09_StreamAPI {

    public static void main(String[] args) {

        ArrayList<String> courses = new ArrayList<String>();
        Collections.addAll(courses, "Java", "C#", "Python", "HTML");

        ArrayList<HW_09_Student> students = new ArrayList<HW_09_Student>();
        Collections.addAll(students, new HW_09_Student("Basilio"), new HW_09_Student("Jorge"), new HW_09_Student("Miguel"), new HW_09_Student("Julio"),
                new HW_09_Student("Jesus"), new HW_09_Student("Alejandro"), new HW_09_Student("Maria"), new HW_09_Student("Inessa"));
        students.get(0).addCourses(courses, new String[] {"Java", "Python", "C#", "HTML"});
        students.get(1).addCourses(courses, new String[] {"Java", "HTML"});
        students.get(2).addCourses(courses, new String[] {"HTML"});
        students.get(3).addCourses(courses, new String[] {"Python", "C#"});
        students.get(4).addCourses(courses, new String[] {"C#", "HTML"});
        students.get(5).addCourses(courses, new String[] {"HTML", "Python"});
        students.get(6).addCourses(courses, new String[] {"Java", "C#", "HTML"});
        students.get(7).addCourses(courses, new String[] {"Java", "C#", "Python", "HTML"});

        printCourse(students);
        System.out.println("\n-----------More Interested Students------------\n");
        interested(students);

    }

    public static void printCourse(ArrayList<HW_09_Student> students) {
        for(HW_09_Student student: students) {
            List<String> courses = student.getAllCourses();
            StringBuffer coursesBuff = new StringBuffer();
            for(String course: courses) {
                coursesBuff.append(course);
                coursesBuff.append(" | ");
            }
            String sc = coursesBuff.substring(0, coursesBuff.length()-3);
            System.out.println("Student " + student.getName() + " Course " + sc);
        }
    }

    public static void interested(List<HW_09_Student> students) {
        students.stream().sorted(new HW_09_Compare()).limit(3).forEach(s -> System.out.println(s.getName()));
    }

}
