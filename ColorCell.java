import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;

/**
 * Color cell is a cell that changes color depending on the generation and has a bunch of other rules related to its color properties.
 *
 * This class represents a color cell in a simulation.
 * The cell's behavior is determined by rules related to its color properties, generation, and interaction with neighbors.
 *
 * @author Mohammad Abdullah & Aryaman Mehta
 * @version 2022.01.06
 */
public class ColorCell extends Cell {

    int genCount = 1;

    /**
     * Create a new Color Cell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param col The color of the cell.
     */
    public ColorCell(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * This is how the Color Cell decides if it's alive or not.
    * The cell's behavior is influenced by its neighbors, disease state, and generation count.
    */
    public void act() {
        List<Cell> liveNeighbours = getField().getLivingNeighbours(getLocation());
        List<Cell> infectedNeighbours = getField().getInfectedNeighbours(getLocation());
        List<Cell> nonInfectedNeighbours = getField().getNonInfectedNeighbours(getLocation());
        Random rand = Randomizer.getRandom();
        setNextState(false);
        
        if (isAlive()) {
            if (!(getDiseaseState())) {
                if (liveNeighbours.size() == 1 || liveNeighbours.size() == 5) {
                    setNextState(true);
                    if (genCount % 2 == 0) {
                        setColor(Color.SLATEBLUE);
                    }
                    if (!(genCount % 2 == 0)) {
                        setColor(Color.SALMON);
                    }
                    if (infectedNeighbours.size() == 1) {
                        infect(true);
                        mutate();
                    }
                }
            }
            if (getDiseaseState()) {
                if (liveNeighbours.size() == 2 || liveNeighbours.size() == 6) {
                    setNextState(true);
                    if (genCount % 2 == 0) {
                        setColor(Color.LIMEGREEN);
                    }
                    if (!(genCount % 2 == 0)) {
                        setColor(Color.SALMON);
                    }
                    if (nonInfectedNeighbours.size() == 5) {
                        cure();
                    }
                }
            }
        }
    
        if (!isAlive()) {
            if (liveNeighbours.size() >= 3) {
                setNextState(true);
                if (rand.nextDouble() <= 0.35) {
                    cure();
                }
            }
        }
        
        genCount++;
    }
    
    /**
     * Cures the cell of the disease and changes its color based on the generation count.
     */
    public void cure() {
        infect(false);
        if (genCount % 2 == 0) {
            setColor(Color.SLATEBLUE);
        } else {
            setColor(Color.SALMON);
        }
    }
}
