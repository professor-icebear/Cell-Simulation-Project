import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 */

public class Simulator {

    private static final double MYCOPLASMA_ALIVE_PROB = 0.25;
    private static final double STAPHYLOCOCCUS_ALIVE_PROB = 0.10;
    private static final double RANDCELL_ALIVE_PROB = 0.30;
    private static final double COLORCELL_ALIVE_PROB = 0.15;
    private static final double DISEASED_PROB = 0.01;
    private List<Cell> cells;
    private Field field;
    private int generation;
    Random random = new Random(); 

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        cells = new ArrayList<>();
        field = new Field(depth, width);
        reset();
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            cell.act();
        }

        for (Cell cell : cells) {
          cell.updateState();
        }
        generation++;
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() { 
      Random rand1 = Randomizer.getRandom();
      Random rand2 = Randomizer.getRandom();
      Random rand3 = Randomizer.getRandom();
      Random rand4 = Randomizer.getRandom();
      Random rand5 = Randomizer.getRandom();
      field.clear();
      for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          int rand = random.nextInt(4);
          if (rand==0){
            Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
            if (rand1.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
                cells.add(myco);
            }
            else {
                myco.setDead();
                cells.add(myco);
            }
          }
          else if (rand==1){
              Staphylococcus staph = new Staphylococcus(field, location, Color.MEDIUMAQUAMARINE);
              if (rand2.nextDouble() <= STAPHYLOCOCCUS_ALIVE_PROB) {
                cells.add(staph);
                if (rand4.nextDouble()<=DISEASED_PROB){
                    staph.infect(true);
                    staph.mutate();
                }
              }
              else {
                staph.setDead();
                cells.add(staph);
              }
          }
          else if (rand==2){
              RandCell rcell = new RandCell(field, location, Color.LIGHTSKYBLUE);
              if (rand3.nextDouble() <= RANDCELL_ALIVE_PROB) {
                cells.add(rcell);
                if (rand4.nextDouble()<=DISEASED_PROB){
                    rcell.infect(true);
                    rcell.mutate();
                }
              }
              else {
                rcell.setDead();
                cells.add(rcell);
              }
          }
          else if (rand==3){
              ColorCell ccell= new ColorCell(field, location, Color.SALMON);
              if (rand5.nextDouble() <= COLORCELL_ALIVE_PROB) {
                cells.add(ccell);
                if (rand4.nextDouble()<=DISEASED_PROB){
                    ccell.infect(true);
                    ccell.mutate();
                }
              }
              else {
                ccell.setDead();
                cells.add(ccell);
              }
          }
        }
      }
    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    public void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
    
    public Field getField() {
        return field;
    }

    public int getGeneration() {
        return generation;
    }
}