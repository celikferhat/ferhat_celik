import java.util.Iterator;

/**
 * My iterator class
 */
public class SatalliteIterator implements Iterator {
    /**
     * 2d array
     */
    Integer [][] Data ;
    /**
     * x and y positions
     */
    int posx,posy = 0;
    /**
     * directions
     */
    int [][] dirs = {{1, 0},{0, 1}, {-1, 0}, {0, -1}};
    /**
     * direction position
     */
    int dir = 0;
    /**
     * some helper variables
     */
    int ex0 = 0;
    int ex1 = 0;
    int ey0 = 0;
    int ey1 = 0;
    int hn = 0;

    /**
     *
     * @param data Takes parameter as 2D array
     */
    public SatalliteIterator(Integer[][] data) {
        Data = data;
    }

    /**
     * Controls there is a next or not
     * @return if there is next element return true , else false
     */
    @Override
    public boolean hasNext() {

        return hn < Data.length * Data[0].length;
    }

    /**
     *
     * @return Returns next element
     */
    @Override
    public Object next() {
        int number = Data[posx][posy];
        hn ++;
        if(hn < Data.length * Data[0].length) {
            int x = dirs[dir][0];
            int y = dirs[dir][1];

            while (!check(posx + x, posy + y)) {
                dir = (dir + 1) % 4;
                x = dirs[dir][0];
                y = dirs[dir][1];


                switch (dir) {
                    case 0:
                        ex0++;
                        break;
                    case 1:
                        ey0++;
                        break;
                    case 2:
                        ex1++;
                        break;
                    case 3:
                        ey1++;
                        break;
                }


            }


            posx += dirs[dir][0];
            posy += dirs[dir][1];

        }
        return number;
     }

    /**
     * My helper function
     * @param x x position
     * @param y y position
     * @return controls next position is valid , if valid returns true , else returns false
     */
     private boolean check(int x , int y){

         return (x < (Data.length - ex1) && x >= (ex0) ) && (y < (Data[0].length - ey1) && y >= ey1);
     }


}
