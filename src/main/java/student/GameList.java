package student;

import java.util.*;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameList implements IGameList {
    /** A list to store the names of the board games. */
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
        return gameNames;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        gameNames.clear();
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return gameNames.size();
    }

    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
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
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("List name cannot be null or empty");
        }

        List<BoardGame> gameList = filtered.toList();

        boolean found = false;

        String[] parts = str.split("-");

        for (String part : parts) { //7 wonders

            // 如果包含非数字字符 (排除 `-`)，则识别为字符串
            if (part.matches(".*[^0-9-].*")) {
                found = true;
            }
        }

        if (found) {
            if (!str.trim().toLowerCase().equals("all")) {
                for (int i = 0; i < gameList.size(); i++) {
                    if (gameList.get(i).getName().toLowerCase().trim().equals(str.toLowerCase().trim())) {
                        gameNames.add(gameList.get(i).getName());
                    }
                }
            } else {
                for (BoardGame game: gameList) {
                    gameNames.add(game.getName());
                }
            }

        } else {
            if (parts.length == 1) {
                int index = Integer.parseInt(parts[0]);
                for (int i = 0; i < gameList.size(); i++) {
                    if (i == index - 1) {
                        gameNames.add(gameList.get(i).getName());
                    }
                }

            } else {
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                for (int i = 0; i < gameList.size(); i++) {
                    if (start <= i + 1 && i + 1 <= end) {
                       gameNames.add(gameList.get(i).getName());
                   }
                }
            }

        }
        gameNames = new ArrayList<>(new LinkedHashSet<>(gameNames));

    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub

        //3-7
        boolean found = false;

        String[] parts = str.split("-");

        for (String part : parts) { //7 wonders
            if (part.matches(".*[^0-9-].*")) {
                found = true;
            }
        }

        if (found) {
            if (!str.trim().toLowerCase().equals("all")) {
                for (int i = 0; i < gameNames.size(); i++) {
                    if (gameNames.get(i).toLowerCase().trim().equals(str.toLowerCase().trim())) {
                        gameNames.remove(i);
                    }
                }
            } else {
                for (int i = 0; i < gameNames.size(); i++) {
                    gameNames.clear();

                }
            }
        } else {
            if (parts.length == 1) {
                int index = Integer.parseInt(parts[0]);
                for (int i = 0; i < gameNames.size(); i++) {
                    if (i == index - 1) {
                        gameNames.remove(i);
                    }
                }
            } else {
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                List<String> temp = new ArrayList<>();
                for (int i = 0; i < gameNames.size(); i++) {
                    if (start <= i + 1 && i + 1 <= end) {
                        temp.add(gameNames.get(i));
                    }
                }
                gameNames.removeAll(temp);
            }
        }

    }

}
