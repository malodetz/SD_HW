package ru.hse.fmcs.FunctionCaller;

import java.util.HashMap;
import java.util.stream.Stream;

public class FunctionCaller implements FunctionCallerInterface {
    FunctionCaller() {
        functions.put("cat", FunctionCaller::catImpl);
    }

    @Override
    public String HandleFunction(Query query) throws WrongArgumentsException, UnexpectedFunctionName {
        if (functions.containsKey(query.name)) {
            return functions.get(query.name).apply(query);
        }
        throw new UnexpectedFunctionName("Unexpected function name " + query.name + "!");
    }

    private static String catImpl(Query query) throws WrongArgumentsException {
        if (query.args.size() != 1) {
            throw new WrongArgumentsException("cat expected exactly 1 argument! Have " + query.args.size() + " arguments.");
        }
        return query.args.get(0);
    }

}
