package Parsing;
import java_cup.runtime.*;

%%

%public
%class Lexer
%unicode
%cupsym ParserSym
%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();
  SymbolFactory sf;

  public Lexer(java.io.Reader in, SymbolFactory sf) {
    this(in);
    this.sf = sf;
  }

  public Symbol symbol(String plainname, int terminalcode, String lexem) {
      return sf.newSymbol(plainname, terminalcode, lexem);
   }
%}

%eofval{
    return sf.newSymbol("EOF", ParserSym.EOF);
%eofval}

NewLine = \r|\n|\r\n
WhiteSpace = \s
Word = [^\s\"\']+

%state SINGLE_QUOTED_STRING, DOUBLE_QUOTED_STRING

%%

<YYINITIAL> {
    { Word } {
      String token = yytext();
      int asgnIdx = token.indexOf('=');
      if (asgnIdx == -1) {
        System.out.println(token);
        return symbol("Word", ParserSym.Word, token);
      }
      String name = token.substring(0, asgnIdx);
      if (name.matches("[a-z_A-Z][a-z_A-Z0-9]*")) {
        return symbol("AssignmentWord", ParserSym.AssignmentWord, token);
      }
      return symbol("Word", ParserSym.Word, token);
    }

    \" { string.setLength(0); yybegin(DOUBLE_QUOTED_STRING); }
    \' { string.setLength(0); yybegin(SINGLE_QUOTED_STRING); }

    { WhiteSpace } {}
}

<DOUBLE_QUOTED_STRING> {
    \" { yybegin(YYINITIAL); return symbol("StringLiteral", ParserSym.StringLiteral, string.toString()); }
    [^\"]+ { string.append(yytext()); }
}

<SINGLE_QUOTED_STRING> {
    \' { yybegin(YYINITIAL); return symbol("StringLiteral", ParserSym.StringLiteral, string.toString()); }
    [^\']+ { string.append(yytext()); }
}
