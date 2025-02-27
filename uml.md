```mermaid
classDiagram

class BGArenaPlanner {
- static final DEFAULT_COLLECTION: String 
- BGArenaPlanner()
+ static main(args: String[] ) void 
}
       
class BoardGame {
- name: String
- id: int
- minPlayers: int
- maxPlayers: int
- minPlayTime: int
- maxPlayTime: int
- difficulty: double
- rank: int
- averageRating: double
- yearPublished: int
+ BoardGame(String, int, int, int, int, int, double, int, double, int)
+ getName()String
+ getId()int
+ getMinPlayers()int
+ getMaxPlayers()int
+ getMinPlayTime()int
+ getMaxPlayTime()int
+ getDifficulty()double
+ getRank()int
+ getRating()double
+ getYearPublished()int
+ toStringWithInfo(col: GameData)String
+ toString()String
+ equals(Object)boolean
+ hashCode()int
+ static main(args: String[] ) void 
}

class ConsoleApp {
  - static IN: Scanner 
  - static DEFAULT_FILENAME: String 
  - static RND: Random 
  - current: Scanner 
  - gameList: IGameList 
  - planner: IPlanner 
  + ConsoleApp(IGameList, IPlanner)
  + start()void 
  - randomNumber()void 
  - processHelp()void 
  - processFilter()void 
  - printFilterStream(Stream<BoardGame>, GameData)void 
  - processListCommands()void 
  - printCurrentList()void 
  - nextCommand()ConsoleText 
  - remainder()String 
  - static getInput(String, Object)String 
  - static printOutput(String, Object)void 
}

class ConsoleText {
        <<enumeration>>
        WELCOME
        HELP
        INVALID
        GOODBYE
        PROMPT
        NO_FILTER
        NO_GAMES_LIST
        FILTERED_CLEAR
        LIST_HELP
        FILTER_HELP
        INVALID_LIST
        EASTER_EGG
        CMD_EASTER_EGG
        CMD_EXIT
        CMD_HELP
        CMD_QUESTION
        CMD_FILTER
        CMD_LIST
        CMD_SHOW
        CMD_ADD
        CMD_REMOVE
        CMD_CLEAR
        CMD_SAVE
        CMD_OPTION_ALL
        CMD_SORT_OPTION
        CMD_SORT_OPTION_DIRECTION_ASC
        CMD_SORT_OPTION_DIRECTION_DESC
        + toString()String 
        + static fromString(String)ConsoleText 
    }

class GameData {
    - columnName: String 
    + GameData(String)
    + getColumnName() String
    + static fromColumnName(String) GameData
    + static fromString(String) GameData

    <<enumeration>>
    GameData : NAME("objectname")
    GameData : ID("objectid")
    GameData : RATING("average")
    GameData : DIFFICULTY("avgweight")
    GameData : RANK("rank")
    GameData : MIN_PLAYERS("minplayers")
    GameData : MAX_PLAYERS("maxplayers")
    GameData : MIN_TIME("minplaytime")
    GameData : MAX_TIME("maxplaytime")
    GameData : YEAR("yearpublished")
}

class GameList {
        + GameList()
        + getGameNames()List<String> 
        + clear()void 
        + count()int 
        + saveGame(String)void 
        + addToList(String, Stream<BoardGame>)void 
        + removeFromList(String)void 
    }
    
class GamesLoader {
    - static final DELIMITER: String 
    - private GamesLoader()
    + static loadGamesFile(String)Set<BoardGame> 
    - static toBoardGame(String, Map<GameData, Integer>)BoardGame 
    - static processHeader(String)Map<GameData, Integer> 
}

class IGameList {
    <<interface>>
    + getGameNames()List ~String~
    + clear()void 
    + count()int 
    + saveGame(String)void 
    + addToList(String, Stream~BoardGame~)void 
    + removeFromList(String)void 
}


class IPlanner {
    <<interface>>
    + filter(String)Stream~BoardGame~ 
    + filter(String, GameData)Stream~BoardGame~ 
    + filter(String, GameData, boolean)Stream~BoardGame~ 
    + reset()void 
}

class Operations {
    <<enumeration>>
    + operator:String 
    + Operations(String)
    + getOperator()String 
    + static fromOperator(String)Operations 
    + static getOperatorFromStr(String)Operations 
    Operations : == 
    Operations : != 
    Operations : > 
    Operations : <
    Operations : >= 
    Operations : <= 
    Operations : ~= 
}
       
class Planner {
    - games Set~BoardGame~ 
    + Planner(Set~BoardGame~)
    + filter(String)Stream<BoardGame> 
    + filter(String, GameData)Stream<BoardGame> 
    + filter(String, GameData, boolean)Stream~BoardGame~
    + void reset()
}

    BGArenaPlanner .. IPlanner
    BGArenaPlanner .. IGameList
    BGArenaPlanner o-- ConsoleApp
    GameList o-- BoardGame
    IGameList o-- BoardGame
    IPlanner o-- BoardGame
    IPlanner --> GameData: uses
    GamesLoader --> GameData: uses
    GamesLoader o-- BoardGame
    Planner o-- BoardGame
    Planner --> GameData: uses
    IPlanner <|.. Planner
    IGameList <|.. GameList


    ConsoleApp --> IGameList
    ConsoleApp --> IPlanner
    ConsoleApp --> ConsoleText: uses
    ConsoleApp o-- BoardGame
    ConsoleApp --> GameData: uses