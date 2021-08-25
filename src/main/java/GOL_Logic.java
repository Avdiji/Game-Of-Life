import java.util.ArrayList;
import java.util.List;

/** Class is used to set the logic of the game of life **/
public class GOL_Logic {

    /** amount of cells in a column **/
    public static final int WIDTH_CELLS = 50;
    /** amount of cells in a row **/
    public static final int LENGTH_CELLS = 50;

    /** Variable contains all cells of the game of life **/
    private List<Cell> cells;

    /** Constructor **/
    public GOL_Logic(){
        initCells();
    }

    /**
     * getter for cells
     * @return cells
     */
    public List<Cell> getCells(){
        return cells;
    }

    /** Method initializes all the cells **/
    private void initCells(){
        cells = new ArrayList<>();

        for(int i = 0; i < WIDTH_CELLS * LENGTH_CELLS; ++i) {
            cells.add(new Cell());
        }
    }

    /**
     * Method return the cell at the index [i,j]
     * @param i index (row)
     * @param j index (column)
     * @return cell at index [i,j]
     */
    private Cell getCellAt(final int i, final int j){
        return cells.get((i * LENGTH_CELLS) + j);
    }

    /** Method returns amount of neighbours of the cell
     *
     * @param i index (row)
     * @param j index (column)
     * @return amount of neighbours cell currently has
     */
    private int getAmountNeighbours(final int i , final int j){
        int result = 0;

        int iUpper = i == 0 ? LENGTH_CELLS - 1 : i - 1;
        int iLower = i == LENGTH_CELLS - 1 ? 0 : i + 1;
        int jLeft = j == 0 ? WIDTH_CELLS - 1 : j - 1;
        int jRight = j == WIDTH_CELLS - 1 ? 0 : j + 1;

        result += getCellAt(iUpper, j).getAlive() ? 1 : 0;
        result += getCellAt(iLower, j).getAlive() ? 1 : 0;
        result += getCellAt(i, jLeft).getAlive() ? 1 : 0;
        result += getCellAt(i, jRight).getAlive() ? 1 : 0;
        result += getCellAt(iUpper, jLeft).getAlive() ? 1 : 0;
        result += getCellAt(iUpper, jRight).getAlive() ? 1 : 0;
        result += getCellAt(iLower, jLeft).getAlive() ? 1 : 0;
        result += getCellAt(iLower, jRight).getAlive() ? 1 : 0;

        return result;
    }

    /** Method generates the next iteration in the game of life **/
    public void generateNextIteration(){
        int neighbourCounter;
        Cell tmp;
        List<Cell> kill = new ArrayList<>(); // list of cells that need to die
        List<Cell> live = new ArrayList<>(); // list of cells that are allowed to live

        // for each cell
        for(int i = 0 ; i < LENGTH_CELLS; ++i){
            for(int j = 0; j < WIDTH_CELLS; ++j){
                // get cell and alive neighbours of cell
                tmp = getCellAt(i, j);
                neighbourCounter = getAmountNeighbours(i, j);

                // check whether the cell is alive
                if(tmp.getAlive()){
                    // kill the cell if needed
                    if(neighbourCounter < 2){
                        kill.add(getCellAt(i, j));
                    }else if(neighbourCounter > 3){
                        kill.add(getCellAt(i, j));
                    }
                // check whether the cell is dead
                }else{
                    // create a new cell if needed
                    if(neighbourCounter == 3){
                        live.add(getCellAt(i, j));
                    }
                }
            }
        }
        kill.stream().forEach(cell -> {
            cell.setAlive(false);
            cell.setBackground(Cell.COLOR_DEAD);
        });
        live.stream().forEach(cell -> {
            cell.setAlive(true);
            cell.setBackground(Cell.COLOR_ALIVE);
        });
    }

    /** Method renders the field **/
    public void renderField(){
        cells.forEach(cell -> cell.setAlive(false));
        cells.forEach(Cell::renderCell);
    }

}
