package io.sarl.idea.language

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import io.sarl.idea.language.psi.SarlTypes

class SarlBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?, private val spacingBuilder: SpacingBuilder) :
        AbstractBlock(node, wrap, alignment) {

    override fun buildChildren(): MutableList<Block> {
        val blocks = arrayListOf<Block>()
        var child = myNode.firstChildNode
        while (child != null) {
            if(child.elementType != TokenType.WHITE_SPACE) {
                val block = SarlBlock(
                        child,
                        Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(),
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
        return when(myNode.elementType) {
            SarlTypes.AGENT_BODY,
                SarlTypes.CLASS_BODY,
                SarlTypes.INTERFACE_BODY,
                SarlTypes.EVENT_BODY,
                SarlTypes.SKILL_BODY,
                SarlTypes.CAPACITY_BODY,
                SarlTypes.BEHAVIOR_BODY,
                SarlTypes.IF_CONTENT,
                SarlTypes.ELSE_CONTENT,
                SarlTypes.STATEMENT_LIST -> Indent.getNormalIndent()
            else -> Indent.getNoneIndent()
        }

//        return when(myNode.treeParent?.elementType) {
//            null -> Indent.getAbsoluteNoneIndent()
//            SarlTypes.USES_DECLARATION,
//            SarlTypes.MEMBER_DECLARATION,
//            SarlTypes.METHOD_DECLARATION,
//            SarlTypes.ON_DECLARATION -> Indent.getNormalIndent()
//            else -> Indent.getNoneIndent()
//        }


    }

}