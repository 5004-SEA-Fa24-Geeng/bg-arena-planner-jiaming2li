# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram 

Place your class diagrams below. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

### Provided Code

Provide a class diagram for the provided code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.
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
        - static applyFilter(double, Operations, double)boolean
        - static getComparator(GameData, boolean)Comparator~BoardGame~
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
    Planner --> Operations : uses
    IPlanner <|.. Planner
    IGameList <|.. GameList


    ConsoleApp --> IGameList
    ConsoleApp --> IPlanner
    ConsoleApp --> ConsoleText: uses
    ConsoleApp o-- BoardGame
    ConsoleApp --> GameData: uses
```


### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 
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
```




## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 
1. Test that the `Planner` class properly returns a list of boardgames meeting the filter conditions from `filter(String filter) `.
For example, if the filter is "minPlayers>3", it will return a list contains all board games whose minPlayers>3. The conditions cover name, minPlayers, maxPlayers, minPlayTime, maxPlayTime, difficulty, rank, averageRating,yearPublished.
The operations cover the all operations mentioned in `enum Operation`.
2. Test that the `Planner` class properly returns a list of boardgames meeting the filter conditions from `filter(String filter, GameData sortOn) `.
Just like the last test, the result will sort the results according to the keywords provided in ascending order.
3. Test that the `Planner` class properly returns a list of boardgames meeting the filter conditions from `filter(String filter, GameData sortOn, boolean ascending)`.
Just like the last test, the result will sort the results according to the keywords provided in the order provided(ascending or descending order).
4. Test that the `GameList` class properly returns `gameNames.size()`(how many games in the list) from `count()`.
5. Test that the `GameList` class properly save the game list into the file provided from `saveGame(String filename)`.
6. Test that the `GameList` class properly add game or games provided to game list from `addToList(String str, Stream<BoardGame> filtered)`.
7. Test that the `GameList` class properly remove game or games from the list from `removeFromList(String str)`.
8. Test that the `GameList` class properly returns `List<String>` of game names from `getGameNames()`.

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test 1..
2. Test 2..




## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
