package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;


class ComparatorsTest {
    private List<BoardGame> games;

    @BeforeEach
    void setUp() {
        games = new ArrayList<>(List.of(
                new BoardGame("Chess", 2, 2, 7, 30, 60, 8.5, 200, 6.0, 1475),
                new BoardGame("Monopoly", 2, 8, 6, 60, 180, 4.5, 500, 4.0, 1935),
                new BoardGame("Tucano", 5, 4, 10, 60, 120, 8.0, 100, 6.5, 1995)
        ));
    }

    @Test
    void testName() {
        games.sort(Comparators.comparator(GameData.NAME, false));
        List<String> expected = List.of("Tucano", "Monopoly", "Chess");
        assertEquals(expected, games.stream().map(BoardGame::getName).toList());
    }

    @Test
    void testMaxPlayers() {
        games.sort(Comparators.comparator(GameData.MAX_PLAYERS, false));
        List<String> expected = List.of("Tucano", "Chess", "Monopoly");
        assertEquals(expected, games.stream().map(BoardGame::getName).toList());
    }

    @Test
    void testPublishedYears() {
        games.sort(Comparators.comparator(GameData.YEAR, true));
        List<String> expected = List.of("Chess","Monopoly","Tucano");
        assertEquals(expected, games.stream().map(BoardGame::getName).toList());
    }
}