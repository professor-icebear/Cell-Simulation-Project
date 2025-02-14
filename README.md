# Extended Cellular Simulation: A Game of Life Variant

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technical Details](#technical-details)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Simulation](#running-the-simulation)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Overview
This project is an innovative spin on Conway's classic Game of Life. It simulates a dynamic ecosystem where four distinct cellular life forms compete, interact, and evolve on a grid over multiple generations. Each life form possesses unique behaviors, survival strategies, and interactions, making the simulation both unpredictable and engaging.

## Features
- **Diverse Life Forms:**
  - **Mycoplasma:** Starts with a 25% probability of being alive; immune to diseases; survives with exactly 2 or 3 live neighbors; reproduction by having exactly 3 live neighbors.
  - **Color Cell:** Begins with a 15% probability; alternates colors between generations (Salmon Pink on odd, Slate Blue on even) under normal conditions, and shifts to Lime Green when diseased; has specific survival and curing mechanisms based on neighbor configuration.
  - **Staphylococcus:** Initiates at a 10% probability; its survival rules adapt over time, becoming more stringent as generations progress; controls population through overcrowding checks and adapts behavior when diseased.
  - **Random Cell:** Exhibits non-deterministic behavior with various probabilities influencing survival, color change, and disease infection.
- **Advanced Disease Mechanics:** Implements a disease system where cells (except Mycoplasma) can get infected, mutate (changing color to Black), and potentially cure themselves under specific neighbor conditions.
- **Symbiosis:** Mycoplasma and Staphylococcus engage in a mutualistic relationship, where Mycoplasma cures infected Staphylococcus cells while Staphylococcus aids in reviving dead Mycoplasma cells.
- **Graphical User Interface (GUI):** Provides a visual simulation with a grid representing cell states, along with a "Show Chart" feature that displays pre- and post-simulation population statistics via pie charts.

## Technical Details
- **Language & Tools:**
  - Developed in Java and packaged as a runnable JAR (`GameofLifeFinalCode.jar`).
  - Utilizes Java's GUI libraries for simulation display.
- **Core Components:**
  - `Simulator.java`: Drives the simulation cycle by managing generations.
  - `SimulatorView.java`: Renders the simulation grid and handles user interactions.
  - `Field.java` & `FieldCanvas.java`: Define and display the simulation grid.
  - `FieldStats.java` & `StatChart.java`: Collect and present cell population statistics.
  - `Cell.java` along with subclasses (`Mycoplasma.java`, `ColorCell.java`, `Staphylococcus.java`, `RandCell.java`/`Randomizer.java`): Implement the rules and behaviors for each cell type.
  - `Location.java`: Manages cell positioning within the grid.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Ensure your Java environment variables are correctly configured

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/professor-icebear/Cell-Simulation-Project.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Cell-Simulation-Project
   ```
3. Build the project using your preferred IDE or compile via command line. Alternatively, you may use the provided JAR file.

### Running the Simulation
Execute the simulation with the following command:
```sh
java -jar GameofLifeFinalCode.jar
```
- The simulation window will display the evolving grid.
- Click the "Show Chart" button to view a comparative pie chart of cell populations before and after the simulation run.

## Contributing
Contributions are welcome! Follow these steps to contribute:
1. Fork the repository.
2. Create your feature branch:
   ```sh
   git checkout -b feature/YourFeature
   ```
3. Commit your changes:
   ```sh
   git commit -m "Your descriptive commit message"
   ```
4. Push to the branch:
   ```sh
   git push origin feature/YourFeature
   ```
5. Open a pull request describing your changes.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements
- Inspired by Conway's Game of Life and cellular automata research.
- Thanks to all contributors and the open-source community for their support.
