# TicTacToe 2.0 with Software Engineering

## 1. Overview

TicTacToe is a famous game, and it's also first time pen and paper gaming for many kids. 
The structure of the game is the great opportunity to show the art of OOP design.
Growing from OOP to Design Patterns was the way to bring the game to life.
From Design Patterns to Game library to become as engine to be used to various interfaces - from Console to an Online API.

For this project, Console was used as interface. Java 8 was used to be reference language. Spring Framework was used to
show Software Engineering in action. Lombok library to show code cleaner for presentation here, avoiding boilerplate. 
Jackson Library was used to show the example of json config file content converting to POJO. And, lastly, Gradle was used for 
package management and building purposes. Those mentioned libraries are totally neutral for presentation of Software Engineering.

### 1.1 Instalation and run instructions

* pre-requisites installed softwares in your machine: [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) and [Java 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html). Google if finding some difficulties, or let comment here!

* run the command `git clone git@github.com:deafjava/tictactoe.git`

* go to the project `cd tictactoe`

* In the root, run: `./gradlew build` for Unix systems (Linux, Mac) or `gradlew.bat build` for Windows

* create a json config file, with the data:

```json
{
  "size": 3,
  "characters": [
    "X",
    "O",
    "W"
  ]
}

```

* run `java -jar build/libs/game-1.0.0.jar game.json` and enjoy!

P.S. game.json is the name of config file, you can replace with one you created.

## 2. Software Engineering

From simple OOP to application of Design Patterns.
Sections are divided by: structure, gear, utils, screen, factory and exceptions.
Architecture proposition: to become the engine of the game as a separated library.

### 2.1 Structure of the Game

#### 2.1.1 Marker

Also as known as character that you set on the playfield - "X" and "O" - this class has one attribute, `character`, it's setup
during its instantation, and it doesn't allow to be setup on the run, because in the real world we don't modify character 
during a game! To handle when we try to get an empty or null character, an exception is thrown - `Marker.NotValidCharacterException`. Its equality are strictly by the `character` attribute

Why don't throw during the instantation? This is also possible, but for a better experience's scenario, for example,
setting a "?", or a random character, when handling the getter exception, instead of blocking the gaming due to bad config data.
Both scenarios are correct, it's a project decision.

```java
/**
 * The character we set in the playfield.
 *
 * I decided to call this as Marker because in a new
 * version we can also add an image for an extended
 * class
 *
* */

@Getter
@EqualsAndHashCode(of = "character", doNotUseGetters = true)
public class Marker {

    private String character;

    public Marker(String character) {
        this.character = character;
    }

    public String getCharacter() throws NotValidCharacterException {
        return Optional.ofNullable(character)
                .filter(StringUtils::hasText)
                .orElseThrow(NotValidCharacterException::new);
    }

    public class NotValidCharacterException extends Exception {
        public NotValidCharacterException() {
            super("Oops! Null or empty character has been set for this marker!");
        }
    }
}

```

#### 2.1.2 Score

For scoring the game, this class has an attribute called `wins`, that keeps the quantity of games won by a player, its value starts with zero.
This attribute is also not possible to be set on run, avoiding "cheating". This class has also a method called `incrementWin()`, 
that sum up victories of a player.

```java
/**
 * Score of a player
 * 
 * Each victory increments a win
 */
@Getter
public class Score {
    private Integer wins = 0;

    public void incrementWin() {
        wins++;
    }
}
```

#### 2.1.3 Player

The gamer of TicTacToe. A player has, of course, his/her `name`, then `score`, `marker` and `cpu` as attributes.
Only the `name` can be set on the running for correcting typos' purpose, although is open for cheating also. Other fields are
acessible only reading. `score` and `marker` are represented by the classes above described, with theirs name respectivelly.
We can see that this class template indeed represents in the real world. The setup occurs only during the instantation.

All players begin with score 0. At least, the `cpu` attribute defines if the Player is a human or a cpu, `false` and `true`, respectivelly. The equality of this class is defined by the marker only, because each player is identified by the character only set on the playfield, and name is not considered because it can happen that two players may have same name. Other fields not being considered are more obvious.

