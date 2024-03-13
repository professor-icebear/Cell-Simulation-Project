import javafx.scene.paint.Color; 
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * This abstract class defines the common properties and behaviors for different types of cells in a life simulation.
 * It includes methods for determining the cell's state, updating its state, setting color, getting location, and interacting with other cells.
 *
 * @author David J. Barnes, Michael Kölling, Jeffery Raphael, Mohammad Abdullah & Aryaman Mehta
 * @version 2022.01.06
 */
public abstract class Cell {

    private boolean alive;    
    private boolean nextAlive; // The state of the cell in the next iteration
    private boolean diseased;
    private Field field;
    private Location location;
    private Color color = Color.WHITE;
    private int age;

    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param col The initial color of the cell.
     */
    public Cell(Field field, Location location, Color col) {
        alive = true;
        nextAlive = false;
        diseased = false;
        this.field = field;
        setLocation(location);
        setColor(col);
        age = 0;
    }

    /**
     * Make this cell act - that is: the cell decides its status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
        nextAlive = value;
    }

    /**
     * Changes the state of the cell.
     */
    public void updateState() {
        alive = nextAlive;
    }

    /**
     * Changes the color of the cell.
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Returns the cell's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }

    /**
     * Returns the cell's infection status.
     */
    public boolean getDiseaseState() {
        return diseased;
    }

    /**
     * Infects the cell with the disease.
     */
    public void infect(Boolean bool) {
        diseased = bool;
    }

    /**
     * The cell mutates by changing its color to black.
     */
    public void mutate() {
        setColor(Color.BLACK);
    }

    /**
     * Interacts with another cell.
     * @param otherCell The cell with which interaction occurs.
     */
    public void interact(Cell otherCell) 
    {
        if (otherCell.isAlive())
        {
            if (this instanceof Mycoplasma && otherCell instanceof Staphylococcus) 
            {
                Mycoplasma mycoplasma = (Mycoplasma) this;
                Staphylococcus staph = (Staphylococcus) otherCell;

                // Check if the Mycoplasma cell is alive and can cure the Staphylococcus cell
                if (mycoplasma.isAlive()) {
                    staph.cure();
                    setNextState(true);
                }
            }
            // Staphylococcus reviving dead Mycoplasma cells
            else if (this instanceof Staphylococcus && otherCell instanceof Mycoplasma) {
                Staphylococcus staph = (Staphylococcus) this;
                Mycoplasma mycoplasma = (Mycoplasma) otherCell;

                // Check if the Staphylococcus cell is alive and has revived Mycoplasma cells
                if (staph.isAlive() && staph.hasRevivedMycoplasma()) {
                    mycoplasma.revive();
                    setNextState(true);
                }
            }
        }
    }
}
