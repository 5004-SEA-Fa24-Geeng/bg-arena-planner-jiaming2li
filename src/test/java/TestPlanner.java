import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import student.Planner;
import student.IPlanner;
import student.GameData;


/**
 * JUnit test for the Planner class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {
    static Set<BoardGame> games;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }

     @Test
    public void testFilterByNameEquals() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    @Test
    public void testFilterByNameContains() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name ~= Go").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("Go", "Go Fish", "golang", "GoRami");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testNameGreaterThan() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name > golang").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("GoRami", "Monopoly", "Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testNameLessThan() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name < Go").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("17 days", "Chess");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testNameGreaterThanOrEqual() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name >= GoRami").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("GoRami", "Monopoly", "Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testNameLessThanOrEqual() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name <= Chess").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("17 days", "Chess");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testNameNotEquals() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("name!=go").map(BoardGame::getName).toList();
        List<String> expectedList = List.of(
                "17 days", "Chess", "Go Fish", "golang", "GoRami", "Monopoly", "Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testMinPlayersGreaterThan() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplayers > 8").toList();
        assertEquals(1, filtered.size());
        assertEquals("Tucano", filtered.get(0).getName());
    }

    @Test
    public void testMinPlayersLessThan() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplayers < 2").toList();
        assertEquals(1, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
    }

    @Test
    public void testMinPlayersGreaterThanOrEqual() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minplayers >= 6").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("GoRami", "Monopoly","Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testMinPlayersLessThanOrEqual() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplayers <= 1").toList();
        assertEquals(1, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
    }

    @Test
    public void testMinPlayersNotEqual() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minplayers != 2").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("17 days", "GoRami", "Monopoly", "Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testMinPlayersEqual() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplayers == 1").toList();
        assertEquals(1, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
    }

    @Test
    public void testMaxPlayersGreaterThan() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("maxplayers > 10").toList();
        assertEquals(1, filtered.size());
        assertEquals("Tucano", filtered.get(0).getName());
        IPlanner planner1 = new Planner(games);
        List<BoardGame> filtered1 = planner1.filter("maxplayers < 3").toList();
        assertEquals(1, filtered1.size());
        assertEquals("Chess", filtered1.get(0).getName());
    }


    @Test
    public void testMinPlayTimeGreaterThanOrEqual() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("minplaytime >= 50").map(BoardGame::getName).toList();
        List<String> expectedList = List.of("17 days", "golang", "Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testMinPlayTimeLessThanOrEqual() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplaytime <= 10").toList();
        assertEquals(1, filtered.size());
        assertEquals("Chess", filtered.get(0).getName());
    }

    @Test
    public void testMaxPlayTime() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("maxplaytime >= 1000").toList();
        assertEquals(1, filtered.size());
        assertEquals("Monopoly", filtered.get(0).getName());
    }

    @Test
    public void testDifficulty() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("difficulty == 1").toList();
        assertEquals(1, filtered.size());
        assertEquals("Monopoly", filtered.get(0).getName());
    }

    @Test
    public void testRank() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("rank >= 700").toList();
        assertEquals(2, filtered.size());
        assertEquals("Chess", filtered.get(0).getName());
        assertEquals("Monopoly", filtered.get(1).getName());
    }

    @Test
    public void testRating() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("rating > 9").toList();
        assertEquals(2, filtered.size());
        assertEquals("Chess", filtered.get(0).getName());
        assertEquals("golang", filtered.get(1).getName());
    }

    @Test
    public void testYearPublished() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("yearpublished == 2000").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    @Test
    public void testEmptyFilter() {
        IPlanner planner = new Planner(games);
        List<String> filtered = planner.filter("").map(BoardGame::getName).toList();
        List<String> expectedList = List.of(
                "17 days", "Chess", "Go", "Go Fish", "golang", "GoRami", "Monopoly", "Tucano");
        assertEquals(expectedList, filtered);
    }

    @Test
    public void testChainedFilters() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("yearpublished > 2004, rank>=700, difficulty<2").toList();
        assertEquals(1, filtered.size());
        assertEquals("Monopoly", filtered.get(0).getName());
    }

    @Test
    public void testFiltersWithSpaces() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("difficulty ==    1").toList();
        assertEquals(1, filtered.size());
        assertEquals("Monopoly", filtered.get(0).getName());
    }

    @Test
    public void testFilterSortNameDesc() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplayers >= 6",GameData.NAME,false).toList();
        assertEquals(3, filtered.size());
        assertEquals("Tucano", filtered.get(0).getName());
        assertEquals("Monopoly", filtered.get(1).getName());
        assertEquals("GoRami", filtered.get(2).getName());
    }

    @Test
    public void testFilterByMinPlayersAsc() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minplayers != 2",GameData.MIN_PLAYERS).toList();
        assertEquals(4, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
        assertEquals("GoRami", filtered.get(1).getName());
        assertEquals("Monopoly", filtered.get(2).getName());
        assertEquals("Tucano", filtered.get(3).getName());
    }

    @Test
    public void testFilterByRankDesc() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("rank >500",GameData.MIN_PLAYERS,false).toList();
        assertEquals(3, filtered.size());
        assertEquals("Monopoly", filtered.get(0).getName());
        assertEquals("Chess", filtered.get(1).getName());
        assertEquals("17 days", filtered.get(2).getName());
    }

    @Test
    public void testFilterReset() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("rank >500",GameData.MIN_PLAYERS,false).toList();
        assertEquals(3, filtered.size());
        planner.reset();
        assertEquals(8, games.size());
    }

}