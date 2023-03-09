package ru.hse.fmcs.FunctionCaller;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FunctionCaller implements FunctionCallerInterface {
    public FunctionCaller() {
        functions.put("echo", FunctionCaller::echoImplementation);
        functions.put("cat", FunctionCaller::catImplementation);
        functions.put("wc", FunctionCaller::wcImplementation);
        functions.put("pwd", FunctionCaller::pwdImplementation);
        functions.put("exit", FunctionCaller::exitImplementation);
    }

    @Override
    public String HandleFunction(Query query) throws WrongArgumentsException, UnexpectedFunctionName, IOException, ExitException {
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
        return String.join("\n", Files.readAllLines(path));
    }

    private static String wcImplementation(Query query) throws WrongArgumentsException, IOException {
        String catResult = catImplementation(query);
        int lines_number = StringUtils.countMatches(catResult, "\n");
        StringTokenizer stringTokenizer = new StringTokenizer(catResult);
        int words_number = stringTokenizer.countTokens();
        int chars_number = catResult.length();
        ArrayList<String> list = new ArrayList<>();
        list.add(String.valueOf(lines_number));
        list.add(String.valueOf(words_number));
        list.add(String.valueOf(chars_number));
        return String.join(" ", list);
    }

    private static String pwdImplementation(Query query) {
        return ROOT_DIRECTORY;
    }

    private static String exitImplementation(Query query) throws ExitException {
        throw new ExitException();
    }

    private static final String ROOT_DIRECTORY = "./root/";

}
