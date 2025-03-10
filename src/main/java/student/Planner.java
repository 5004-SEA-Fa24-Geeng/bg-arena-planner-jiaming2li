package student;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Planner implements IPlanner { //classPlanner
    /** The original set of board games. */
    private Set<BoardGame> games;
    /** A copy of the games set for internal operations. */
    private Set<BoardGame> copy;

    /**
     * Constructs a new {@code Planner} with the specified set of board games.
     * This constructor initializes a planner instance with a given set of {@link BoardGame} objects.
     * It also creates a copy of the original set to allow filtering and modifications without
     * affecting the original data.
     *
     * @param games games The set of {@link BoardGame} objects that this planner will manage.
     *              A copy of this set is also created to support filtering operations.
     */
    public Planner(Set<BoardGame> games) {
        // TODO Auto-generated method stub
        this.games = games;
        this.copy = new HashSet<>(games);
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        // TODO Auto-generated method stub
        return filter(filter, GameData.NAME, true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        // TODO Auto-generated method stub
        return filter(filter, sortOn, true);
    }


    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        // TODO Auto-generated method stub

        Stream<BoardGame> stream = games.stream();

        if (!filter.isEmpty()) {
            String[] conditions = filter.split(",");

            for (String condition : conditions) {
                        condition = condition.trim();
                        Operations op = Operations.getOperatorFromStr(condition);
                        String[] parts = condition.split("[=><!~]+");
                        if (parts.length != 2 || op == null) {
                            throw new IllegalArgumentException("Invalid condition");
                        }
                        try {
                            GameData gd = GameData.fromString(parts[0].trim());
                            String right = parts[1].trim().toLowerCase();
                            stream = stream.filter(game -> Filters.filter(game, gd, op, right));
                        }
                        catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Invalid game data");
                        }

            }

            //for (String condition : conditions) { //miniplayers>4, name = quintus
            //    condition = condition.trim();
            //    Operations op = Operations.getOperatorFromStr(condition);
            //    String[] parts = condition.split("[=><!~]+");
            //    GameData gd = GameData.fromString(parts[0].trim());

            //    String right = parts[1].trim().toLowerCase();

            //    stream = stream.filter(game -> Filters.filter(game, gd, op, right));
            //}
        }

        return stream.sorted(Comparators.comparator(sortOn, ascending));
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        this.games = new HashSet<>(copy);
    }

}
