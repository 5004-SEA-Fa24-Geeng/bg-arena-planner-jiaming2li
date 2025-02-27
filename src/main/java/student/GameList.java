package student;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameList implements IGameList {
    private List<String> gameNames = new ArrayList<>();

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        //throw new UnsupportedOperationException("Unimplemented constructor 'GameList'");
    }

    @Override
    public List<String> getGameNames() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getGameNames'");
        return gameNames;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'clear'");
        gameNames.clear();
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'count'");
        return gameNames.size();
    }

    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'saveGame'");
        try {
            Files.write(Paths.get(filename), gameNames);
            System.out.println("Games saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving games to file: " + e.getMessage());
        }

    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'addToList'");
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("List name cannot be null or empty");
        }

        List<BoardGame> gameList = filtered.toList();
        String[] index = str.split("-");

        for (int i = 0; i<gameList.size(); i++) {
            if (index.length == 1) {
                int j = Integer.parseInt(index[0]);
                if (j == i+1) {
                    gameNames.add(gameList.get(i).getName());
                }
            } else{
                int j = Integer.parseInt(index[0]);
                int k = Integer.parseInt(index[1]);
                if (j <= i + 1 && i + 1 <= k) {
                    gameNames.add(gameList.get(i).getName());
                }
            }

        }

    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'removeFromList'");

        //3-7
        String[] index = str.split("-");
        List<String> temp = new ArrayList<>();

        if (index.length == 1){
            int j = Integer.parseInt(index[0])-1;
            gameNames.remove(j);
        } else {
            int j = Integer.parseInt(index[0])-1;
            int k = Integer.parseInt(index[1])-1;
            for(int i = 0; i <gameNames.size(); i++){
                if(i<=k && i>=j){ //1-3 0-2
                    temp.add(gameNames.get(i));
                }
            }
            gameNames.removeAll(temp);
        }
    }

}
