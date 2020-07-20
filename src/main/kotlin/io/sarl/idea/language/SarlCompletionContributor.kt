package io.sarl.idea.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import io.sarl.idea.language.psi.SarlTypes

class SarlCompletionContributor : CompletionContributor() {

    init {
        // FIXME Not working
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SarlTypes.IMPORT_DECLARATIONS),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters,
                                       context: ProcessingContext,
                                       resultSet: CompletionResultSet) {
                        resultSet.addElement(LookupElementBuilder.create("import"))
                    }
                }
        )
    }

}