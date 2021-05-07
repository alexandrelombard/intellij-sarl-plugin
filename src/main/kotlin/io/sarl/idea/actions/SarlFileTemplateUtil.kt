package io.sarl.idea.actions

import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Condition
import com.intellij.util.SmartList
import com.intellij.util.containers.ContainerUtil
import io.sarl.idea.SarlFileType

object SarlFileTemplateUtil {
    private val SARL_TEMPLATE_PREFIX = "Sarl"

    @JvmOverloads
    fun getApplicableTemplates(
        project: Project,
        filter: Condition<FileTemplate> = Condition
        { fileTemplate -> SarlFileType.defaultExtension == fileTemplate.extension }): List<FileTemplate> {
        val applicableTemplates = SmartList<FileTemplate>()
        applicableTemplates.addAll(ContainerUtil.findAll(FileTemplateManager.getInstance(project).internalTemplates, filter))
        applicableTemplates.addAll(ContainerUtil.findAll(FileTemplateManager.getInstance(project).allTemplates, filter))
        return applicableTemplates
    }

    fun getTemplateShortName(templateName: String): String {
        return if (templateName.startsWith(SARL_TEMPLATE_PREFIX)) {
            templateName.substring(SARL_TEMPLATE_PREFIX.length)
        } else templateName
    }
}
