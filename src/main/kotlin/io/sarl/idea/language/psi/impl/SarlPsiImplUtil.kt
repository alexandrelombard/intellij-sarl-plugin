package io.sarl.idea.language.psi.impl

import com.intellij.psi.PsiElement
import io.sarl.idea.language.psi.*

class SarlPsiImplUtil {
    companion object {
        @JvmStatic fun getName(element: SarlAgentDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlAgentDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlAgentDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlBehaviorDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlBehaviorDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlBehaviorDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlCapacityDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlCapacityDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlCapacityDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlClassDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlClassDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlClassDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlEventDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlEventDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlEventDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlInterfaceDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlInterfaceDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlInterfaceDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlMethodDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlMethodDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlMethodDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlSkillDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlSkillDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlSkillDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlVarDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlVarDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlVarDeclaration) = _getNameIdentifier(element)

        @JvmStatic fun getName(element: SarlValDeclaration) = _getName(element)
        @JvmStatic fun setName(element: SarlValDeclaration, name: String) = _setName(element, name)
        @JvmStatic fun getNameIdentifier(element: SarlValDeclaration) = _getNameIdentifier(element)

        @JvmStatic
        private fun _getName(element: SarlNamedElement): String {
            val identifierNode = element.node.findChildByType(SarlTypes.IDENTIFIER)
            if (identifierNode != null) {
                return identifierNode.text
            }
            return ""
        }

        @JvmStatic
        private fun _setName(element: SarlNamedElement, name: String): PsiElement {
            val identifierNode = element.node.findChildByType(SarlTypes.IDENTIFIER)
            if (identifierNode != null) {
                // TODO
            }
            return element
        }

        @JvmStatic
        private fun _getNameIdentifier(element: SarlNamedElement): PsiElement? {
            return element.node.findChildByType(SarlTypes.IDENTIFIER)?.psi
        }
    }
}