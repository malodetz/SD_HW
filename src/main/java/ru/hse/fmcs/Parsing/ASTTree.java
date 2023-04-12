package ru.hse.fmcs.Parsing;

public class ASTTree {
  interface ASTNode {
    void evaluate();
  }


  public static class ASTNodeSingleCommand implements ASTNode {
    final ASTNodeAssignment assign;
    final ASTNodeFunction func;

    public ASTNodeSingleCommand(final ASTNodeAssignment assign, final ASTNodeFunction func) {
      this.assign = assign;
      this.func = func;
    }

    public void evaluate() {

    }
  }

  public static class ASTNodeFunction implements ASTNode {
    final String functionName;
    final ASTNodeArgumentsList args;

    public ASTNodeFunction(final String functionName, final ASTNodeArgumentsList args) {
      this.functionName = functionName;
      this.args = args;
    }

    public void evaluate() {

    }
  }

  public static class ASTNodeArgumentsList implements ASTNode {
    final ASTNodeArgument head;
    final ASTNodeArgumentsList tail;

    public ASTNodeArgumentsList(ASTNodeArgument head, ASTNodeArgumentsList tail) {
      this.head = head;
      this.tail = tail;
    }

    public void evaluate() {

    }
  }

  public static class ASTNodeArgument implements ASTNode {
    final String arg;

    public ASTNodeArgument(final String arg) {
      this.arg = arg;
    }

    public void evaluate() {

    }
  }

  public static class ASTNodeAssignment implements ASTNode {
    final String assign;
    final ASTNodeAssignment next;

    public ASTNodeAssignment(final String assign, final ASTNodeAssignment next) {
      this.assign = assign;
      this.next = next;
    }

    public void evaluate() {

    }
  }

  private final ASTNode root;

  public ASTTree(final ASTNode root) {
    this.root = root;
  }

//  public void traverse() {
//
//  }
}
