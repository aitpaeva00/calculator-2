 **Calculator Project Documentation**

 **Project Overview**
This project is a command-line calculator in Java that supports basic arithmetic operations (addition, subtraction, multiplication, division) and advanced functions like square root (`sqrt`), absolute value (`abs`), and rounding (`round`). The calculator also handles parentheses and maintains a history of calculations. 

It allows input through the command line and supports reading mathematical expressions from text files for batch testing.

**Key Features**
- **Basic Operations:** Addition, subtraction, multiplication, division.
- **Advanced Functions:** `sqrt`, `abs`, and `round`.
- **Expression Parsing:** Handles parentheses for operator precedence.
- **History:** Tracks and displays the history of calculations.
- **File Input:** Read expressions from `.txt` files for automated testing.

 **Design and Challenges**
- **Recursive Parsing:** Used a recursive descent parser to evaluate mathematical expressions. This approach handles operator precedence and functions like `sqrt`, `abs`, and `round`.
- **Error Handling:** Includes checks for division by zero, invalid functions, and malformed expressions.
- **User Interaction:** Prompts the user to continue or exit after each calculation and handles invalid inputs.

 **Data Structures and Algorithms**
- **Expression Parsing:** Recursive descent parsing splits expressions into terms and factors.
- **History Storage:** An `ArrayList<String>` stores the history of operations.
- **File Input:** Expressions are read from `.txt` files placed in the `testFiles` directory, used for batch testing.

**Improvements**
- **File Input:** The program now reads expressions from text files, simplifying batch testing.
- **Error Handling:** Improved handling for division by zero and invalid input formats.
- **User Flow:** Added prompts to ask users if they want to continue after each calculation.

 **File Usage**
- **Input:** `.txt` files in the `testFiles` folder contain expressions for automated testing.
- **Output:** Results of expressions are printed to the console, along with error messages when needed.

 **Future Enhancements**
- Add more complex functions (e.g., trigonometric functions).
- Extend the program to support variables for reuse in expressions.
- Develop a graphical user interface (GUI).
