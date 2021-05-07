package io.sarl.idea.project

import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager
import io.sarl.idea.SarlIcons
import javax.swing.Icon

class SarlModuleType : ModuleType<SarlModuleBuilder>(ID) {
    override fun createModuleBuilder(): SarlModuleBuilder {
        return SarlModuleBuilder.INSTANCE
    }

    override fun getName(): String {
        return "SARL"
    }

    override fun getDescription(): String {
        return "SARL module"
    }

    override fun getNodeIcon(isOpened: Boolean): Icon {
        return SarlIcons.SARL
    }

    companion object {
        private const val ID = "SARL_MODULE"
        val INSTANCE: SarlModuleType by lazy { ModuleTypeManager.getInstance().findByID(ID) as SarlModuleType }
    }
}
