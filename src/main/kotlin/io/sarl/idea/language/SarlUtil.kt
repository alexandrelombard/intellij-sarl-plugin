package io.sarl.idea.language

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import io.sarl.idea.SarlFileType
import io.sarl.idea.language.psi.SarlNamedElement

object SarlUtil {

    fun findNamedElements(project: Project, name: String): List<SarlNamedElement> {
        val result = arrayListOf<SarlNamedElement>()
        val virtualFiles = FileTypeIndex.getFiles(SarlFileType, GlobalSearchScope.allScope(project))

        for (virtualFile in virtualFiles) {
            val sarlFile = PsiManager.getInstance(project).findFile(virtualFile)
            if(sarlFile != null) {
                val sarlNamedElements = PsiTreeUtil.getChildrenOfType(sarlFile, SarlNamedElement::class.java)
                if(sarlNamedElements != null) {
                    for(sarlNamedElement in sarlNamedElements) {
                        if(sarlNamedElement != null && sarlNamedElement.name == name) {
                            result.add(sarlNamedElement)
                        }
                    }
                }
            }
        }

        return result
    }

    fun findNamedElements(project: Project): List<SarlNamedElement> {
        val result = arrayListOf<SarlNamedElement>()
        val virtualFiles = FileTypeIndex.getFiles(SarlFileType, GlobalSearchScope.allScope(project))

        for (virtualFile in virtualFiles) {
            val sarlFile = PsiManager.getInstance(project).findFile(virtualFile)
            if(sarlFile != null) {
                val sarlNamedElements = PsiTreeUtil.getChildrenOfType(sarlFile, SarlNamedElement::class.java)
                if(sarlNamedElements != null) {
                    result.addAll(sarlNamedElements.filterNotNull())
                }
            }
        }

        return result
    }
}