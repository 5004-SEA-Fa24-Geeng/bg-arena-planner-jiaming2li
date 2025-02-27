package student;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;



import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {


    BoardGame bg1 = new BoardGame("Cat", 1, 1, 4, 60, 30, 2.5, 1, 4.5, 1995);
    BoardGame bg2 = new BoardGame("Cata", 3, 3, 4, 60, 30, 2.5, 1, 4.5, 1995);
    BoardGame bg3 = new BoardGame("Catan", 2, 5, 4, 60, 30, 2.5, 1, 4.5, 1995);

    Set<BoardGame> games = Set.of(bg1, bg2, bg3);

    Planner p = new Planner(games);


    @Test
    void testFilter() {
    }

    @Test
    void testFilter1() {

        Planner p = new Planner(games);
        List<BoardGame> expected = List.of(bg3);

        List<BoardGame> actual = p.filter("minplayers>4", GameData.NAME, true)
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    void reset() {
    }
}