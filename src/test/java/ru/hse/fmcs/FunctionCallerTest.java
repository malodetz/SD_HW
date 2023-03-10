package ru.hse.fmcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.FunctionCaller.ExitException;
import ru.hse.fmcs.FunctionCaller.FunctionCaller;
import ru.hse.fmcs.FunctionCaller.Query;
import ru.hse.fmcs.FunctionCaller.WrongArgumentsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionCallerTest {
    private final String correct_filename = "lol.txt";
    private final String wrong_filename = "no-existing-file.txt";
    private final String cat = "cat";
    private final String echo = "echo";
    private final String wc = "wc";
    private final String pwd = "pwd";
    private final String exit = "exit";

    private FunctionCaller functionCaller;

    @BeforeEach
    public void prepareFunctionCaller() {
        functionCaller = new FunctionCaller();
    }

    @Test
    public void catSuccessTest() {
        Query catQuery = new Query(cat, new ArrayList<>(Collections.singleton(correct_filename)));
        assertDoesNotThrow(() -> assertEquals("Good test for CLI!\n" + "\n" + "Bye :)",
                functionCaller.HandleFunction(catQuery)));
    }

    @Test
    public void catNoSuchFileTest() {
        Query catQuery = new Query(cat, new ArrayList<>(Collections.singleton(wrong_filename)));
        assertThrows(IOException.class, () -> functionCaller.HandleFunction(catQuery));
    }

    @Test
    public void catNotSingleArgumentTest() {
        Query catQuery = new Query(cat, Stream.of(correct_filename, wrong_filename).collect(Collectors.toCollection(ArrayList::new)));
        WrongArgumentsException thrown = assertThrows(WrongArgumentsException.class, () -> functionCaller.HandleFunction(catQuery));
        assertEquals("Expected 1 argument! Got 2.", thrown.getMessage());
    }

    @Test
    public void echoNoArgumentsTest() {
        Query echoQuery = new Query(echo, new ArrayList<>());
        assertDoesNotThrow(() -> assertEquals("", functionCaller.HandleFunction(echoQuery)));
    }

    @Test
    public void echoMultipleArgumentsTest() {
        Query echoQuery = new Query(echo, Stream.of("kek", "lol").collect(Collectors.toCollection(ArrayList::new)));
        assertDoesNotThrow(() -> assertEquals("kek lol", functionCaller.HandleFunction(echoQuery)));
    }

    @Test
    public void wcSuccessTest() {
        Query wcQuery = new Query(wc, new ArrayList<>(Collections.singleton(correct_filename)));
        assertDoesNotThrow(() -> assertEquals("3 6 26", functionCaller.HandleFunction(wcQuery)));
    }

    @Test
    public void wcNoSuchFileTest() {
        Query wcQuery = new Query(wc, new ArrayList<>(Collections.singleton(wrong_filename)));
        assertThrows(IOException.class, () -> functionCaller.HandleFunction(wcQuery));
    }

    @Test
    public void wcNotSingleArgumentTest() {
        Query wcQuery = new Query(wc, Stream.of(correct_filename, wrong_filename).collect(Collectors.toCollection(ArrayList::new)));
        WrongArgumentsException thrown = assertThrows(WrongArgumentsException.class, () -> functionCaller.HandleFunction(wcQuery));
        assertEquals("Expected 1 argument! Got 2.", thrown.getMessage());
    }

    @Test
    public void pwdSuccessTest() {
        Query pwdQuery = new Query(pwd, new ArrayList<>());
        assertDoesNotThrow(() ->
                assertEquals(FunctionCaller.ROOT_DIRECTORY, functionCaller.HandleFunction(pwdQuery)));
    }

    @Test
    public void pwdNotZeroArgumentsTest() {
        Query pwdQuery = new Query(pwd, new ArrayList<>(Collections.singleton(correct_filename)));
        assertThrows(WrongArgumentsException.class, () -> functionCaller.HandleFunction(pwdQuery));
    }


    @Test
    public void exitSuccessTest() {
        Query exitQuery = new Query(exit, new ArrayList<>());
        assertThrows(ExitException.class, () -> functionCaller.HandleFunction(exitQuery));
    }

    @Test
    public void exitNotZeroArgumentsTest() {
        Query exitQuery = new Query(exit, new ArrayList<>(Collections.singleton(correct_filename)));
        assertThrows(WrongArgumentsException.class, () -> functionCaller.HandleFunction(exitQuery));
    }

}
