import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class Represents a Cell in the GameOfLife
 **/
public class Cell extends JButton implements MouseListener {

    /**
     * Blue color scheme
     **/
    public static final Color COLOR_BLUE_ALIVE = new Color(0x03254c);
    public static final Color COLOR_BLUE_DEAD = new Color(0x1167b1);
    public static final Color COLOR_BLUE_BACKGROUND = new Color(0x7DB1FC);

    /**
     * Red color scheme
     **/
    public static final Color COLOR_RED_ALIVE = new Color(0x8E0000);
    public static final Color COLOR_RED_DEAD = new Color(0xFF4F4F);
    public static final Color COLOR_RED_BACKGROUND = new Color(0xFFBEBE);

    /**
     * Light color scheme
     **/
    public static final Color COLOR_LIGHT_ALIVE = new Color(0x828282);
    public static final Color COLOR_LIGHT_DEAD = new Color(0xdedede);
    public static final Color COLOR_LIGHT_BACKGROUND = Color.WHITE;

    /**
     * Dark color scheme
     **/
    public static final Color COLOR_DARK_ALIVE = Color.BLACK;
    public static final Color COLOR_DARK_DEAD = new Color(0x444444);
    public static final Color COLOR_DARK_BACKGROUND = new Color(0x2c2c2c);

    /**
     * Variable checks, whether this cell is alive
     **/
    private boolean isAlive;

    /**
     * Currently selected color schemes
     **/
    private Color current_color_alive;
    private Color current_color_dead;

    /**
     * Constructor of a Cell
     **/
    public Cell() {
        current_color_alive = COLOR_LIGHT_ALIVE;
        current_color_dead = COLOR_LIGHT_DEAD;

        isAlive = false;
        this.setBackground(current_color_dead);
        this.addMouseListener(this);
    }

    /**
     * Copy Constructor
     **/
    public Cell(final Cell other) {
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

    /**
     * Getter for {@link #current_color_alive}
     *
     * @return current_color_alive
     */
    public Color getCurrent_color_alive() {
        return current_color_alive;
    }

    /**
     * Getter for {@link #current_color_dead}
     *
     * @return current_color_dead
     */
    public Color getCurrent_color_dead() {
        return current_color_dead;
    }

    /**
     * Setter for {@link #current_color_alive}
     *
     * @param color
     */
    public void setCurrent_color_alive(final Color color) {
        current_color_alive = color;
    }

    /**
     * Setter for {@link #current_color_dead}
     *
     * @param color
     */
    public void setCurrent_color_dead(final Color color) {
        current_color_dead = color;
    }

    /**
     * Render this cell
     **/
    public void renderCell() {
        setBackground(isAlive ? current_color_alive : current_color_dead);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(isAlive ? current_color_dead : current_color_alive);
        isAlive = !isAlive;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // got this from stack overflow works good enough -> https://stackoverflow.com/questions/6828684/java-mouseevent-check-if-pressed-down
        if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
            this.setBackground(isAlive ? current_color_dead : current_color_alive);
            isAlive = !isAlive;
        }
    }

    @Override
    public String toString() {
        return isAlive + " ";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
