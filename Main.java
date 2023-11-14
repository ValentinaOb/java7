import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
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
        person1[] person1 = new person1[num];
        Thread[] thr = new Thread[2];

        thr[0] = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < num; i++) {
                    person[i] = new person(width, height, i);
                    person[i].move();
                    System.out.println("\nPerson" + (i + 1) + ": " + person[i]);
                }
            }
        });

        thr[1] = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < num; i++) {
                    person1[i] = new person1(width, height, i);
                    person1[i].move();
                    System.out.println("\nPerson" + (i + 1) + ": " + person1[i]);
                }
            }
        });

        for (int i = 0; i < 2; i++) {
            thr[i].start();
        }

        for (int i = 0; i < 2; i++) {
            try {
                thr[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main2(String[] args) throws IOException {

        ArrayList<Document> d = new ArrayList<Document>();
        ArrayList<Series> ser = new ArrayList<Series>();
        Thread[] thr = new Thread[2];

        Scanner s = new Scanner(new File("1.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        Scanner s1 = new Scanner(new File("2.txt"));
        while (s1.hasNext()) {
            list.add(s1.next());
        }
        Scanner s2 = new Scanner(new File("3.txt"));
        while (s2.hasNext()) {
            list.add(s2.next());
        }

        //

        Scanner ss = new Scanner(new File("11.txt"));
        ArrayList<String> l = new ArrayList<String>();
        while (ss.hasNext()) {
            l.add(ss.next());
        }
        Scanner s11 = new Scanner(new File("22.txt"));
        while (s11.hasNext()) {
            l.add(s11.next());
        }

        thr[0] = new Thread(new Runnable() {
            public void run() {

                System.out.println("\nList: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i) + " ");
                }

                System.out.println("\n");

                String name = list.get(0);
                String name1 = list.get(1);
                String name2 = list.get(2);

                int id = Integer.parseInt(list.get(3));
                int id1 = Integer.parseInt(list.get(4));
                int id2 = Integer.parseInt(list.get(5));

                String rec = list.get(6);
                String rec1 = list.get(7);
                String rec2 = list.get(8);

                String pr = list.get(9);
                String pr1 = list.get(10);
                String pr2 = list.get(11);

                int sum = Integer.parseInt(list.get(12));
                int sum1 = Integer.parseInt(list.get(13));
                int sum2 = Integer.parseInt(list.get(14));

                d.add(new Kvut(name, id, rec));
                d.add(new Kvut(name1, id1, rec1));
                d.add(new Kvut(name2, id2, rec2));
                d.add(new Nakl(name, id, pr));
                d.add(new Nakl(name1, id1, pr1));
                d.add(new Nakl(name2, id2, pr2));
                d.add(new Pax(name, id, sum));
                d.add(new Pax(name1, id1, sum1));
                d.add(new Pax(name2, id2, sum2));

                BaseAI a = new Doc(d, ser);
                a.start();
            }
        });
        //

        thr[1] = new Thread(new Runnable() {
            public void run() {

                System.out.println("\nListS: ");
                for (int i = 0; i < l.size(); i++) {
                    System.out.print(l.get(i) + " ");
                }

                System.out.println("\n");

                int aa = Integer.parseInt(l.get(0));
                int aa1 = Integer.parseInt(l.get(1));

                ser.add(new Linear(aa));
                ser.add(new Linear(aa1));
                ser.add(new Exponential(aa));
                ser.add(new Exponential(aa1));

                BaseAI b = new Ser(ser, d);
                b.start();
            }
        });

        for (int i = 0; i < 2; i++) {
            thr[i].start();
        }

        for (int i = 0; i < 2; i++) {
            try {
                thr[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

class Document {

    String name;
    int id;

    public String Name() {
        return name;
    }

    public int id_doc() {
        return id;
    }

    public Document(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void Show() {
        System.out.printf("Document: %d %s \n", id, name);
    }

    public void sort(Comparator<Document> com) {
    }
}

class Kvut extends Document {

    String recognition;

    public Kvut(String name, int id, String recognition) {

        super(name, id);
        this.recognition = recognition;
    }

    public String getRec() {
        return recognition;
    }

    public void Show() {

        System.out.printf("%d Document %s Kvut %s  \n", super.id, super.Name(), recognition);
    }

}

class Nakl extends Document {

    String product;

    public Nakl(String name, int id, String product) {

        super(name, id);
        this.product = product;
    }

    public String getPr() {
        return product;
    }

    public void Show() {
        System.out.printf("%d Document %s Nakl %s\n", super.id, super.Name(), product);
    }
}

class Pax extends Document {

    int sum;

    public Pax(String name, int id, int sum) {

        super(name, id);
        this.sum = sum;
    }

    public int getPax() {
        return sum;
    }

    public void Show() {
        System.out.printf("%d Document %s Pax %d \n\n", super.id, super.Name(), sum);
    }

}

abstract class Series {

    public int id;

    public Series(int id) {
        this.id = id;
    }

    public abstract int pow(int a, int b);

    public abstract String toStr();

    public void sort(Comparator<Series> com2) {
    }

}

class Linear extends Series {

    int part = 0;
    int sum = 0;
    int[] a = { 1, 2, 3, 4, 5 };
    int d;
    int p = 1;
    int n = 3; // pos

    public Linear(int id) {
        super(id);
        d = a[1] - a[0];
        part = a[0] + d * (n - 1);
        sum = ((a[0] + part) / 2) * n;
    }

    public String toStr() {
        return "\nId: " + super.id + "\nLp: " + part + "\nLs: " + sum;
    }

    public int pow(int a, int b) {
        for (int i = 1; i <= b; i++) {
            p = p * a;
        }
        return p;
    }
}

class Exponential extends Series {

    int part = 0;
    int sum = 0;
    int[] b = { 3, 9, 27 };
    int q = 0;
    int n = 2;

    public Exponential(int id) {
        super(id);
        q = b[1] / b[0];
        part = b[0] * pow(q, (n));
        sum = (b[0] * (pow(q, n + 1) - 1)) / (q - 1);
    }

    public String toStr() {
        return "\nEp: " + this.part + "\nEs: " + this.sum + "\n";
    }

    public int pow(int a, int b) {
        int p = 1;
        for (int i = 0; i < b; i++) {
            p = p * a;
        }
        return p;
    }
}

abstract class BaseAI extends Thread {

    public ArrayList<Document> d;
    public ArrayList<Series> s;

    public BaseAI(ArrayList<Document> d, ArrayList<Series> s) {
        this.d = d;
        this.s = s;
    }

    public void run() {

    }
}

class Doc extends BaseAI {

    public Doc(ArrayList<Document> d, ArrayList<Series> s) {
        super(d, s);
    }

    public void run() {

        for (Document d : d) {
            if (d instanceof Document) {

                Comparator<Document> com = new Comparator<Document>() {
                    public int compare(Document i, Document j) {
                        if (i.name == j.name) {
                            return 0;
                        } else
                            return 1;

                    }
                };

                Comparator<Document> com1 = new Comparator<Document>() {
                    public int compare(Document i, Document j) {
                        if (i.id > j.id) {
                            return 1;
                        } else if (i.id < j.id) {
                            return -1;
                        } else
                            return 0;
                    }
                };

                d.sort(com);
                System.out.println("\nL:\n");
                d.Show();

                d.sort(com1);

                System.out.println("\n\nL: ");
                d.Show();

                System.out.println("\n");

            }
        }
    }

}

class Ser extends BaseAI {

    public Ser(ArrayList<Series> s, ArrayList<Document> d) {
        super(d, s);
    }

    public void run() {

        for (Series s : s) {
            if (s instanceof Series) {

                System.out.println("\n\nLS: ");
                System.out.printf(s.toStr());

                Comparator<Series> com2 = new Comparator<Series>() {
                    public int compare(Series i, Series j) {
                        if (i.id > j.id) {
                            return 1;
                        } else if (i.id < j.id) {
                            return -1;
                        } else
                            return 0;
                    }
                };

                s.sort(com2);

                System.out.println("\n\nLS: ");
                System.out.printf(s.toStr());

                System.out.print("\n\n");

            }
        }
    }

}
