import Jama.LUDecomposition;
import Jama.Matrix;
public class MatrixInversion implements SolveMedhod{

    public MatrixInversion(){

    }

    @Override
    public void solve(double [][] A , double[] B) {
        Matrix a = new Matrix(A);
        Matrix b = new Matrix(B, B.length);
        LUDecomposition luDecomposition = new LUDecomposition(a);
        Matrix x = luDecomposition.solve(b);
        System.out.print("Solution:");
        x.print(5, 3);
    }

}
