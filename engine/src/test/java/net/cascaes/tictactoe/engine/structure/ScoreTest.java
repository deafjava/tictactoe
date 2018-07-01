package net.cascaes.tictactoe.engine.structure;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ScoreTest {

    @Test
    public void successfullyIncrementWin() {
        Score score = new Score();

        int randomNum = ThreadLocalRandom.current().nextInt(3, 20);

        assertTrue(score.getWins() == 0);

        IntStream.range(0, randomNum).forEach(i -> score.incrementWin());

        assertTrue(score.getWins() == randomNum);
    }

}