Project Overview
================

This project implements a planetary simulation consisting of three concrete planet types—GasPlanet, IcePlanet, and AbstractPlanet—and a supporting PlanetSystem API. The application includes a command‑line driver that lets users interact with the system via a menu‑driven interface.

Key Features
------------

*   Object‑Oriented Design – Abstract base class `Planet` defines common attributes and behavior; concrete subclasses implement specific logic for gas and ice planets.
*   Unique Identifier Management – Static `nextID` field guarantees a unique `planetID` for each instantiated planet.
*   Gravity Calculation – Each planet computes its surface gravity based on mass and radius.
*   Extensible API – `PlanetSystem` provides CRUD operations, sorting, and retrieval of the top‑5 planets by radiation level.
*   Interactive CLI Driver – Menu‑driven main program supports adding, deleting (by ID or index), searching, and displaying planets.

Implementation Highlights
-------------------------

*   Planet Hierarchy – `Planet` is abstract and defines `displayInfo()` and `classifyBody()`. Subclasses override these methods and invoke `super.displayInfo()` / `super.classifyBody()` where appropriate.
*   ID Generation – A static counter (`nextID`) ensures each planet receives a sequential, immutable identifier. The logic was refined after initial debugging.
*   Gravity Computation – Implemented in `calculateGravity()` and integrated into each subclass’s display routine.
*   Sorting & Top‑5 Retrieval – Custom comparator sorts planets by radiation level; the top‑5 method returns the highest‑radiation entries (bug fixed after discovery).
*   Driver UI – Multi‑layer menu system guides users through creation, deletion, search, and reporting functions. Input validation and error handling were added iteratively.

Development Process
-------------------

1.  Class Construction – Started by defining the abstract `Planet` class and its concrete subclasses. Initial difficulties with static ID generation were resolved by refactoring the `nextID` logic.
2.  API Development – Built the `PlanetSystem` service layer, exposing methods for addition, removal, lookup, sorting, and top‑5 extraction.
3.  Iterative Refinement – While integrating the API with the driver, discovered and corrected several typographical bugs and logical errors (e.g., improper use of `deleteByPlanetID`).
4.  Testing & Validation – Conducted manual tests through the CLI driver, confirming that all CRUD operations and sorting mechanisms behaved as expected.

Challenges & Resolutions
------------------------

*   Static ID Management – Initially ignored the requirement; later implemented a thread‑safe static counter to guarantee uniqueness.
*   Abstract Method Invocation – Needed to correctly call `super.displayInfo()` and `super.classifyBody()` from subclasses; resolved by ensuring proper `@Override` annotations and method signatures.
*   Sorting Logic – The highest‑radiation sorting algorithm contained a subtle bug; fixed by revisiting the comparator implementation and adding unit tests.
*   Menu Complexity – Designed a clear, hierarchical menu structure to improve usability while preserving flexibility for future extensions.

Possible Future Improvements
-------------------

*   Add automated unit tests (JUnit) for all public methods.
*   Refactor the driver to support a graphical UI or web front‑end.
*   Introduce persistence (e.g., JSON or database storage) for planet data across sessions.
*   Optimize sorting with a priority queue for large datasets.