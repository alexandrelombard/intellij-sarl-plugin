package io.sarl.idea.language

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parentOfType
import io.sarl.idea.language.psi.SarlOnDeclaration
import io.sarl.idea.language.psi.SarlTypes

class SarlAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        checkOccurrenceInOn(element, holder)

        return
    }

    private fun checkOccurrenceInOn(element: PsiElement, holder: AnnotationHolder) {
        if(element.elementType == SarlTypes.OCCURRENCE) {
            val onDeclaration = PsiTreeUtil.getParentOfType(element, SarlOnDeclaration::class.java, false)
            if(onDeclaration == null) {
                val annotation = holder.newAnnotation(HighlightSeverity.ERROR, "occurrence can only be used inside an event handler")
                annotation.create()
            }
        }
    }
}