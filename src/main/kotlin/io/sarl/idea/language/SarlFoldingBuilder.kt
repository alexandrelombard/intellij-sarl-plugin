package io.sarl.idea.language

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.sarl.idea.language.psi.*
import java.util.*


class SarlFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun getPlaceholderText(node: ASTNode): String? {
        return "{...}"
    }

    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        // Initialize the list of folding regions
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        // Get a collection of the blocks in the document below root
        val blocks =
                PsiTreeUtil.findChildrenOfAnyType(
                        root,
                        SarlClassifierDeclaration::class.java,
                        SarlMethodDeclaration::class.java,
                        SarlOnDeclaration::class.java,
                        SarlExpressionBlock::class.java)
        // Evaluate the collection
        for (block in blocks) {
            descriptors.add(FoldingDescriptor(
                    block.node,
                    TextRange(
                            block.textRange.startOffset + block.text.indexOf("{"),
                            block.textRange.endOffset),
                    FoldingGroup.newGroup(if(block is SarlIdentifiable) block.getIdentifier().text else block.text)))
        }
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return false
    }

}
