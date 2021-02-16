import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        traffic_lights lights = new traffic_lights();
        HiTech tech = new HiTech();
        lights.setObservable(tech);

    while (true) {
        System.out.println("\n1- Run Traffic Lights");
        System.out.println("2- Change Traffic Jam");
        System.out.println("3- Exit\n");
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                lights.run_lights();
                break;
            case 2:
                System.out.println("1- Create Jam\n2-Remove Jam\n");
                int ch = sc.nextInt();
                if (ch == 1)
                    tech.changeDetected(true);
                else if (ch == 2)
                    tech.changeDetected(false);
                break;
            case 3:
                System.exit(1);
                break;
        }
    }

    }
}
