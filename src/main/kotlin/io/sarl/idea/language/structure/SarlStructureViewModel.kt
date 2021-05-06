package io.sarl.idea.language.structure

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile

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
    }
}
