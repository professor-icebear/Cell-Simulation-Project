import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author Mohammad Abdullah & Aryaman Mehta
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {

    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
    public void act() {
        List<Cell> liveNeighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);

        if (isAlive()) {
            if (liveNeighbours.size() == 2 || liveNeighbours.size() == 3){
                setNextState(true);
            }
            for (Cell neighbor : liveNeighbours)
            {
                interact(neighbor);
            }
        }

        if (!isAlive()) {
            if (liveNeighbours.size() == 3){
                setNextState(true);
            }
        }
    }
    
    /**
     * Revive the Mycoplasma cell.
     */
    public void revive() {
        setNextState(true);
        setColor(Color.ORANGE);
    }
}