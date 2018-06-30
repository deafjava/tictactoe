package net.cascaes.tictactoe.game;

import net.cascaes.tictactoe.game.config.TestTicTacToeConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestTicTacToeConfig.class})
public class TicTacToeGameTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private PrintStream console;

    @Before
    public void setUp() {
        out.reset();
        err.reset();
        console = System.out;
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @Test
    public void useDefaultWhenFileNotProvidedTest() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("config_game.json").getFile());
        System.out.println(file.getAbsolutePath());

        GameApplication.main(new String[]{""});
        String e = err.toString();
        assertTrue(e.contains("File reading error! Populating with default values."));
    }

    @Test
    public void playFieldFilledTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("config_game.json").getFile());

        GameApplication.main(new String[]{file.getAbsolutePath()});
        String r = out.toString();
        String e = err.toString();
        assertFalse(e.contains("File reading error! Populating with default values."));
        assertTrue(r.contains(
                "-----------------------------------------\n" +
                "----------- Tic Tac Toe 2.0 -------------\n" +
                "-----------------------------------------\n" +
                "\n" +
                "SCORE                                    \n" +
                "Player 1:\t0\n" +
                "Player 2:\t0\n" +
                "Computer:\t0\n" +
                "\n" +
                "  | 1 | 2 | 3 | \n" +
                "----------------\n" +
                "1 |   |   |   | \n" +
                "----------------\n" +
                "2 |   |   |   | \n" +
                "----------------\n" +
                "3 |   |   |   | \n" +
                "----------------\n" +
                "\n" +
                "-- Type \"exit\" to leave --"));

        assertTrue(r.contains("| X |"));
        assertTrue(r.contains("| O |"));
        assertTrue(r.contains("| W |"));

        assertTrue(e.contains("Oops! There is no such position or it's already marked ("));

        assertTrue(r.contains("CPU's turn. CPU thinking...\n" +
                "\n" +
                "CPU played!"));

        assertTrue(r.contains("won! Congratulations!") || r.contains("Ooh! Full draw! Nobody won! Let's try again!\n" +
                "\n" +
                "Press ENTER to continue..."));


    }

    @After
    public void tearDown() {
        System.setOut(console);
        System.setErr(console);
    }
}