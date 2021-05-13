package Lesson_01;

/*
    *   Тигашов Валерий Евгеньевич
    *   Java Core
    *   Урок №1 (Класс Object)
 */

/*
    *   Разобраться с имеющимся кодом.
    *   Добавить класс Team, который будет содержать:
    *   -   название команды;
    *   -   массив из четырех участников — в конструкторе можно сразу всех участников указывать);
    *   -   метод для вывода информации о членах команды, прошедших дистанцию;
    *   -   метод вывода информации обо всех членах команды.
    *   Добавить класс Course (полоса препятствий), в котором будут находиться:
    *   -   массив препятствий;
    *   -   метод, который будет просить команду пройти всю полосу.
 */

public class HW_01_Competition {

    public static void main(String[] args) {

        Team firstTeam = new Team("Cats", "cat1", "cat2", "cat3", "cat4");
        firstTeam.getInfoTeam();
        System.out.println("--------------------------------");
        Team secondTeam = new Team("Dogs", "dog1", "dog2", "dog3", "dog4");
        secondTeam.getInfoTeam();
        System.out.println("--------------------------------");
        Course course = new Course("Treadmill", "wall", "ford", "springboard");
        course.doIt(firstTeam);
        course.doIt(secondTeam);
        firstTeam.getResults();
        secondTeam.getResults();

    }

}

class Team {
    String name;
    String[] member;
    Team(String name, String member1, String member2, String member3, String member4) {
        this.name = name;
        member = new String[4];
        member[0] = member1;
        member[1] = member2;
        member[2] = member3;
        member[3] = member4;
        Results = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Results[i][j] = true;
            }
        }
    }

    boolean[][] Results;

    void getResults() {
        System.out.println("----------Team: " + name + " -----------");
        for (int i = 0; i < 4; i++) {
            System.out.println(member[i] + " | " + memberResult(i, 0) + " | " + memberResult(i, 1)
                    + " | " + memberResult(i, 2) + " | " + memberResult(i, 3));
        }
    }

    private String memberResult (int member, int block) {
        if (Results[member][block])
            return "Pass";
        else return "    ";
    }

    void getInfoTeam() {
        System.out.println("Team: " + name);
        System.out.println("Members: " + member[0] + ", " + member[1] + ", " + member[2] + ", " + member[3] + ".");
    }
}

class Course {
    String[] blocks;
    Course(String block1, String block2, String block3, String block4) {
        blocks = new String[4];
        blocks[0] = block1;
        blocks[1] = block2;
        blocks[2] = block3;
        blocks[3] = block4;
    }

    void doIt(Team t1) {
        System.out.println("Team " + t1.name + " passes an obstacle course");
    }

}

