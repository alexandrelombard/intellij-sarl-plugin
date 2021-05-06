package io.sarl.idea.language.structure

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.navigation.ColoredItemPresentation
import com.intellij.openapi.editor.colors.CodeInsightColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.tree.LeafPsiElement
import java.util.*
import javax.swing.Icon

class SarlStructureViewElement(element: PsiElement) :
    PsiTreeElementBase<PsiElement>(element), SortableTreeElement, ColoredItemPresentation {
    override fun getChildrenBase(): Collection<StructureViewTreeElement> {
        val element = element
        if (element == null || element is LeafPsiElement) return Collections.emptyList()
        val result = arrayListOf<StructureViewTreeElement>()
        var e = element.firstChild
        while (e != null) {
            if (e !is PsiWhiteSpace) {
                result.add(SarlStructureViewElement(e))
            }
            e = e.nextSibling
        }
        return result
    }

    override fun getAlphaSortKey(): String {
        return presentableText
    }

    override fun getPresentableText(): String {
        val element = element
        val elementType = (element?.node)?.elementType
        if (element is LeafPsiElement) {
            return "$elementType: '" + element.text + "'"
        } else if (element is PsiErrorElement) {
            return "PsiErrorElement: '" + element.errorDescription + "'"
        }
        return elementType.toString()
    }

    override fun getLocationString(): String? {
        return null
    }

    override fun getIcon(open: Boolean): Icon? {
        val element = element
        if (element is PsiErrorElement) {
            return null //AllIcons.General.Error;
        } else if (element is LeafPsiElement) {
            return null
        }
        val elementType = (element?.node)?.elementType
        return null
    }

    override fun getTextAttributesKey(): TextAttributesKey? {
        return if (element is PsiErrorElement) CodeInsightColors.ERRORS_ATTRIBUTES else null
    }
}