This class has a method called `won()`, that increments the score when player wins the game. 

```java
/**
 * Player - only name is editable for typos' fixing.
 *
 * Score is instantiated during the class instantiation, as player
 * always starts with fresh score.
 */

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"marker"})
public class Player {

    private Marker marker;

    @Setter(AccessLevel.PUBLIC)
    private String name;

    private Score score;

    private boolean cpu;

    public Player(String name, Marker marker, boolean cpu) {
        setName(name);
        setMarker(marker);
        setCpu(cpu);
        score = new Score();
    }

    public void won() {
        score.incrementWin();
    }
}


```

##### 2.1.3.1 NullPlayer

This is a "special" player, that represents... no player! This is a usage of a Design Pattern called "[Null Object Pattern](https://en.wikipedia.org/wiki/Null_object_pattern)", to better manage not played fields, that is used, for example, checking game fullfilness. This class is child of `Player` class. This Design Pattern helps to avoid to check if it's null or have to set, uglily, as null when there is no player in such a field.

```java
/**
 * Represents a no player, that is mostly used
 * over not yet played fields
 */

public class NullPlayer extends Player {
    public NullPlayer() {
        super("----", null, false);
    }
}

```

#### 2.1.4 Field

This represents each field of the TicTacToe playfield. For example, with a 3x3 playfield, there are 9 fields in total, so we will have 9 objects. Its attributes are `posX`, `posY` and `player`, representing X,Y position in the grid of playfield and the player that has marked on the field, as this attribute has enough data to manage related over the field. The position's attributes are enough to make difference each others.

This class has two constructor, one for all fields, that is automated from the Lombok annotation `@AllArgsConstructor`, another one special constructor for receiving position as String input and then parse to the its respective attributes. Such errors as number format or setting only one of two needed position's attributes are thrown as `InvalidInputDataException`. As input datum for position are set as human readable, starting from 1 instead of 0 as usual in the programming world, it's handled to set expected number. Also, input datum is cleaned from space, tab and alikes.

```java
/**
 * A playfield has fields. Each field is an object.
 *
 * For actions over a field, player is set as reference for all management
 *
 * As field lies over a grid, then its 2D position become as its identifier
 */

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(of = {"posX", "posY"})
public class Field {
    private int posX;
    private int posY;
    private Player player;

    public Field(String input, Player player) throws InvalidInputDataException {
        String[] pos = input
                .replaceAll("\\s", "")
                .split(",");
        try {
            setPosX(Integer.parseInt(pos[0]) - 1);
            setPosY(Integer.parseInt(pos[1]) - 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputDataException();
        }
        setPlayer(player);
    }
}
```

#### 2.1.5 Grid

