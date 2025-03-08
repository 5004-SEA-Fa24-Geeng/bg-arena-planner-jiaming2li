package student;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

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
        Filters f = new Filters();
        Comparators c = new Comparators();

        Stream<BoardGame> stream = games.stream();

        if (!filter.isEmpty()) {
            String[] conditions = filter.split(",");

            for (String condition : conditions) { //miniplayers>4, name = quintus
                condition = condition.trim();


                Operations op = Operations.getOperatorFromStr(condition);
                String[] parts = condition.split("[=><!~]+");
                GameData gd = GameData.fromString(parts[0].trim());

                String right = parts[1].trim();

                stream = stream.filter(game -> f.filter(game, gd, op, right));//ignore?
            }
              // Return all games if filter is empty
        }

        //Stream<BoardGame> stream = games.stream();

        return stream.sorted(c.comparator(sortOn, ascending));
    }


    @Override
    public void reset() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'reset'");
        this.games = new HashSet<>(copy);
    }

}
