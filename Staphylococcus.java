import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;

/**
 * This cell evolves over time and changes behavior as generations pass.
 *
 * This class represents a Staphylococcus cell in a simulation.
 * The cell's behavior is influenced by its neighbors, disease state, and generation count.
 * It evolves through different stages with varying rules for each stage.
 * It can also interact with its neighbors and potentially revive Mycoplasma cells.
 *
 * @author Mohammad Abdullah & Aryaman Mehta
 * @version 2022.01.06
 */

public class Staphylococcus extends Cell {

    int genCount=1;
    private boolean hasRevivedMycoplasma;
    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Staphylococcus(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Staphylococcus decides if it's alive or not and evolves over time.
     * The cell's behavior is influenced by its neighbors, disease state, and generation count.
     */
    public void act() {
        List<Cell> liveNeighbours = getField().getLivingNeighbours(getLocation());
        List<Cell> deadNeighbours = getField().getDeadNeighbours(getLocation());
        List<Cell> infectedNeighbours = getField().getInfectedNeighbours(getLocation());
        List<Cell> nonInfectedNeighbours = getField().getNonInfectedNeighbours(getLocation());
        int sameTypeNeighbours= countSameTypeNeighbours(liveNeighbours);
        Random rand = Randomizer.getRandom();
        setNextState(false);

        if (isAlive() && !(getDiseaseState())){ 
            if (genCount<20){
                if (sameTypeNeighbours >= 2 || liveNeighbours.size()>3){
                    setNextState(true);
                }
                if (infectedNeighbours.size()==1){
                    infect(true);
                    mutate();
                }
            }

            if (genCount>=20 && genCount<45 ){
                if (sameTypeNeighbours >= 3 || liveNeighbours.size()>4){
                    setNextState(true);
                }
                if (infectedNeighbours.size()==1){
                    infect(true);
                    mutate();
                }
            } 

            if (genCount>=45 && genCount<65){
                if (sameTypeNeighbours >= 4 || liveNeighbours.size()>5){
                    setNextState(true);
                }
                if (infectedNeighbours.size()==1){
                    infect(true);
                    mutate();
                }
            } 

            if (genCount>=65 && genCount<95){
                if (sameTypeNeighbours >= 5 && liveNeighbours.size()>6){
                    setNextState(true);
                }
                if (infectedNeighbours.size()==1){
                    infect(true);
                    mutate();
                }
            }

            if (genCount>=95){
                if (sameTypeNeighbours >= 6 && liveNeighbours.size()>7){
                    setNextState(true);
                }
                if (infectedNeighbours.size()==1){
                    infect(true);
                    mutate();
                }
            }
        }

        if ( isAlive() && getDiseaseState()){
            if (genCount<20){
                if (sameTypeNeighbours >= 6 || liveNeighbours.size()>7){
                    setNextState(true);
                }
                if (nonInfectedNeighbours.size()==2){
                    cure();
                }
            }

            if (genCount>=20 && genCount<45 ){
                if (sameTypeNeighbours >= 5 || liveNeighbours.size()>6){
                    setNextState(true);
                }
                if (nonInfectedNeighbours.size()==3){
                    cure();
                }
            } 

            if (genCount>=45 && genCount<65){
                if (sameTypeNeighbours >= 4 || liveNeighbours.size()>5){
                    setNextState(true);
                }
                if (nonInfectedNeighbours.size()==4){
                    cure();
                }
            } 

            if (genCount>=65 && genCount<95){
                if (sameTypeNeighbours >= 3 && liveNeighbours.size()>4){
                    setNextState(true);
                }
                if (nonInfectedNeighbours.size()==5){
                    cure();
                }
            }

            if (genCount>=95){
                if (sameTypeNeighbours >= 2 && liveNeighbours.size()>3){
                    setNextState(true);
                }
                if (nonInfectedNeighbours.size()==6){
                    cure();
                }
            }
        }

        if (genCount>= 20 && genCount % 10 == 0) {
            if (sameTypeNeighbours >= 5) {
                setNextState(false); // If overcrowded, the cell dies
            }
        }

        if (!isAlive()){
            if (liveNeighbours.size() >= 3 && deadNeighbours.size()>=2){
                setNextState(true);
                if (rand.nextDouble()<=0.5){
                    cure();
                }
            }

            if  (deadNeighbours.size()==8){
                setNextState(true);
                if (rand.nextDouble()<=0.35){
                    cure();
                }
            }
        }

        for (Cell neighbor : liveNeighbours) {
            interact(neighbor);
        }

        genCount++;
    }

    /**
     * Count the number of live neighbors of the same type as this Staphylococcus cell.
     *
     * @param liveNeighbours The list of live neighbors.
     * @return The count of live neighbors of the same type.
     */
    private int countSameTypeNeighbours(List<Cell> liveNeighbours) {
        int count = 0;
        for (Cell neighbor : liveNeighbours) {
            if (neighbor instanceof Staphylococcus) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if the cell has revived Mycoplasma.
     *
     * @return True if the cell has revived Mycoplasma.
     */
    public boolean hasRevivedMycoplasma()
    {
        hasRevivedMycoplasma = true;
        return hasRevivedMycoplasma;
    }
    
    /**
     * cures the cell of the disease
     */
    public void cure() {
        infect(false);
        setColor(Color.MEDIUMAQUAMARINE);
    }
} 