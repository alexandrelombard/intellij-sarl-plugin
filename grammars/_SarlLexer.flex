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

NL=(\n|\r\n|\r)
WS=[ \t\n\x0B\f\r]+
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*(.|\n)*\*"/"
HEX_NUMBER=0x([0-9A-Fa-f])+
NUMBER=[0-9]+(\.[0-9]*)?
STRING=\"([^\"]|\\\")*\"
BOOL=true|false
ID=[A-Za-z][A-Za-z0-9_]*

%%
<YYINITIAL> {
  {WHITE_SPACE}        { return WHITE_SPACE; }

  ";"                  { return SEMI; }
  ","                  { return COMMA; }
  ":"                  { return COLON; }
  "."                  { return DOT; }
  "("                  { return LP; }
  ")"                  { return RP; }
  "{"                  { return LB; }
  "}"                  { return RB; }
  "="                  { return EQ; }
  "+="                 { return OP_PLUS_EQ; }
  "-="                 { return OP_MINUS_EQ; }
  "*="                 { return OP_TIMES_EQ; }
  "/="                 { return OP_DIV_EQ; }
  "++"                 { return OP_INC; }
  "--"                 { return OP_DEC; }
  "+"                  { return OP_PLUS; }
  "-"                  { return OP_MINUS; }
  "*"                  { return OP_TIMES; }
  "/"                  { return OP_DIVIDE; }
  "%"                  { return OP_MODULUS; }
  "<="                 { return OP_LEQ; }
  ">="                 { return OP_GEQ; }
  "<"                  { return OP_LT; }
  ">"                  { return OP_GT; }
  "=="                 { return OP_EQ; }
  "!="                 { return OP_NEQ; }
  "||"                 { return OP_OR; }
  "&&"                 { return OP_AND; }
  "break"              { return BREAK; }
  "continue"           { return CONTINUE; }
  "class"              { return CLASS; }
  "event"              { return EVENT; }
  "agent"              { return AGENT; }
  "interface"          { return INTERFACE; }
  "skill"              { return SKILL; }
  "capacity"           { return CAPACITY; }
  "val"                { return VAL; }
  "var"                { return VAR; }
  "package"            { return PACKAGE; }
  "import"             { return IMPORT; }
  "public"             { return PUBLIC; }
  "private"            { return PRIVATE; }
  "protected"          { return PROTECTED; }
  "on"                 { return ON; }
  "new"                { return NEW; }
  "def"                { return DEF; }
  "occurrence"         { return OCCURRENCE; }
  "int"                { return INT; }
  "float"              { return FLOAT; }
  "double"             { return DOUBLE; }
  "this"               { return THIS; }
  "it"                 { return IT; }
  "true"               { return TRUE; }
  "false"              { return FALSE; }
  "behavior"           { return BEHAVIOR; }
  "uses"               { return USES; }
  "return"             { return RETURN; }
  "if"                 { return IF; }
  "else"               { return ELSE; }
  "while"              { return WHILE; }
  "do"                 { return DO; }
  "for"                { return FOR; }

  {NL}                 { return NL; }
  {WS}                 { return WS; }
  {LINE_COMMENT}       { return LINE_COMMENT; }
  {BLOCK_COMMENT}      { return BLOCK_COMMENT; }
  {HEX_NUMBER}         { return HEX_NUMBER; }
  {NUMBER}             { return NUMBER; }
  {STRING}             { return STRING; }
  {BOOL}               { return BOOL; }
  {ID}                 { return ID; }

}

[^] { return BAD_CHARACTER; }
