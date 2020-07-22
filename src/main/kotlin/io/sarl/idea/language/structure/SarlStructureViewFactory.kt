package io.sarl.idea.language.structure

//import org.intellij.grammar.livePreview.LivePreviewElementType
import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ColoredItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.colors.CodeInsightColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.tree.LeafPsiElement
import java.util.*
import javax.swing.Icon

class SarlStructureViewFactory : PsiStructureViewFactory {
//    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder? {
//        return object : TreeBasedStructureViewBuilder() {
//            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
//                return SarlStructureViewModel(psiFile)
//            }
//
//        }
//    }

    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return MyModel(psiFile)
            }

            override fun isRootNodeShown() = false

        }
    }

    private inner class MyModel(psiFile: PsiFile) :
            StructureViewModelBase(psiFile, MyElement(psiFile)), StructureViewModel.ElementInfoProvider {
        init {
            withSuitableClasses(PsiElement::class.java)
        }

        override fun shouldEnterElement(element: Any) = true
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement) = false

        @Override
        override fun isAlwaysLeaf(element: StructureViewTreeElement) =
                element.value is LeafPsiElement

    }

    private inner class MyElement(element: PsiElement) : PsiTreeElementBase<PsiElement>(element), SortableTreeElement, ColoredItemPresentation {
        override fun getChildrenBase(): Collection<StructureViewTreeElement> {
            val element = element
            if (element == null || element is LeafPsiElement) return Collections.emptyList()
            val result = arrayListOf<StructureViewTreeElement>()
            var e = element.firstChild
            while (e != null) {
                if (e !is PsiWhiteSpace) {
                    result.add(MyElement(e))
                }
                e = e.nextSibling
            }
            return result
        }

        override fun getAlphaSortKey(): String {
            return presentableText
        }

        override fun getPresentableText(): String {
            val element = element
            val elementType = (element?.node)?.elementType
            if (element is LeafPsiElement) {
                return "$elementType: '" + element.text + "'"
            } else if (element is PsiErrorElement) {
                return "PsiErrorElement: '" + element.errorDescription + "'"
            } /*else if (elementType is LivePreviewElementType.RuleType) {
                val rule = (elementType).getRule(element.project)
                if (rule != null) {
                    val file = rule.containingFile
                    val className = getRulePsiClassName(rule, getPsiClassFormat(file as BnfFile?))
                    return className + ": '" + StringUtil.first(element.text, 30, true) + "'"
                }
            }*/
            return elementType.toString()
        }

        override fun getLocationString(): String? {
            return null
        }

        override fun getIcon(open: Boolean): Icon? {
            val element = element
            if (element is PsiErrorElement) {
                return null //AllIcons.General.Error;
            } else if (element is LeafPsiElement) {
                return null
            }
            val elementType = (element?.node)?.elementType
//            if (elementType is LivePreviewElementType.RuleType) {
//                return BnfIcons.RULE
//            }
            return null
        }

        override fun getTextAttributesKey(): TextAttributesKey? {
            return if (element is PsiErrorElement) CodeInsightColors.ERRORS_ATTRIBUTES else null
        }
    }
}