This class represents the playfield, as the playfield are very
 similar to a grid, this can be filled with X,Y positioning. This class has two fields - `fields` and `size`. The `fields` is a quadratic 2D array of class `Field`. The `size` sets up the size of the quadratic 2D array. Its constructor receives the size of
 grid as argument, and throws `TooShortOrTooLargeException` when the size is less than 3 or greater than 10; after the instantiation, the fields are then instantiated with a two dimensions
 array. This class has four methods:

 * `clear()` - to clear up the `fields`.

 * `getField(int x, int y)` - to get `Field` object in the grid by its position. When the object doesn't exists, `NotMarkedFieldException` is thrown, as none player has marked in the specific field.

 * `mark(Field field)` - to mark a position with an object of `Field`, it occurs when a player choose a position to mark.

 * `remainedPositions()` - returns the list of not yet marked positions.

 ```java

 /**
  * Grid where playfield lies.
  *
  * The presence or absence of a field in the grid means the played or not yet field by a player
  */

 @NoArgsConstructor(access = AccessLevel.PRIVATE)
 public class Grid {

     @Getter
     private Field[][] fields;

     @Setter(AccessLevel.PRIVATE)
     @Getter
     private Integer size;

     public Grid(Integer size) throws TooShortOrTooLargeException {
         if (size < 3 || size > 10) {
             throw new TooShortOrTooLargeException(size);
         }
         fields = new Field[size][size];
         setSize(size);
     }

     public void clear() {
         fields = new Field[size][size];
     }

     public Field getField(int x, int y) throws NotMarkedFieldException {
         return Optional.ofNullable(fields[x][y]).orElseThrow(NotMarkedFieldException::new);
     }

     public void mark(Field field) throws InvalidPositionException {
         try {
             Optional<Field> fieldOpt = Optional.ofNullable(fields[field.getPosX()][field.getPosY()]);
             if (fieldOpt.isPresent()) throw new InvalidPositionException(field.getPosX(), field.getPosY());
             fields[field.getPosX()][field.getPosY()] = field;
         } catch (Exception e) {
             throw new InvalidPositionException(field.getPosX(), field.getPosY());
         }
     }

     public List<String> remainedPositions() {
         List<String> list = new ArrayList<>();
         for (int x = 0; x < size; x++) {
             for (int y = 0; y < size; y++) {
                 if (fields[x][y] == null) {
                     list.add((x + 1) + "," + (y + 1));
                 }
             }
         }
         return list;
     }

     public class TooShortOrTooLargeException extends Exception {
         public TooShortOrTooLargeException(Integer size) {
             super("Size must be between 3 and 10. You set: " + size);
         }
     }

     public class NotMarkedFieldException extends Exception {
         public NotMarkedFieldException() {
             super("This field hasn't been yet marked by any of players!");
         }
     }

     public class InvalidPositionException extends Exception {
         public InvalidPositionException(int x, int y) {
             super("Oops! There is no such position or it's already marked (" + (x + 1) + "," + (y + 1) + "): ");
         }
     }
 }

```

#### 2.1.6 Winner

This class handles the winner of the game. We have `player` as field, when it receives a player, it means that there was a winner, otherwise, when it's receiving the `NullPlayer`, it indicates
that there is still no winner. Another field - `fullDraw` - is a boolean to indicates that the game ended and nobody won.


```java
/**
 * To handle the winner (or not) of the game
 */

@Data
@AllArgsConstructor
public class Winner {

    private Player player;

    private boolean fullDraw;

    public boolean isWinner() {
        return !player.equals(new NullPlayer());
    }
}
```

### 2.2 Gear of the Game

#### 2.2.1 Game

