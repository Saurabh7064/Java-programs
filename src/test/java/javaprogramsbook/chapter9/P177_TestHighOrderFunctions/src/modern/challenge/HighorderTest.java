package javaprogramsbook.chapter9.P177_TestHighOrderFunctions.src.modern.challenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static javaprogramsbook.chapter9.P177_TestHighOrderFunctions.src.modern.challenge.Main.reduceStrings;
import static javaprogramsbook.chapter9.P177_TestHighOrderFunctions.src.modern.challenge.Main.replace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HighorderTest {

    @Test
    void testReplacer() {
        List<String> names = Arrays.asList("Ann a 15", "Mir el 28", "D oru 33");
        List<String> resultWs = replace(names, (String s) -> s.replaceAll("\\s", ""));
        List<String> resultNr = replace(names, (String s) -> s.replaceAll("\\d", ""));

        assertEquals(Arrays.asList("Anna15", "Mirel28", "Doru33"), resultWs);
        assertEquals(Arrays.asList("Ann a ", "Mir el ", "D oru "), resultNr);
    }

    @Test
    void testReduceStrings() {
        Function<String, String> f1 = (String s) -> s.toUpperCase();
        Function<String, String> f2 = (String s) -> s.concat(" DONE");

        Function<String, String> f3 = (String s) -> s.concat(" ok");

        Function<String, String> f = reduceStrings(f1, f2, f3);

        assertEquals("TEST DONE ok", f.apply("test"));
    }

    @Test
    void testReplaceNullArgumentsThrow() {
        assertThrows(IllegalArgumentException.class, () -> replace(null, s -> s));
        assertThrows(IllegalArgumentException.class, () -> replace(Arrays.asList("a"), null));
    }
}
