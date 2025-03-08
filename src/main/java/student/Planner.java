package student;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Planner implements IPlanner {//classPlanner
    private Set<BoardGame> games;
    private Set<BoardGame> copy;

    public Planner(Set<BoardGame> games) {
        // TODO Auto-generated method stub
        this.games = games;
        this.copy = new HashSet<>(games);
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        // TODO Auto-generated method stub
        return filter(filter,GameData.NAME,true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        // TODO Auto-generated method stub
        return filter(filter,sortOn,true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        // TODO Auto-generated method stub

        Stream<BoardGame> stream = games.stream();

        if (!filter.isEmpty()) {
            String[] conditions = filter.split(",");

            for (String condition : conditions) { //miniplayers>4, name = quintus
                condition = condition.trim();


                Operations op = Operations.getOperatorFromStr(condition);
                String[] parts = condition.split("[=><!~]+");
                GameData gd = GameData.fromString(parts[0].trim());

                String right = parts[1].trim().toLowerCase();

                stream = stream.filter(game -> Filters.filter(game, gd, op, right));//ignore?
            }
        }


        return stream.sorted(Comparators.comparator(sortOn, ascending));
    }


    @Override
    public void reset() {
        // TODO Auto-generated method stub
        this.games = new HashSet<>(copy);
    }

}