This class implements the `Line` interface to check if there is a winner in some line directions.
See [Line](#line). Also it makes computer's turn to play. Computer plays in a silly way, just finding randomly an available field to mark. Of course it can be improved to make it smarter, but not a SkyNet so early ;).

Each methods expected a list of hashset, and when there is just one player and the player is not the `NullPlayer`, it means that
there is a winner for such line.

```java

/**
 * This implements the Line of TicTacToe result game
 * <p>
 * All line directions are verified to check if there's a winner.
 * <p>
 * A method that makes the computer to play
 */
public class Game implements Line {

    public Winner scan(Grid grid) {
        Player player = horizontal(grid);
        Player nullPlayer = new NullPlayer();
        if (player.equals(nullPlayer)) {
            player = vertical(grid);
        }
        if (player.equals(nullPlayer)) {
            player = straightDiagonal(grid);
        }
        if (player.equals(nullPlayer)) {
            player = inverseDiagonal(grid);
        }
        if (!player.equals(nullPlayer)) {
            grid.clear();
        }

        return new Winner(player, grid.remainedPositions().isEmpty());
    }


    @Override
    public Player horizontal(Grid grid) {
        Player noPlayer = new NullPlayer();
        for (int x = 0; x < grid.getSize(); x++) {
            Set<Player> differentPlayers = new HashSet<>();
            Player winner = null;

            for (int y = 0; y < grid.getSize(); y++) {
                try {
                    winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noPlayer);
                } catch (Grid.NotMarkedFieldException e) {
                    winner = noPlayer;
                }
                differentPlayers.add(winner);
            }
            if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
                winner.won();
                return winner;
            }
        }
        return noPlayer;
    }

    @Override
    public Player vertical(Grid grid) {
        Player noPlayer = new NullPlayer();
        for (int y = 0; y < grid.getSize(); y++) {
            Set<Player> differentPlayers = new HashSet<>();
            Player winner = null;

            for (int x = 0; x < grid.getSize(); x++) {
                try {
                    winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noPlayer);
                } catch (Grid.NotMarkedFieldException e) {
                    winner = noPlayer;
                }
                differentPlayers.add(winner);
            }
            if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
                winner.won();
                return winner;
            }
        }
        return noPlayer;
    }

    @Override
    public Player straightDiagonal(Grid grid) {
        Player noPlayer = new NullPlayer();
        Set<Player> differentPlayers = new HashSet<>();
        Player winner = null;
        for (int xy = 0; xy < grid.getSize(); xy++) {

            try {
                winner = Optional.of(grid.getField(xy, xy).getPlayer()).orElse(noPlayer);
            } catch (Grid.NotMarkedFieldException e) {
                winner = noPlayer;
            }
            differentPlayers.add(winner);
        }
        if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
            winner.won();
            return winner;
        }
        return noPlayer;
    }

    @Override
    public Player inverseDiagonal(Grid grid) {
        Player noPlayer = new NullPlayer();
        Set<Player> differentPlayers = new HashSet<>();
        Player winner = null;
        for (int y = 0, x = 2; y < grid.getSize(); y++, x--) {

            try {
                winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noPlayer);
            } catch (Grid.NotMarkedFieldException e) {
                winner = noPlayer;
            }
            differentPlayers.add(winner);
        }
        if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
            winner.won();
            return winner;
        }
        return noPlayer;
    }

    public void cpuPlays(Grid grid, Player cpuPlayer) {
        try {
            List<String> remainedPos = grid.remainedPositions();
            int i = GameUtils.randomPos(remainedPos.size());
            grid.mark(new Field(remainedPos.get(i), cpuPlayer));
        } catch (Grid.InvalidPositionException | InvalidInputDataException ignored) {
        }
    }
}
```

#### 2.2.2 Line

This is an interface that contains four significant methods - `vertical()`, `horizontal()`, `straightDiagonal()` and `inverseDiagonal()` for finding a winner. Why an interface? Because we can,
e.g., use an external service to verify for us.

### 2.3 Screen of the Game

#### 2.3.1 Printer

This is the interface that has methods to focus to printing to
the screen only. `screen()` receives `grid`, `players`, `turn`, `winner` as parameters, as they have relevant informations.
Others methods - `printContinue()`, `pressKeyContinue()`, `readInput()`, `printErr()` are self explained.

```java
/**
 * Printing the game to the screen
 */
public interface Printer {

    void screen(Grid grid, Player[] players, int turn, Winner winner);

    void printContinue();

    void pressKeyContinue();

    String readInput();

    void printErr(Throwable e);
}

```

#### 2.3.2 TicTacToeConsole

This is the implementation of `Printer`, to print the game in the console (or terminal).
Yoy may check the implementation in the project.

### 2.4 Factory

#### 2.4.1 PlayerFactory

As we have two kinds of players - human and computer (cpu) - [Factory Method Pattern](https://en.wikipedia.org/wiki/Factory_method_pattern) is a
good opportunity to apply.

```java
public class PlayerFactory {

    private static int playerSequence = 1;

    public static Player human(String marker) {
        return new Player("Player " + playerSequence++, new Marker(marker), false);
    }

    public static Player cpu(String marker) {
        return new Player("CPU", new Marker(marker), true);
    }
}

```

### 2.5 Exceptions

#### 2.5.1 InvalidInputDataException

This exception is thrown as generic Exception focused to input issues.

```java
public class InvalidInputDataException extends Throwable {
    public InvalidInputDataException() {
        super("Input data seem to be invalid! Example of valid data: 1,2: ");
    }
}
```

### 2.6 Utils

We have Utils for simple operations or boilerplate operations.

### 2.7 TicTacToe

Finally, the heart class!

This class make the game happens, there are two methods, using overload: `play()` when one of player is a computer, other when all are humans. For
the purpose of this presentation, playing with computer has been chosen.

A Design Pattern - [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection) - is shown in the field `printer`, the advantage will be shown in the Integration Test header below.

```java
@Component
public class TicTacToe {

    @Autowired
    private Printer printer;

    public void play(int size, String[] characters) {
        play(size, characters, false);
    }

    public void play(int size, String[] characters, boolean allHumans) {

        try {
            Grid grid = new Grid(size);
            Game game = new Game();

            Player[] players = GameUtils.initPlayers(characters, allHumans);

            boolean endGame = false;

            int turn = GameUtils.randomTurn();

            while (!endGame) {
                Winner winner = game.scan(grid);
                if (winner.isWinner() || winner.isFullDraw()) {
                    turn = GameUtils.randomTurn();
                }
                printer.screen(grid, players, turn, winner);
                if (winner.isFullDraw()) {
                    grid.clear();
                    printer.printContinue();
                    printer.pressKeyContinue();
                    continue;
                }
                if (players[turn].isCpu()) {
                    game.cpuPlays(grid, players[turn]);
                    turn = GameUtils.next(turn);
                } else {

                    String input = printer.readInput();

                    if (endGame = IOUtils.parseExit(input)) {
                        continue;
                    }
                    boolean again;
                    do {
                        try {
                            grid.mark(new Field(input, players[turn]));
                            again = false;
                            turn = GameUtils.next(turn);
                        } catch (Grid.InvalidPositionException | InvalidInputDataException e) {
                            printer.printErr(e);
                            again = true;
                            input = printer.readInput();
                        }
                    } while (again);
                }
            }
        } catch (Grid.TooShortOrTooLargeException e) {
            printer.printErr(e);
        }
    }
}
```

## 3. Client of the TicTacToe engine

We can see the usage of the engine from a client, importing it as a module for presentation's purpose, that can be replaced to a path where engine can be laid.

```gradle

    compile project(":engine")

```

### 3.1 File config

As the input data for the game config is from a file, I chose `json` structure to populate key-values of data.

```json
{
  "size": 3,
  "characters": [
    "X",
    "O",
    "W"
  ]
}
```

### 3.2 ConfigReadHelper

This class is a hub from file reading, translating data into POJO and at the end providing data for
game config - `getSize()` and `getCharacters()`

```java
public class ConfigReadHelper {

    private ConfigRead configRead;

    public ConfigReadHelper(String fileName) throws IOException {
        String configData = ConfigFileReader.read(fileName);
        configRead = new ObjectMapper().readValue(configData, ConfigRead.class);
    }

    public int getSize() {
        return configRead.getSize();
    }

    public String[] getCharacters() {
        return configRead.getCharacters();

    }
}
```

### 3.3 Integration Tests

This is the icing on the cake of Software Engineering: using Dependency Injection for reliable test environment.
In the real world, the inputs are made by humans fingers on the keyboard, and classes to be injected are setup in the `TicTacToeConfig`.
When we must run the Integration Tests, other classes is used to allow good simulation - `TestTicTacToeConfig` and
provides a different behavioured `Printer` interface implementation - `TestTicTacToeConsole`.

```java
@ActiveProfiles("test")
public class TestTicTacToeConsole extends TicTacToeConsole {

    private static final String[] listInputs = new String[]{"1,1", "3,3", "2,2", "1,3", "3,2", "3,1", "2,1", "1, 2", "2,3", "exit"};
    private int seq = 0;

    @Override
    public String readInput() {
        return listInputs[seq++];
    }

    @Override
    public void pressKeyContinue() {

    }
}
```

#### 3.3.1 TicTacToeGameTest

This is the Integration Test class, two main scenarios are considered:

* When no file are provided, default values are setup in place.

* Screen print scenarios that must happen, no matter how game flowed.

#### 3.3.2 Unit Tests

In the engine, the code coverage is well spread, allowing to protect the code from undesirable changes, that then is completed by the Integration above shown.