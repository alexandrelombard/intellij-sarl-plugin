package io.sarl.idea.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.sarl.idea.language.psi.SarlNamedElement

abstract class SarlNamedElementImpl(node: ASTNode) :
        ASTWrapperPsiElement(node), SarlNamedElement