package io.sarl.idea.language.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.sarl.idea.language.psi.SarlTypes.*;

%%

%{
  public _SarlLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SarlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

STRING=\"[^\"]*\"
NUMBER=[0-9]+
IMPORT_PACKAGE=[a-zA-Z_][a-zA-Z0-9_]*(\.[a-zA-Z_][a-zA-Z0-9_]*)*\.\*
SIMPLE_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*.*?\*"/"
WHITE_SPACE=[ \t\n\x0B\f\r]+

%%
<YYINITIAL> {
  {WHITE_SPACE}            { return WHITE_SPACE; }

  "{"                      { return LB; }
  "}"                      { return RB; }
  "["                      { return LBR; }
  "]"                      { return RBR; }
  "("                      { return LP; }
  ")"                      { return RP; }
  ","                      { return COMMA; }
  ":"                      { return COLON; }
  ";"                      { return SEMICOLON; }
  "."                      { return DOT; }
  "=="                     { return OP_EQUAL; }
  "!="                     { return OP_NOT_EQUAL; }
  ">"                      { return OP_GT; }
  "<"                      { return OP_LT; }
  ">="                     { return OP_GEQ; }
  "<="                     { return OP_LEQ; }
  "&&"                     { return OP_AND; }
  "||"                     { return OP_OR; }
  "="                      { return OP_EQ; }
  "-"                      { return OP_MINUS; }
  "+"                      { return OP_PLUS; }
  "*"                      { return OP_TIMES; }
  "/"                      { return OP_DIV; }
  "%"                      { return OP_MOD; }
  "package"                { return PACKAGE; }
  "import"                 { return IMPORT; }
  "class"                  { return CLASS; }
  "interface"              { return INTERFACE; }
  "agent"                  { return AGENT; }
  "event"                  { return EVENT; }
  "behavior"               { return BEHAVIOR; }
  "skill"                  { return SKILL; }
  "capacity"               { return CAPACITY; }
  "private"                { return PRIVATE; }
  "public"                 { return PUBLIC; }
  "protected"              { return PROTECTED; }
  "abstract"               { return ABSTRACT; }
  "on"                     { return ON; }
  "val"                    { return VAL; }
  "return"                 { return RETURN; }
  "occurrence"             { return OCCURRENCE; }
  "this"                   { return THIS; }
  "if"                     { return IF; }
  "else"                   { return ELSE; }
  "break"                  { return BREAK; }
  "continue"               { return CONTINUE; }
  "true"                   { return TRUE; }
  "false"                  { return FALSE; }
  "while"                  { return WHILE; }
  "do"                     { return DO; }
  "for"                    { return FOR; }
  "new"                    { return NEW; }
  "def"                    { return DEF; }
  "uses"                   { return USES; }
  "var"                    { return VAR; }

  {STRING}                 { return STRING; }
  {NUMBER}                 { return NUMBER; }
  {IMPORT_PACKAGE}         { return IMPORT_PACKAGE; }
  {SIMPLE_IDENTIFIER}      { return SIMPLE_IDENTIFIER; }
  {LINE_COMMENT}           { return LINE_COMMENT; }
  {BLOCK_COMMENT}          { return BLOCK_COMMENT; }
  {WHITE_SPACE}            { return WHITE_SPACE; }

}

[^] { return BAD_CHARACTER; }
