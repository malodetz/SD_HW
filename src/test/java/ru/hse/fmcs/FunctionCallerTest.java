package ru.hse.fmcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.fmcs.FunctionCaller.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionCallerTest {
    private final String correct_filename = "test_file.txt";
    private final String wrong_filename = "no-existing-file.txt";
    private final String cat = "cat";
    private final String echo = "echo";
    private final String wc = "wc";
    private final String pwd = "pwd";
    private final String exit = "exit";
    private final String nonExistFunction = "nonExistFunction";

    private FunctionCaller functionCaller;

    @BeforeEach
    public void prepareFunctionCaller() {
        functionCaller = new FunctionCaller();
    }

    @Test
    public void catSuccessTest() {
        Query catQuery = new Query(cat, new ArrayList<>(Collections.singleton(correct_filename)));
        assertDoesNotThrow(() -> assertEquals("Good test for CLI!\n" + "\n" + "Bye :)",
                functionCaller.handleFunction(catQuery)));
    }

    @Test
    public void catNoSuchFileTest() {
        Query catQuery = new Query(cat, new ArrayList<>(Collections.singleton(wrong_filename)));
        assertThrows(IOException.class, () -> functionCaller.handleFunction(catQuery));
    }

    @Test
    public void catNotSingleArgumentTest() {
        Query catQuery = new Query(cat, Stream.of(correct_filename, wrong_filename).collect(Collectors.toCollection(ArrayList::new)));
        FunctionCallException thrown = assertThrows(FunctionCallException.class, () -> functionCaller.handleFunction(catQuery));
        assertEquals("Expected 1 argument! Got 2.", thrown.getMessage());
    }

    @Test
    public void echoNoArgumentsTest() {
        Query echoQuery = new Query(echo, new ArrayList<>());
        assertDoesNotThrow(() -> assertEquals("", functionCaller.handleFunction(echoQuery)));
    }

    @Test
    public void echoMultipleArgumentsTest() {
        Query echoQuery = new Query(echo, Stream.of("kek", "lol").collect(Collectors.toCollection(ArrayList::new)));
        assertDoesNotThrow(() -> assertEquals("kek lol", functionCaller.handleFunction(echoQuery)));
    }

    @Test
    public void wcSuccessTest() {
        Query wcQuery = new Query(wc, new ArrayList<>(Collections.singleton(correct_filename)));
        assertDoesNotThrow(() -> assertEquals("3 6 26", functionCaller.handleFunction(wcQuery)));
    }

    @Test
    public void wcNoSuchFileTest() {
        Query wcQuery = new Query(wc, new ArrayList<>(Collections.singleton(wrong_filename)));
        assertThrows(IOException.class, () -> functionCaller.handleFunction(wcQuery));
    }

    @Test
    public void wcNotSingleArgumentTest() {
        Query wcQuery = new Query(wc, Stream.of(correct_filename, wrong_filename).collect(Collectors.toCollection(ArrayList::new)));
        FunctionCallException thrown = assertThrows(FunctionCallException.class, () -> functionCaller.handleFunction(wcQuery));
        assertEquals("Expected 1 argument! Got 2.", thrown.getMessage());
    }

    @Test
    public void pwdSuccessTest() {
        Query pwdQuery = new Query(pwd, new ArrayList<>());
        assertDoesNotThrow(() ->
                assertEquals(FunctionCaller.ROOT_DIRECTORY, functionCaller.handleFunction(pwdQuery)));
    }

    @Test
    public void pwdNotZeroArgumentsTest() {
        Query pwdQuery = new Query(pwd, new ArrayList<>(Collections.singleton(correct_filename)));
        assertThrows(FunctionCallException.class, () -> functionCaller.handleFunction(pwdQuery));
    }


    @Test
    public void exitSuccessTest() {
        Query exitQuery = new Query(exit, new ArrayList<>());
        assertThrows(ExitException.class, () -> functionCaller.handleFunction(exitQuery));
    }

    @Test
    public void exitNotZeroArgumentsTest() {
        Query exitQuery = new Query(exit, new ArrayList<>(Collections.singleton(correct_filename)));
        assertThrows(FunctionCallException.class, () -> functionCaller.handleFunction(exitQuery));
    }

    @Test
    public void NonExistFunctionTest() {
        Query nonExistFunctionQuery = new Query(nonExistFunction, new ArrayList<>());
        assertThrows(UnexpectedFunctionName.class, () -> functionCaller.handleFunction(nonExistFunctionQuery));
    }

}
