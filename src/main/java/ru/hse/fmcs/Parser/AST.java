package ru.hse.fmcs.Parser;

import java.util.ArrayList;

abstract class ASTNode {
}

class Command extends ASTNode {
    String name;
    ArrayList<ASTNode> arguments;

    public Command(String name, ArrayList<ASTNode> arguments) {
        this.name = name;
        this.arguments = arguments;
    }
}

class Argument extends ASTNode {
    String value;

    public Argument(String value) {
        this.value = value;
    }
}

class Assignment extends ASTNode {
    String variable, value;

    public Assignment(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }
}

class Pipe extends ASTNode {
    ASTNode left, right;

    public Pipe(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }
}

public class AST {
    ASTNode root;

    public AST(ASTNode root) {
        this.root = root;
    }
}
