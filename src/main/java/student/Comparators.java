package student;

import java.util.Comparator;

public class Comparators {
    public Comparators(){}

    public static Comparator<BoardGame> comparator(GameData sortOn, boolean ascending) {

        Comparator<BoardGame> comparator;

        switch (sortOn) {
            case NAME:
                comparator = Comparator.comparing(BoardGame::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case MIN_PLAYERS:
                comparator = Comparator.comparingInt(BoardGame::getMinPlayers);
                break;
            case MAX_PLAYERS:
                comparator = Comparator.comparingInt(BoardGame::getMaxPlayers);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting field: " + sortOn);
        }

        return ascending ? comparator : comparator.reversed();
    }
}
