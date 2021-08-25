import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class Represents a Cell in the GameOfLife
 **/
public class Cell extends JButton implements MouseListener {

    /** Color of the cell, when alive **/
    public static final Color COLOR_ALIVE = Color.BLACK;
    /** Color of the cell, when dead **/
    public static final Color COLOR_DEAD = Color.DARK_GRAY;

    /** Variable checks, whether this cell is alive **/
    private boolean isAlive;

    /** Constructor of a Cell **/
    public Cell() {
        isAlive = false;
        this.setBackground(COLOR_DEAD);
        this.addMouseListener(this);
    }

    /** Copy Constructor **/
    public Cell(final Cell other){
        this.isAlive = other.isAlive;
    }

    /**
     * Method return true, if this cell is alive
     *
     * @return isAlive
     */
    public boolean getAlive() {
        return isAlive;
    }

    /**
     * Setter for the variable isAlive
     *
     * @param value
     */
    public void setAlive(final boolean value) {
        isAlive = value;
    }

    /** Render this cell **/
    public void renderCell(){
        setBackground(isAlive ? COLOR_ALIVE : COLOR_DEAD);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(isAlive ? COLOR_DEAD : COLOR_ALIVE);
        isAlive = !isAlive;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // got this from stack overflow works good enough -> https://stackoverflow.com/questions/6828684/java-mouseevent-check-if-pressed-down
        if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0 ){
            this.setBackground(isAlive ? COLOR_DEAD : COLOR_ALIVE);
            isAlive = !isAlive;
        }
    }

    @Override
    public String toString(){
        return isAlive + " ";
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
