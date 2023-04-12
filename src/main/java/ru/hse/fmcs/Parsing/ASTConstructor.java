package ru.hse.fmcs.Parsing;

import Parsing.Lexer;
import Parsing.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author sergey
 * <p>
 * Files generated by CUP located in wrong package. It is
 * a bug of that parser.
 */
public class ASTConstructor {
  /**
   * Parser implementation using for parsing.
   */
  private final Parser parser;

  public ASTConstructor(InputStream is) {
    final SymbolFactory sf = new ComplexSymbolFactory();
    final Lexer lexer = new Lexer(new InputStreamReader(is), sf);
    parser = new Parser(lexer, sf);
  }

  public ASTTree consumeInput() throws ParsingException {
    ASTTree.ASTNode root;
    try {
      root = (ASTTree.ASTNode) parser.parse().value;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ParsingException(e);
    }
    return new ASTTree(root);
  }
}
