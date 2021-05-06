package io.sarl.idea.language.structure

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import io.sarl.idea.language.psi.SarlFile
import io.sarl.idea.language.structure.SarlStructureViewElement

class SarlStructureViewModel(psiFile: PsiFile) :
        StructureViewModelBase(psiFile, SarlStructureViewElement(psiFile)),
        StructureViewModel.ElementInfoProvider {

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return false // TODO
//        return element.value is SarlFile
//        return (element as SarlStructureViewElement).element is SarlFile
//        return element is SarlPackageDeclaration ||
//                element is SarlImportDeclarations ||
//                element is SarlClassDeclaration ||
//                element is SarlInterfaceDeclaration // TODO Add missing ones
    }
}
