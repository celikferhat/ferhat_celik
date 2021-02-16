public class LinearSolver extends  Solver{

    public LinearSolver(){
        solveMedhod = new GaussianElimination();
    }

    public void ChangeSolveMethod(SolveMedhod newmethod){
        solveMedhod = newmethod;
    }

}
