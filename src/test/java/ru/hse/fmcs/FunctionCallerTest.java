package ru.hse.fmcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private final String cat = "cat";
    private final String echo = "echo";

    private FunctionCaller functionCaller;

    @BeforeEach
    public void prepareFunctionCaller() {
        functionCaller = new FunctionCaller();
    }

    @Test
    public void catSuccessTest() {
        String filename = "lol.txt";
        Query catQuery = new Query(cat, new ArrayList<>(Collections.singleton(filename)));
        assertDoesNotThrow(() -> {
            assertEquals("Good test for CLI!\n" + "\n" + "Bye :)",
                    functionCaller.HandleFunction(catQuery));
        });
    }

    @Test
    public void catNoArgumentsTest() {
        Query catQuery = new Query(cat, new ArrayList<>());
        assertThrows(WrongArgumentsException.class,
                () -> functionCaller.HandleFunction(catQuery),
                "cat expected exactly 1 argument! Have 0 arguments.");
    }

    @Test
    public void catNoSuchFileTest() {
        String filename = "no-existing-file.txt";
        Query catQuery = new Query(cat, new ArrayList<>(Collections.singleton(filename)));
        assertThrows(IOException.class, () -> functionCaller.HandleFunction(catQuery));
    }

    @Test
    public void catMultipleArgumentsTest() {
        String filename = "no-existing-file.txt";
        Query catQuery = new Query(cat, Stream.of(filename, filename).collect(Collectors.toCollection(ArrayList::new)));
        assertThrows(WrongArgumentsException.class,
                () -> functionCaller.HandleFunction(catQuery),
                "cat expected exactly 1 argument! Have 2 arguments.");
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
}
