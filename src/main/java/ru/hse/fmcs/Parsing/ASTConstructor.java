package ru.hse.fmcs.Parsing;

import Parsing.Lexer;
import Parsing.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;
import ru.hse.fmcs.Parsing.ASTNode.ASTNode;

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

  public ASTConstructor(final InputStream is) {
    final SymbolFactory sf = new ComplexSymbolFactory();
    final Lexer lexer = new Lexer(new InputStreamReader(is), sf);
    parser = new Parser(lexer, sf);
  }

  public AST consumeInput() throws ParsingException {
    ASTNode root;
    try {
      root = (ASTNode) parser.parse().value;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ParsingException(e);
    }
    return new AST(root);
  }
}
