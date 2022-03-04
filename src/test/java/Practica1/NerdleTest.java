package Practica1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static Practica1.Nerdle.SymbolHint.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class NerdleTest {
    static stubNerdle nerdle;

    @BeforeAll
    static void prepara(){
        nerdle = new stubNerdle();
    }

    static Stream<Arguments> datos(){
        return Stream.of(
                Arguments.of("5+5=10", "5+5=10", true, new Nerdle.SymbolHint[]{CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT}, true, true),
                Arguments.of("5+6=11", "5+5=10", true, new Nerdle.SymbolHint[]{CORRECT, CORRECT, USELESS, CORRECT, CORRECT, MISPLACED}, true, true),
                Arguments.of("9+6=11", "5+5=10", true, null, false, true),
                Arguments.of("5+6=11", "5+5=11", true, null, true, false),
                Arguments.of("federico", "5+5=10", true, null, false, true),
                Arguments.of("5+6=11", "federico", true, null, true, false),
                Arguments.of(null, "12+12=34", false, null, true, true),
                Arguments.of("12+12=34", null, false, null, true, true),
                Arguments.of("asdfgh", "asdfgh", false, null, true, true),
                Arguments.of("12+12=34", "12+12=24", false, null, false, true),
                Arguments.of("12+12=24", "12+12=24", false, new Nerdle.SymbolHint[]{CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT}, true, true),
                Arguments.of("12+12=24", "5+5=10", false, null, true, true),
                Arguments.of("5+5=10", "5+5=10", true, new Nerdle.SymbolHint[]{CORRECT, CORRECT, CORRECT, CORRECT, CORRECT, CORRECT}, true, true),
                Arguments.of("5+5=10", "12+12=24", true, null, true, true)

                );
    }

    @ParameterizedTest
    @MethodSource("datos")
    void getHints(String guess, String solution, boolean isMin, Nerdle.SymbolHint[] solucion, boolean vGuess, boolean vSolution) {
        nerdle.sol = vGuess && vSolution;
        assertThat(nerdle.getHints(guess, solution, isMin), is(solucion));
    }


}