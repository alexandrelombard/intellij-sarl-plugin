package io.sarl.idea.lexer

import com.intellij.lexer.FlexAdapter
import io.sarl.idea.language.parser._SarlLexer

class SarlLexerAdapter : FlexAdapter(_SarlLexer(null))