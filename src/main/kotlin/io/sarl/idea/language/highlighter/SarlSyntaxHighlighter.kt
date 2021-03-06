package io.sarl.idea.language.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import io.sarl.idea.language.psi.SarlTypes
import io.sarl.idea.lexer.SarlLexerAdapter


class SarlSyntaxHighlighter : SyntaxHighlighterBase() {
    val LINE_COMMENT = createTextAttributesKey("LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val BLOCK_COMMENT = createTextAttributesKey("BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
    val KEYWORD = createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val STRING = createTextAttributesKey("STRING", DefaultLanguageHighlighterColors.STRING)

    private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
    private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT)
    private val KEYWORD_KEYS = arrayOf(KEYWORD)
    private val STRING_KEYS = arrayOf(STRING)
    private val EMPTY_KEYS = emptyArray<TextAttributesKey>()

    override fun getHighlightingLexer() = SarlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when(tokenType) {
            SarlTypes.BLOCK_COMMENT -> BLOCK_COMMENT_KEYS
            SarlTypes.LINE_COMMENT -> LINE_COMMENT_KEYS
            SarlTypes.STRING -> STRING_KEYS
            SarlTypes.CLASS,
                SarlTypes.USES,
                SarlTypes.INTERFACE,
                SarlTypes.AGENT,
                SarlTypes.SKILL,
                SarlTypes.BEHAVIOR,
                SarlTypes.CAPACITY,
                SarlTypes.EVENT,
                SarlTypes.PUBLIC,
                SarlTypes.PRIVATE,
                SarlTypes.PROTECTED,
                SarlTypes.VAL,
                SarlTypes.VAR,
                SarlTypes.DEF,
                SarlTypes.PACKAGE,
                SarlTypes.ON,
                SarlTypes.RETURN,
                SarlTypes.IMPORT,
                SarlTypes.OCCURRENCE,
                SarlTypes.THIS,
                SarlTypes.IF,
                SarlTypes.ELSE,
                SarlTypes.CONTINUE,
                SarlTypes.BREAK,
                SarlTypes.TRUE,
                SarlTypes.FALSE,
                SarlTypes.WHILE,
                SarlTypes.DO,
                SarlTypes.FOR,
                SarlTypes.NEW -> KEYWORD_KEYS
            else -> EMPTY_KEYS
        }
    }
}
