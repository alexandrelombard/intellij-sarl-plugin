package io.sarl.idea.language.structure

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.sarl.idea.SarlIcons
import io.sarl.idea.language.psi.*
import io.sarl.idea.language.psi.impl.SarlClassifierDeclarationImpl
import io.sarl.idea.language.psi.impl.SarlImportStatementImpl
import io.sarl.idea.language.psi.impl.SarlPackageDeclarationImpl

class SarlStructureViewElement(val element: NavigatablePsiElement) :
        StructureViewTreeElement, SortableTreeElement {
    override fun navigate(requestFocus: Boolean) {
        return element.navigate(requestFocus)
    }

    override fun getPresentation(): ItemPresentation {
        return element.presentation ?:
                PresentationData(
                        element.toString(), // TODO
                        "", // TODO
                        SarlIcons.FILE, // TODO
                        TextAttributesKey.createTextAttributesKey(element.toString()))  // TODO
    }

    override fun canNavigate(): Boolean {
        return element.canNavigate()
    }

    override fun getValue(): Any {
        return element
    }

    override fun canNavigateToSource(): Boolean {
        return element.canNavigateToSource()
    }

    override fun getAlphaSortKey(): String {
        return element.name ?: ""
    }

    override fun getChildren(): Array<TreeElement> {
        if(element is SarlFile) {
            // TODO Manage the other children
            val elements = arrayListOf<TreeElement>()

            // Package declaration
            elements.addAll(
                    PsiTreeUtil.getChildrenOfTypeAsList(element, SarlPackageDeclaration::class.java)
                            .map { SarlStructureViewElement(it as SarlPackageDeclarationImpl) })

            // Import declarations
            elements.addAll(
                    PsiTreeUtil.getChildrenOfTypeAsList(element, SarlImportStatement::class.java)
                            .map { SarlStructureViewElement(it as SarlImportStatementImpl) })

            // Type declarations
            elements.addAll(
                    PsiTreeUtil.getChildrenOfTypeAsList(element, SarlClassifierDeclaration::class.java)
                            .map { SarlStructureViewElement(it as SarlClassifierDeclarationImpl) })


            return elements.toTypedArray()
        }

        return emptyArray()
    }
}
