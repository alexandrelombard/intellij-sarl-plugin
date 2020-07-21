package io.sarl.idea.language

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class SarlReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
        object : PsiReferenceProvider() {
            override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
                val literalExpression = element as PsiLiteralExpression
                val literalExpresssionValue = literalExpression.value
                val value: String? = if(literalExpresssionValue is String) literalExpresssionValue else null
                // TODO
//                if(value != null && value.startsWith()) {
//                    val name = TextRange()
//                    return Array<PsiReference>(SarlReference(element, name))
//                }

                return PsiReference.EMPTY_ARRAY
            }

        })
    }

}