package Lesson_09;

/*
 *   Тигашов Валерий Евгеньевич
 *   Курс - Java Core
 *   Урок №9 (Stream API)
 */

import java.util.ArrayList;
import java.util.List;

public class HW_09_Student implements Student {

    private String student;
    private List<String> courses;

    public HW_09_Student(String student) {
        this.student = student;
        courses = new ArrayList<String>();
    }

    public void addCourses(List<String> list1, String[] courses) {
        for (int i = 0; i < courses.length; i++) {
            int index = list1.indexOf(courses[i]);
            if(index >= 0) this.courses.add(list1.get(index));
        }
    }

    @Override
    public String getName() {
        return student;
    }

    @Override
    public List<String> getAllCourses() {
        return courses;
    }

}
