import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ex: ");

        try (Scanner in = new Scanner(System.in)) {
            int a = in.nextInt();

            switch (a) {
                case 1:
                    main1(args);
                    break;
                case 2:
                    main2(args);
            }
        }
    }

    public static void main1(String[] args) {
        int width = 400;
        int height = 200;
        int num = 5;

        person[] person = new person[num];

        for (int i = 0; i < num; i++) {
            person[i] = new person(width, height, i);
            person[i].move();
            System.out.println("\nPerson" + (i + 1) + ": " + person[i]);
        }
    }

    public static void main2(String[] args) {
        int width = 400;
        int height = 200;
        int num = 5;

        person1[] person1 = new person1[num];

        for (int i = 0; i < num; i++) {
            person1[i] = new person1(width, height, i);
            person1[i].move();
            System.out.println("\nPerson" + (i + 1) + ": " + person1[i] + "\n");
        }
    }
}

class person {
    int x;
    int y;
    int x1;
    int y1;
    boolean point = false;

    public person(int w, int h, int i) {
        Random random = new Random();
        x = random.nextInt(w - (w / 2)) + (w / 2);
        y = random.nextInt(h - (h / 2)) + (h / 2);

        x1 = random.nextInt(w / 2);
        y1 = random.nextInt(h / 2);

        System.out.println("\n\n" + (i + 1) + ". X: " + x + " Y: " + y + "\n" + "X1: " + x1 + " Y1: " + y1 + "\n\n");
    }

    public void move() {
        if (!point) {

            while (x != x1 || y != y1) {
                if (x < x1) {
                    x++;
                } else if (x > x1) {
                    x--;
                }
                if (y < y1) {
                    y++;
                } else if (y > y1) {
                    y--;
                }
                System.out.println("(" + x + ", " + y + ")");
            }
            point = true;
        }
    }

    public String toString() {
        return "Position: (" + x + ", " + y + ")";
    }
}

class person1 {
    int x;
    int y;
    int x1;
    int y1;
    boolean point = false;

    public person1(int w, int h, int i) {
        Random random = new Random();
        x = random.nextInt(w / 2);
        y = random.nextInt(h / 2);

        x1 = random.nextInt(w - (w / 2)) + (w / 2);
        y1 = random.nextInt(h - (h / 2)) + (h / 2);

        System.out.println("\n\n" + (i + 1) + ". X: " + x + " Y: " + y + "\n" + "X1: " + x1 + " Y1: " + y1 + "\n\n");
    }

    public void move() {
        if (!point) {

            while (x != x1 || y != y1) {
                if (x < x1) {
                    x++;
                } else if (x > x1) {
                    x--;
                }
                if (y < y1) {
                    y++;
                } else if (y > y1) {
                    y--;
                }
                System.out.println("(" + x + ", " + y + ")");
            }
            point = true;
        }
    }

    public String toString() {
        return "Position: (" + x + ", " + y + ")";
    }
}