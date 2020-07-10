package io.sarl.idea.language.psi

import com.intellij.psi.tree.IElementType
import io.sarl.idea.SarlLanguage

class SarlTokenType(debugName: String) : IElementType(debugName, SarlLanguage) {
    override fun toString() = "SarlTokenType.${super.toString()}"
}