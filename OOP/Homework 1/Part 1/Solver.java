public abstract class Solver {

    protected SolveMedhod solveMedhod;

    public Solver(){
    }

    public void PerformSolve(double [][] A , double[] B){
        solveMedhod.solve(A,B);
    }

}
