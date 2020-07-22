package io.sarl.idea.language.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import io.sarl.idea.language.psi.SarlStatement
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
                myNode.elementType == SarlTypes.CLASS_BODY ||
                myNode.elementType == SarlTypes.STATEMENT_LIST

        if(stackAlignement)
            alignmentStack.push(Alignment.createAlignment())

        while (child != null) {
            if(child.elementType != TokenType.WHITE_SPACE) {
                val block = SarlBlock(
                        child,
                        Wrap.createWrap(WrapType.NONE, false),
                        alignmentStack.peek(), // Alignment.createAlignment(),
                        spacingBuilder)
                blocks.add(block)
            }
            child = child.treeNext
        }

        if(stackAlignement)
            alignmentStack.pop()

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
            SarlTypes.AGENT_BODY,
                SarlTypes.CLASS_BODY,
                SarlTypes.INTERFACE_BODY,
                SarlTypes.EVENT_BODY,
                SarlTypes.SKILL_BODY,
                SarlTypes.CAPACITY_BODY,
                SarlTypes.BEHAVIOR_BODY -> return Indent.getNormalIndent()
        }

        if(myNode.psi is SarlStatement) {
            // Statements are indented relatively to their statement list
            return Indent.getNormalIndent()
        }

        if((myNode.elementType == SarlTypes.LINE_COMMENT ||
                myNode.elementType == SarlTypes.BLOCK_COMMENT) &&
                myNode.treeParent.findChildByType(SarlTypes.STATEMENT_LIST) != null) {
            // Comments next to a statement list should be indented like the statement list
            return Indent.getNormalIndent()
        }

        return Indent.getNoneIndent()
    }

    companion object {
        val alignmentStack = LinkedList<Alignment>()
    }

}