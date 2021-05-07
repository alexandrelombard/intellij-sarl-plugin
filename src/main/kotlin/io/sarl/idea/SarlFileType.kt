package io.sarl.idea

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object SarlFileType : LanguageFileType(SarlLanguage) {
    override fun getIcon(): Icon = SarlIcons.SARL
    override fun getName() = "SARL File"
    override fun getDefaultExtension() = "sarl"
    override fun getDescription() = "SARL language file"
}
