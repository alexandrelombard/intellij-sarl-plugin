package io.sarl.idea.language.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.FormatterUtil
import com.intellij.psi.formatter.common.AbstractBlock
import io.sarl.idea.language.psi.SarlExpressionBlock
import io.sarl.idea.language.psi.SarlTypes
import org.apache.commons.collections.ArrayStack
import java.util.*

class SarlBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?, private val spacingBuilder: SpacingBuilder) :
        AbstractBlock(node, wrap, alignment) {

    override fun buildChildren(): MutableList<Block> {
        val blocks = arrayListOf<Block>()
        var child = myNode.firstChildNode

        // FIXME This stack alignment thing isn't really working
        val stackAlignement =
                myNode.elementType == SarlTypes.CLASSIFIER_BODY ||
                myNode.elementType == SarlTypes.EXPRESSION_BLOCK

        while (child != null) {
            if(!FormatterUtil.containsWhiteSpacesOnly(child)) {
                val block = SarlBlock(
                        child,
                        Wrap.createWrap(WrapType.NONE, false),
                        null,   // Alignment.createAlignment(),
                        spacingBuilder)
                blocks.add(block)
            }
            child = child.treeNext
        }

        return blocks
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode === null
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getIndent(): Indent {
        // Basic cases
        when(myNode.elementType) {
            SarlTypes.CLASSIFIER_BODY -> return Indent.getNormalIndent()
        }

        if(myNode.psi is SarlExpressionBlock) {
            // Statements are indented relatively to their statement list
            return Indent.getNormalIndent()
        }

        if((myNode.elementType == SarlTypes.LINE_COMMENT ||
                myNode.elementType == SarlTypes.BLOCK_COMMENT) &&
                myNode.treeParent.findChildByType(SarlTypes.EXPRESSION_BLOCK) != null) {
            // Comments next to a statement list should be indented like the statement list
            return Indent.getNormalIndent()
        }

        return Indent.getNoneIndent()
    }
}
