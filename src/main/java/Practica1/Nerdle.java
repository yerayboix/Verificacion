package Practica1;

import java.util.HashSet;
import java.util.Set;

public class Nerdle {
    public static final int NORMAL_LENGTH = 8;
    public static final int MINI_LENGTH = 6;

    public enum SymbolHint {
        USELESS,
        MISPLACED,
        CORRECT
    }

    public boolean validateExpression(String expression) {
        // Given a string, it returns whether or not it follows the syntax
        // ArithmeticExpression "=" Result
        // with ints, "+", "-", "*" and "/" as operators and
        // without parentheses or spaces AND
        // the value of ArithmeticExpression equals Result
        // Not yet implemented...
        return false;
    }

    public SymbolHint[] getHints(String guess, String solution, boolean isMini) {
        if (guess != null && solution != null && validateExpression(guess) && validateExpression(solution)) {
            if(guess.length() != solution.length()) return null;

            int tam_sol = 0;
            boolean correcto = false;

            if (isMini && guess.length() == 6) {
                tam_sol = 6;
                correcto = true;
            } else if (!isMini && guess.length() == 8) {
                tam_sol = 8;
                correcto = true;
            }

            if (correcto) {
                HashSet<Character> s = new HashSet<Character>();
                for (int i = 0; i < solution.length(); i++) {
                    s.add(solution.charAt(i));
                }

                SymbolHint[] sol = new SymbolHint[tam_sol];
                for (int i = 0; i < tam_sol; i++) {
                    if (guess.charAt(i) == solution.charAt(i)) {
                        sol[i] = SymbolHint.CORRECT;
                    } else if (s.contains(guess.charAt(i))) {
                        sol[i] = SymbolHint.MISPLACED;
                    } else {
                        sol[i] = SymbolHint.USELESS;
                    }
                }
                return sol;
            }

        }
        return null;
    }
}
