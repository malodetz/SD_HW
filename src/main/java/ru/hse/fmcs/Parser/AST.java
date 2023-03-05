package ru.hse.fmcs.Parser;

import java.util.ArrayList;

public class AST {
    private class ASTNode {
        String command;
        ArrayList<ASTNode> arguments;

        public ASTNode(String command, ArrayList<ASTNode> arguments) {
            this.command = command;
            this.arguments = arguments;
        }
    }

    ASTNode root;
}
