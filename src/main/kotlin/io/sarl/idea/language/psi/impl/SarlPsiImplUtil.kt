package io.sarl.idea.language.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import io.sarl.idea.language.SarlUtil
import io.sarl.idea.language.psi.*

object SarlPsiImplUtil {
    // region References
    @JvmStatic
    fun getReference(element: SarlRefExpr): PsiReference? {
        val refIdentifier = element.validIdentifier ?: return null

        val name = refIdentifier.text
        val namedElements = SarlUtil.findNamedElements(element.project, name)

        if(namedElements.size == 1) {
            return namedElements[0].reference
        }

        return null
    }
    // endregion

    // region Named elements
    @JvmStatic fun getName(element: SarlClassifierDeclaration) = _getName(element)
    @JvmStatic fun setName(element: SarlClassifierDeclaration, name: String) = _setName(element, name)
    @JvmStatic fun getNameIdentifier(element: SarlClassifierDeclaration) = _getNameIdentifier(element)
    // endregion

    @JvmStatic
    private fun _getName(element: SarlClassifierDeclaration): String {
        val identifierNode = element.node.findChildByType(SarlTypes.VALID_IDENTIFIER)
        if (identifierNode != null) {
            return identifierNode.text
        }
        return ""
    }

    @JvmStatic
    private fun _setName(element: SarlClassifierDeclaration, name: String): PsiElement {
        val identifierNode = element.node.findChildByType(SarlTypes.VALID_IDENTIFIER)
        if (identifierNode != null) {
            // TODO
        }
        return element
    }

    @JvmStatic
    private fun _getNameIdentifier(element: SarlClassifierDeclaration): PsiElement? {
        return element.node.findChildByType(SarlTypes.VALID_IDENTIFIER)?.psi
    }
}
