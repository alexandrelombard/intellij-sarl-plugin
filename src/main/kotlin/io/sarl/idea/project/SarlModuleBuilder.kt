package io.sarl.idea.project

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException
import java.util.logging.Logger

class SarlModuleBuilder : ModuleBuilder() {

    @Throws(ConfigurationException::class)
    override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {
        // region Initialize SDK
        if (myJdk != null) {
            modifiableRootModel.sdk = myJdk
        } else {
            modifiableRootModel.inheritSdk()
        }
        // endregion

        // region Initialize the project structure
        val contentEntry = doAddContentEntry(modifiableRootModel)
        if (contentEntry != null) {
            val baseDir = contentEntry.file
            if (baseDir != null) {
                val filesToOpen: Collection<VirtualFile>
                try {
                    filesToOpen = SarlProjectTemplate.DEFAULT.generateProject(modifiableRootModel.module, baseDir)

                    if (!filesToOpen.isEmpty()) {
                        val manager = FileEditorManager.getInstance(modifiableRootModel.project)
                        for (file in filesToOpen) {
                            manager.openFile(file, true)
                        }
                    }
                } catch (e: IOException) {
                    Logger.getLogger(name)
                        .severe("Unable to generate the SARL template project. Cause: " + e.message)
                }

            }

            contentEntry.addSourceFolder("src/main/sarl", false)
            contentEntry.addSourceFolder("src/test/sarl", true)
        }
        // endregion


        // region Add maven framework
        // FIXME
//        val frameworkSupportProviderList = FrameworkSupportUtil.getAllProviders()
        // endregion
    }

    override fun getModuleType(): ModuleType<*> {
        return SarlModuleType.INSTANCE
    }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider): Array<ModuleWizardStep> {
        return super.createWizardSteps(wizardContext, modulesProvider)
    }

    companion object {
        val INSTANCE = SarlModuleBuilder()
    }
}
