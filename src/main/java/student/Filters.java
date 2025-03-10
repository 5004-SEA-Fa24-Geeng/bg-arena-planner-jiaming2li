package student;

public final class Filters {

    /**
     * Utility class for filtering BoardGame objects based on various game attributes.
     */
    private Filters() {

    }

    /**
     * Filters a {@link BoardGame} based on the specified column, operation, and value.
     *
     *  This method applies a filtering condition to a given {@code BoardGame} object by
     *  comparing the specified {@link GameData} field using the given {@link Operations}
     *  and a provided value.
     *
     * @param game The {@link BoardGame} object to be filtered.
     * @param column The {@link GameData} field to filter by.
     * @param op The {@link Operations} defining the comparison operation.
     * @param value The string representation of the value to compare against.
     *              If the column is numerical, the value will be parsed as a number.
     * @return {@code true} if the game matches the filtering condition, {@code false} otherwise.
     */
    public static boolean filter(BoardGame game, GameData column, Operations op, String value) {

        switch (column) {
            case NAME:
                return filterString(game.getName().toLowerCase(), op, value);
            case MIN_PLAYERS:
                return filterNum(game.getMinPlayers(), op, value);
            case MAX_PLAYERS:
                return filterNum(game.getMaxPlayers(), op, value);
            case RATING:
                return filterNum(game.getRating(), op, value);
            case RANK:
                return filterNum(game.getRank(), op, value);
            case YEAR:
                return filterNum(game.getYearPublished(), op, value);
            case DIFFICULTY:
                return filterNum(game.getDifficulty(), op, value);
            case MAX_TIME:
                return filterNum(game.getMaxPlayTime(), op, value);
            case MIN_TIME:
                return filterNum(game.getMinPlayTime(), op, value);
            default:
                return false;
        }
    }

    /**
     * Filters a string value based on the specified operation.
     * This method compares the given game data string with the provided value
     * using the specified operation.
     *
     * @param gameData The string value of the game data to be compared.
     * @param op The operation used for comparison.
     * @param value The string value to be compared against.
     * @return ture if the comparison evaluates to true based on the operation, otherwise false.
     */
    public static boolean filterString(String gameData, Operations op, String value) {

        switch (op) {
            case GREATER_THAN:
                return gameData.compareTo(value) > 0;
            case LESS_THAN:
                return gameData.compareTo(value) < 0;
            case GREATER_THAN_EQUALS:
                return gameData.compareTo(value) >= 0;
            case LESS_THAN_EQUALS:
                return gameData.compareTo(value) <= 0;
            case CONTAINS:
                return gameData.contains(value);
            case NOT_EQUALS:
                return !gameData.equals(value);
            case EQUALS:
                return gameData.equals(value);
            default:
                return false;
        }
    }

    /**
     * Filters a numeric value based on the specified operation.
     * This method compares the given numeric game data with the provided value.
     *
     * @param gameData The numeric value of the game data to be compared.
     * @param op The operation used for comparison.
     * @param value The numeric value in string format to be compared against.
     * @return true if the comparison evaluates to true based on the operation, otherwise false.
     */
    public static boolean filterNum(double gameData, Operations op, String value) {
        double doubleValue = Double.parseDouble(value);

        switch (op) {
            case GREATER_THAN:
                return gameData > doubleValue;
            case LESS_THAN:
                return gameData < doubleValue;
            case GREATER_THAN_EQUALS:
                return gameData >= doubleValue;
            case LESS_THAN_EQUALS:
                return gameData <= doubleValue;
            case NOT_EQUALS:
                return gameData != doubleValue;
            case EQUALS:
                return gameData == doubleValue;
            default:
                return false;
        }
    }

    /**
     * Validates the given value based on the specified GameData column.
     *
     * @param column column The {@code GameData} column to check
     * @param value value  The string value to validate
     */
    public static void filterExam(GameData column, String value) {
        if (!column.equals(GameData.NAME)) {
            try {
                double doubleValue = Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid value");
            }
        }

    }


}
