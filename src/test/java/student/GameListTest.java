package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest {

    private List<BoardGame> games;

    @BeforeEach
    void setUp() {
        games = new ArrayList<>();
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));

    }

    @Test
    void getGameNames() {
        GameList gameList = new GameList();
        gameList.addToList("1", games.stream());
        List<String> expected = List.of("Go");
        assertEquals(expected, gameList.getGameNames());

    }

    @Test
    void clear() {
        GameList gameList = new GameList();
        gameList.addToList("1", games.stream());
        gameList.clear();
        assertTrue(gameList.getGameNames().isEmpty());
    }

    @Test
    void count() {
        GameList gameList = new GameList();
        gameList.addToList("1", games.stream());

        assertTrue(gameList.getGameNames().size() == 1);
    }

    @Test
    void saveGame() {
    }

    @Test
    void addToList() {
        GameList gameList = new GameList();
        gameList.addToList("1-3", games.stream());
        List<String> expected = List.of("Go", "Go Fish", "golang");
        assertEquals(expected, gameList.getGameNames());
    }

    @Test
    void removeFromList() {
        GameList gameList = new GameList();
        gameList.addToList("1-3", games.stream());
        gameList.removeFromList("1-2");
        List<String> expected = List.of("golang");
        assertEquals(expected, gameList.getGameNames());
    }
}