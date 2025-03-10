package student;

import java.util.Comparator;

/**
 * Utility class for providing comparators to sort BoardGame objects based on different game data fields.
 */
 public final class Comparators {

    private Comparators() {

    }

    /**
     * Returns a comparator for sorting objects based on the specified field.
     * This method generates a comparator that sorts objects based on the given field.
     *
     * @param sortOn the field of {@link BoardGame} to sort by.
     * @param ascending ascending {@code true} for ascending order, {@code false} for descending order.
     * @return a {@link Comparator} for sorting {@code BoardGame} objects based on the given field.
     */
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
            case RATING:
                comparator = Comparator.comparingDouble(BoardGame::getRating);
                break;
            case RANK:
                comparator = Comparator.comparingInt(BoardGame::getRank);
                break;
            case YEAR:
                comparator = Comparator.comparingInt(BoardGame::getYearPublished);
                break;
            case DIFFICULTY:
                comparator = Comparator.comparingDouble(BoardGame::getDifficulty);
                break;
            case MAX_TIME:
                comparator = Comparator.comparingInt(BoardGame::getMaxPlayTime);
                break;
            case MIN_TIME:
                comparator = Comparator.comparingInt(BoardGame::getMinPlayTime);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting field: " + sortOn);
        }

        return ascending ? comparator : comparator.reversed();
    }
}
