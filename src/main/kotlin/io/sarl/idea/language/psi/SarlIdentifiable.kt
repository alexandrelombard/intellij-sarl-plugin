package io.sarl.idea.language.psi

import com.intellij.psi.PsiElement

interface SarlIdentifiable : PsiElement {
    fun getIdentifier(): SarlIdentifier
}