package io.sarl.idea.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import io.sarl.idea.SarlLanguage
import io.sarl.idea.language.parser.SarlParser
import io.sarl.idea.language.psi.SarlFile
import io.sarl.idea.language.psi.SarlTypes
import io.sarl.idea.lexer.SarlLexerAdapter

class SarlParserDefinition : ParserDefinition {
    companion object {
        val WHITE_SPACES = TokenSet.EMPTY   // FIXME
        val COMMENTS = TokenSet.create(SarlTypes.LINE_COMMENT, SarlTypes.BLOCK_COMMENT)
        val FILE = IFileElementType(SarlLanguage)
    }

    override fun createParser(project: Project) = SarlParser()
    override fun createFile(viewProvider: FileViewProvider) = SarlFile(viewProvider)

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.create(SarlTypes.STRING)
    }

    override fun getFileNodeType() = FILE
    override fun createLexer(project: Project) = SarlLexerAdapter()
    override fun createElement(node: ASTNode) = SarlTypes.Factory.createElement(node)
    override fun getCommentTokens() = COMMENTS
    override fun getWhitespaceTokens() = WHITE_SPACES
}
