package io.sarl.idea.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import io.sarl.idea.SarlFileType
import io.sarl.idea.SarlLanguage

class SarlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SarlLanguage) {
    override fun getFileType() = SarlFileType
    override fun toString() = "SARL File"
}