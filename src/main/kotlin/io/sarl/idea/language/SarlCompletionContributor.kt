package io.sarl.idea.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import io.sarl.idea.SarlLanguage
import io.sarl.idea.language.psi.SarlElementType
import io.sarl.idea.language.psi.SarlTypes

class SarlCompletionContributor : CompletionContributor() {

    init {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().inside(false, PlatformPatterns.psiElement(SarlTypes.PACKAGE_DECLARATION)),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters,
                                                context: ProcessingContext,
                                                resultSet: CompletionResultSet) {
                        // TODO Suggest package name (according to directory name)
                        println("package")
                        resultSet.addElement(LookupElementBuilder.create(parameters.originalFile.containingDirectory))
                    }
                }
        )

        extend(CompletionType.BASIC, PlatformPatterns.psiElement().inside(false, PlatformPatterns.psiElement(SarlTypes.IMPORT_DECLARATION)),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters,
                                                context: ProcessingContext,
                                                resultSet: CompletionResultSet) {
                        // TODO Suggest imports
                        println("import")
                    }
                }
        )

        // FIXME
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().inside(false, PlatformPatterns.psiElement(SarlTypes.CLASS_DECLARATION)),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters,
                                                context: ProcessingContext,
                                                resultSet: CompletionResultSet) {
                        resultSet.addElement(LookupElementBuilder.create(parameters.originalFile.name))
                    }
                }
        )

        // Field access
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().inside(false, PlatformPatterns.psiElement(SarlTypes.FIELD_ACCESS_EXPR)),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters,
                                                context: ProcessingContext,
                                                resultSet: CompletionResultSet) {
                        // TODO
                        println("Field access")
                    }
                }
        )

        // FIXME Not working
        extend(CompletionType.SMART, PlatformPatterns.psiElement().withSuperParent(10, PlatformPatterns.psiElement(SarlTypes.CLASS_BODY)),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters,
                                                context: ProcessingContext,
                                                resultSet: CompletionResultSet) {
                        resultSet.addElement(LookupElementBuilder.create("def"))
                        resultSet.addElement(LookupElementBuilder.create("new"))
                        resultSet.addElement(LookupElementBuilder.create("val"))
                        resultSet.addElement(LookupElementBuilder.create("var"))
                        println("class body")
                    }
                }
        )
    }

}