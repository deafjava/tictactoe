# TicTacToe 2.0 with Software Engineering

## Overview

TicTacToe is a famous game, and it's also first time pen and paper gaming for many kids. 
The structure of the game is the great opportunity to show the art of OOP design.
Growing from OOP to Design Pattern was the way to bring the game to life.
From Design Pattern to Game library to become as engine to be used to various interfaces - from Console to an Online API.

For this project, Console was used as interface. Java 8 was used to be reference language. Spring Framework was used to
show Software Engineering in action. Lombok library to show code cleaner for presentation here, avoiding boilerplate. 
Jackson Library was used to show the example of json config file content converting to POJO. And, lastly, Gradle was used for 
package management and building purposes. Those mentioned libraries are totally neutral for presentation of Software Engineering.

## Structure of the game - OOP Design

Starting from simpler to more complex. Each element described is a class.

### Marker

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

### Score

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

### Player

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

#### NullPlayer

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

