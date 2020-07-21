package io.sarl.idea.language.psi.impl

import com.intellij.psi.PsiElement
import io.sarl.idea.language.psi.SarlNamedElement
import io.sarl.idea.language.psi.SarlTypes

class SarlPsiImplUtil {
    companion object {
        @JvmStatic
        fun getName(element: SarlNamedElement): String {
            val identifierNode = element.node.findChildByType(SarlTypes.IDENTIFIER)
            if (identifierNode != null) {
                return identifierNode.text
            }
            return ""
        }

        @JvmStatic
        fun setName(element: SarlNamedElement, name: String): PsiElement {
            val identifierNode = element.node.findChildByType(SarlTypes.IDENTIFIER)
            if (identifierNode != null) {
                // TODO
            }
            return element
        }

        @JvmStatic
        fun getNameIdentifier(element: SarlNamedElement): PsiElement? {
            return element.node.findChildByType(SarlTypes.IDENTIFIER)?.psi
        }
    }
}