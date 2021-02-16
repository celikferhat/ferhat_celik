import java.util.Scanner;

public class Main {


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter number of variables");
        int N = scan.nextInt();

        double[] B = new double[N];
        double[][] A = new double[N][N];
        System.out.println("\nEnter "+ N +" equations coefficients ");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = scan.nextDouble();

        System.out.println("\nEnter "+ N +" solutions");
        for (int i = 0; i < N; i++)
            B[i] = scan.nextDouble();

        LinearSolver linearSolver = new LinearSolver();
        linearSolver.PerformSolve(A,B);
        linearSolver.ChangeSolveMethod(new MatrixInversion()); // Default method Gaussian Elimination
        linearSolver.PerformSolve(A,B);
    }


}
