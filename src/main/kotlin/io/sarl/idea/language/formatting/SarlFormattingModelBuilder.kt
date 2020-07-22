package io.sarl.idea.language.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.TokenSet
import io.sarl.idea.SarlLanguage
import io.sarl.idea.language.psi.SarlTypes

class SarlFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
        return FormattingModelProvider
                .createFormattingModelForPsiFile(
                        element.containingFile,
                        SarlBlock(
                                element.node,
                                Wrap.createWrap(WrapType.NONE, false),
                                null,
                                createSpaceBuilder(settings)),
                        settings)
    }

    override fun getRangeAffectingIndent(file: PsiFile, offset: Int, elementAtOffset: ASTNode): TextRange? {
        return null
    }

    companion object {
        private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
            return SpacingBuilder(settings, SarlLanguage)
                    .around(TokenSet.create(
                            SarlTypes.OP_EQ,
                            SarlTypes.OP_EQUAL,
                            SarlTypes.OP_AND,
                            SarlTypes.OP_DIV,
                            SarlTypes.OP_GEQ,
                            SarlTypes.OP_LEQ,
                            SarlTypes.OP_GT,
                            SarlTypes.OP_LT,
                            SarlTypes.OP_MINUS,
                            SarlTypes.OP_MOD,
                            SarlTypes.OP_OR,
                            SarlTypes.OP_NOT_EQUAL,
                            SarlTypes.OP_TIMES))
                    .spaces(1)
                    .after(TokenSet.create(
                            SarlTypes.COLON,
                            SarlTypes.SEMICOLON,
                            SarlTypes.COMMA))
                    .spaces(1)
                    .after(TokenSet.create(SarlTypes.LB))
                    .lineBreakInCode()
                    .before(TokenSet.create(SarlTypes.RB))
                    .none()
        }
    }
}