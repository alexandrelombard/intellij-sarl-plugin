package io.sarl.idea.language

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class SarlReference(element: PsiElement, textRange: TextRange) :
        PsiReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference {

    private val name: String

    init {
        this.name = element.text.substring(textRange.startOffset, textRange.endOffset)
    }

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = myElement.project
        val properties = SarlUtil.findNamedElements(project, name)
        val results = arrayListOf<ResolveResult>()

        results.addAll(properties.map { PsiElementResolveResult(it) })

        return results.toTypedArray()
    }

}