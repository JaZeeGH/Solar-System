# Solar-System Project

## Table of Contents

1.  [Overview]
2.  [Features]
3.  [Implementation Highlights]
4.  [Development Process]
5.  [Future Improvements]
6.  [Project Structure]
8.  [License]
9.  [Contact]

## Overview

Solar-System is a planetary simulation application implementing three planet types—GasPlanet, IcePlanet, and AbstractPlanet—using an object-oriented approach. The project features a comprehensive `PlanetSystem` API and an interactive command-line interface (CLI) for managing and exploring planetary data.

## Features

- **Object-Oriented Design**  
  Abstract base class `Planet` defines shared attributes and behaviors. Concrete subclasses (`GasPlanet`, `IcePlanet`) implement specific logic.

- **Unique Identifier Management**  
  Every planet gets a unique, immutable `planetID` via a static counter.

- **Gravity Calculation**  
  Each planet can calculate its surface gravity based on physical properties.

- **Extensible API**  
  The `PlanetSystem` class offers CRUD operations, sorting, and retrieval of the top 5 planets by radiation level.

- **Interactive CLI Driver**  
  A menu-driven interface lets users add, delete (by ID or index), search, and display planet information.

## Implementation Highlights

- **Planet Hierarchy**  
  The abstract `Planet` class provides methods like `displayInfo()` and `classifyBody()`, which are overridden in subclasses and call their parent implementations as appropriate.

- **ID Generation**  
  A static `nextID` counter ensures sequential, immutable planet identifiers.

- **Gravity Calculation**  
  The method `calculateGravity()` computes gravity and is integrated into each subclass’s display logic.

- **Sorting & Top-5 Retrieval**  
  Planets can be sorted by radiation level, and the top 5 highest-radiation planets can be retrieved.

- **Robust CLI UI**  
  The driver features multi-layered menus with input validation and error handling.

## Development Process

1. **Class Construction**  
   Defined the abstract `Planet` class and its concrete subclasses. Refined static ID generation logic after initial issues.

2. **API Development**  
   Built the `PlanetSystem` service layer, enabling addition, removal, lookup, sorting, and top-5 extraction.

3. **Iterative Refinement**  
   Debugged and improved integration between the API and CLI, fixing bugs in deletion logic and sorting.

4. **Testing & Validation**  
   Manual tests via the CLI confirmed correct behavior of CRUD operations and sorting.

## Challenges & Solutions

- **Static ID Management**  
  Implemented a thread-safe static counter for planet IDs.

- **Abstract Method Invocation**  
  Ensured proper use of `@Override` annotations and correct superclass method calls.

- **Sorting Algorithm**  
  Resolved bugs in the radiation-level comparator and added unit tests.

- **Menu Design**  
  Built a hierarchical menu system for usability and future extensibility.

## Future Improvements

- Add automated unit tests (e.g., JUnit) for all public methods.
- Refactor the CLI driver to support graphical or web-based interfaces.
- Implement data persistence (JSON or database) for planets.
- Optimize sorting using priority queues for scalability.

## License

This project is licensed under the MIT License – see the `LICENSE` file for details.

* * *

## Contact

*   Author: JaZeeGH
*   GitHub: [https://github.com/JaZeeGH](https://github.com/JaZeeGH)

---

*Explore, manage, and simulate your own planetary system with Solar-System!*