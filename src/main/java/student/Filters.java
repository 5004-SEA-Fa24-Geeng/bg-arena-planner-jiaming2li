package student;

public class Filters {
    public Filters(){}//or private?

    public static boolean filter(BoardGame game, GameData column, Operations op, String value) {

        switch (column) {
            case NAME:
                return filterString(game.getName(),op,value);
            case MIN_PLAYERS:
                return filterNum(game.getMinPlayers(),op,value);
            case MAX_PLAYERS:
                return filterNum(game.getMaxPlayers(),op,value);
            case RATING:
                return filterNum(game.getRating(),op,value);
            case RANK:
                return filterNum(game.getRank(),op,value);
            case YEAR:
                return filterNum(game.getYearPublished(),op,value);
            case DIFFICULTY:
                return filterNum(game.getDifficulty(),op,value);
            case MAX_TIME:
                return filterNum(game.getMaxPlayTime(),op,value);
            case MIN_TIME:
                return filterNum(game.getMinPlayTime(),op,value);
            default:
                return false;
        }
    }

    public static boolean filterString(String gameData, Operations op, String value) {

        switch (op) {
            case GREATER_THAN:
                return gameData.compareTo(value)>0;
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


}
