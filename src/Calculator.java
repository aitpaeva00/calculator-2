import java.util.*;

public class Calculator {
    private static final List<String> history = new ArrayList<>();

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double sqrt(double number) {
        if (number < 0) {
            throw new ArithmeticException("Error: Square root of negative number");
        }
        return Math.sqrt(number);
    }

    public static double abs(double number) {
        return Math.abs(number);
    }

    public static long round(double number) {
        return Math.round(number);
    }

    public static void addToHistory(String operation) {
        history.add(operation);
    }

    public static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            System.out.println("Calculation History:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }

    public static double evaluate(String expression) {
        try {
            expression = expression.replace("**", "^"); // Заменяем ** на ^
            return new ExpressionParser().parse(expression);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Error: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: Invalid input format");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Calculator!");

        while (true) {
            System.out.print("Enter operation (or type 'exit' to quit): ");
            String input = scanner.nextLine().replaceAll("\\s", "");

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                double result = evaluate(input);
                System.out.println("Result: " + result);

                while (true) {
                    System.out.print("Do you want to continue? (y/n): ");
                    String continueInput = scanner.nextLine().toLowerCase();

                    if (continueInput.equals("y")) {
                        break;
                    } else if (continueInput.equals("n")) {
                        System.out.println("Thank you for using the Calculator!");
                        scanner.close();
                        return;
                    } else {
                        System.out.println("Invalid input. Try again!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}

class ExpressionParser {
    public double parse(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) {
                        double divisor = parseFactor();
                        if (divisor == 0) {
                            throw new ArithmeticException("Error: Division by zero");
                        }
                        x /= divisor;
                    } else if (eat('%')) {
                        double divisor = parseFactor();
                        if (divisor == 0) {
                            throw new ArithmeticException("Error: Modulo by zero");
                        }
                        x %= divisor;
                    } else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (Character.isLetter(ch)) {
                    while (Character.isLetter(ch)) nextChar();
                    String func = expression.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("abs")) x = Math.abs(x);
                    else if (func.equals("round")) x = Math.round(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected character: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());
                return x;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected character: " + (char) ch);
                return x;
            }
        }.parse();
    }
}
