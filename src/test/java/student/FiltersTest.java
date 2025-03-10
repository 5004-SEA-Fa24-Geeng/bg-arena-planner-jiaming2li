package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void filter() {
        BoardGame game1 = new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000);
        BoardGame game2 = new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001);
        assertTrue(Filters.filter(game1,GameData.NAME,Operations.EQUALS,"go"));
        assertTrue(Filters.filter(game2,GameData.MIN_PLAYERS,Operations.GREATER_THAN,"1"));
        assertTrue(Filters.filter(game1,GameData.MAX_PLAYERS,Operations.LESS_THAN,"20"));
        assertTrue(Filters.filter(game1,GameData.RATING,Operations.GREATER_THAN_EQUALS,"6.5"));
        assertTrue(Filters.filter(game2,GameData.RANK,Operations.NOT_EQUALS,"150"));
        assertTrue(Filters.filter(game1,GameData.YEAR,Operations.GREATER_THAN_EQUALS,"2000"));
        assertTrue(Filters.filter(game1,GameData.DIFFICULTY,Operations.EQUALS,"8"));
        assertTrue(Filters.filter(game2,GameData.MAX_TIME,Operations.GREATER_THAN_EQUALS,"100"));
        assertTrue(Filters.filter(game2,GameData.MIN_TIME,Operations.LESS_THAN,"30"));
    }

    @Test
    void filterString() {
        assertTrue(Filters.filterString("Go Fish",Operations.EQUALS,"Go Fish"));
        assertTrue(Filters.filterString("golang",Operations.GREATER_THAN,"Go Fish"));
        assertTrue(Filters.filterString("Go",Operations.LESS_THAN,"Go Fish"));
        assertTrue(Filters.filterString("Go",Operations.GREATER_THAN_EQUALS,"Go"));
        assertTrue(Filters.filterString("Go",Operations.LESS_THAN_EQUALS,"Go Fish"));
        assertTrue(Filters.filterString("Go",Operations.NOT_EQUALS,"Chess"));
        assertFalse(Filters.filterString("Go Fish",Operations.CONTAINS,"go"));
    }

    @Test
    void filterNum() {
        assertTrue(Filters.filterNum(19,Operations.EQUALS,"19"));
        assertTrue(Filters.filterNum(19,Operations.GREATER_THAN,"18"));
        assertTrue(Filters.filterNum(19,Operations.LESS_THAN,"20"));
        assertTrue(Filters.filterNum(20,Operations.GREATER_THAN_EQUALS,"20"));
        assertTrue(Filters.filterNum(20,Operations.LESS_THAN_EQUALS,"20"));
        assertTrue(Filters.filterNum(20,Operations.NOT_EQUALS,"2"));
        assertFalse(Filters.filterNum(20,Operations.CONTAINS,"20"));
    }
}