import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;

/**
 * Random cell is a nondeterministic cell that can't have a fixed copy.
 * It exhibits random behavior based on probability factors and interacts with its neighbors.
 *
 * This class represents a random cell in a simulation.
 * The cell's behavior is influenced by its neighbors and random factors.
 * It can be alive or dead, infected or non-infected, and may undergo mutations and cures.
 *
 * @author Mohammad Abdullah & Aryaman Mehta
 * @version 2022.01.06
 */
public class RandCell extends Cell {

    /**
     * Create a new Random Cell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param col The color of the cell.
     */
    public RandCell(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * This is how the Random Cell decides if it's alive or not based on a probability factor.
    * The cell's behavior is influenced by its neighbors and random factors.
    */
    public void act() {
        Random rand1 = Randomizer.getRandom();
        Random rand2 = Randomizer.getRandom();
        Random rand3 = Randomizer.getRandom();
        Random rand4 = Randomizer.getRandom();
        Random rand5 = Randomizer.getRandom();
        Random rand6 = Randomizer.getRandom();
        Random rand7 = Randomizer.getRandom();
        List<Cell> liveNeighbours = getField().getLivingNeighbours(getLocation());
        List<Cell> infectedNeighbours = getField().getInfectedNeighbours(getLocation());
        List<Cell> nonInfectedNeighbours = getField().getNonInfectedNeighbours(getLocation());
        setNextState(false);
        
        if (isAlive()) {
            if (liveNeighbours.size() >= 2 && !(getDiseaseState()) && rand1.nextDouble() <= 0.75) {
                setNextState(true);
                if (rand3.nextDouble() <= 0.05) {
                    double red = rand3.nextDouble();
                    double green = rand3.nextDouble();
                    double blue = rand3.nextDouble();
                    setColor(new Color(red, green, blue, 1.0));
                }
                if (infectedNeighbours.size() == 1 && rand5.nextDouble() <= 0.2) {
                    infect(true);
                    mutate();
                }
            }
            if (liveNeighbours.size() >= 4 && getDiseaseState() && rand4.nextDouble() <= 0.35) {
                setNextState(true);
                if (nonInfectedNeighbours.size() == 3 && rand6.nextDouble() <= 0.2) {
                    cure();
                }
            }
        }
        
        if (!isAlive()) {
            if (liveNeighbours.size() > 2 && rand2.nextDouble() <= 0.50) {
                setNextState(true);
                if (rand7.nextDouble() <= 0.35) {
                    cure();
                }
            }
        }
    }
    
    /**
     * Cures the cell of the disease.
     * Changes the cell's color to LIGHTSKYBLUE.
     */
    public void cure() {
        infect(false);
        setColor(Color.LIGHTSKYBLUE);
    }
}
