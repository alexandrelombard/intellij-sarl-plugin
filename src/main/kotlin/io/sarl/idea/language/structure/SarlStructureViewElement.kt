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
import io.sarl.idea.language.psi.impl.SarlClassDeclarationImpl
import io.sarl.idea.language.psi.impl.SarlImportDeclarationsImpl
import io.sarl.idea.language.psi.impl.SarlInterfaceDeclarationImpl
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
                    PsiTreeUtil.getChildrenOfTypeAsList(element, SarlImportDeclarations::class.java)
                            .map { SarlStructureViewElement(it as SarlImportDeclarationsImpl) })

            // Type declarations // TODO Add missing ones
            elements.addAll(
                    PsiTreeUtil.getChildrenOfTypeAsList(element, SarlClassDeclaration::class.java)
                            .map { SarlStructureViewElement(it as SarlClassDeclarationImpl) })
            elements.addAll(
                    PsiTreeUtil.getChildrenOfTypeAsList(element, SarlInterfaceDeclaration::class.java)
                            .map { SarlStructureViewElement(it as SarlInterfaceDeclarationImpl) })

            return elements.toTypedArray()
        }

        return emptyArray()
    }
}