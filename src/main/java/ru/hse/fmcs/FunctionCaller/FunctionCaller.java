package ru.hse.fmcs.FunctionCaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FunctionCaller implements FunctionCallerInterface {
    FunctionCaller() {
        functions.put("echo", FunctionCaller::echoImplementation);
        functions.put("cat", FunctionCaller::catImplementation);
    }

    @Override
    public String HandleFunction(Query query) throws WrongArgumentsException, UnexpectedFunctionName, IOException {
        if (functions.containsKey(query.name)) {
            return functions.get(query.name).apply(query);
        }
        throw new UnexpectedFunctionName("Unexpected function name " + query.name + "!");
    }

    private static String echoImplementation(Query query) {
        return String.join(" ", query.args);
    }

    private static String catImplementation(Query query) throws WrongArgumentsException, IOException {
        if (query.args.size() != 1) {
            throw new WrongArgumentsException("cat expected exactly 1 argument! Have " + query.args.size() + " arguments.");
        }
        String filename = query.args.get(0);
        Path path = Paths.get(ROOT_DIRECTORY + filename);
        return Files.readAllLines(path).get(0);
    }

    private static String ROOT_DIRECTORY = "../kek/";

}
