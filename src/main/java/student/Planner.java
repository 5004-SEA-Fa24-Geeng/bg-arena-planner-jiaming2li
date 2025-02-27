package student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;

public class Planner implements IPlanner {//classPlanner
    //private Set<BoardGame> games;
    private Set<BoardGame> games;
    private Set<BoardGame> copy;

    public Planner(Set<BoardGame> games) {
        // TODO Auto-generated method stub
        this.games = games;
        this.copy = new HashSet<>(games);
        //throw new UnsupportedOperationException("Unimplemented constructor 'Planner'");
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'filter'");
        return filter(filter,GameData.NAME,true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'filter'");
        return filter(filter,sortOn,true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'filter'");
        if (filter.isEmpty()) {
            return games.stream();  // Return all games if filter is empty
        }

        String[] conditions = filter.split(",");

        return games.stream()
                .filter(game-> {
                    boolean matches = true;

                    for (String condition : conditions) { //miniplayers>4, name = quintus
                        condition = condition.trim();
                        Operations op = Operations.getOperatorFromStr(condition);
                        String[] parts = condition.split("[=><!~]+");

                        GameData gd = GameData.fromString(parts[0].trim());

                        String right = parts[1].trim();

                        double numValue = 0;
                        String strValue = null;

                        if(right.matches(("-?\\d+(\\.\\d+)?"))&&!gd.equals(GameData.NAME)) {
                            numValue = Double.parseDouble(right);
                        }else{
                            strValue = right;
                        }

                        switch (gd) {
                            case MIN_PLAYERS:
                                matches &= applyFilter(game.getMinPlayers(), op, numValue);
                                break;
                            case MAX_PLAYERS:
                                matches &= applyFilter(game.getMaxPlayers(), op, numValue);
                                break;
                            case NAME:
                                if (op.equals(Operations.CONTAINS)){
                                    matches &= game.getName().toLowerCase().contains(strValue.toLowerCase());
                                    break;
                                }
                        }
                    }
                    return matches;
                })
                .sorted(getComparator(sortOn, ascending));
    }

    private static boolean applyFilter(double fieldValue, Operations op, double value) {
        switch (op) {
            case GREATER_THAN:
                return fieldValue > value;
            case LESS_THAN:
                return fieldValue < value;
            case GREATER_THAN_EQUALS:
                return fieldValue >= value;
            case LESS_THAN_EQUALS:
                return fieldValue <= value;
            case EQUALS:
                return fieldValue == value;
            case NOT_EQUALS:
                return fieldValue != value;
            default:
                return false;
        }
    }

    public static Comparator<BoardGame> getComparator(GameData sortOn, boolean ascending) {
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

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'reset'");
        this.games = new HashSet<>(copy);
    }

}
