package io.sarl.idea.actions


import com.intellij.ide.IdeBundle
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.actions.CreateFromTemplateAction
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import io.sarl.idea.SarlIcons
import io.sarl.idea.message.SarlBundle
import java.util.*
import javax.swing.Icon

class NewSarlFileAction : CreateFromTemplateAction<PsiFile>("SARL File", null, SarlIcons.FILE) {

    override fun getActionName(directory: PsiDirectory, newName: String, templateName: String): String {
        return "Creating File $newName"
    }

    override fun createFile(className: String, templateName: String, dir: PsiDirectory): PsiFile? {
        val project = dir.project
        val props = Properties(FileTemplateManager.getInstance(project).defaultProperties)
        props.setProperty(FileTemplate.ATTRIBUTE_NAME, className)

        val template = FileTemplateManager.getInstance(project).getInternalTemplate(templateName)

        val element = FileTemplateUtil.createFromTemplate(template, className, props, dir, NewSarlFileAction::class.java.classLoader)

        return element.containingFile
    }

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle(SarlBundle.message("action.create.new.file"))

        for (fileTemplate in SarlFileTemplateUtil.getApplicableTemplates(project)) {
            val templateName = fileTemplate.name
            val shortName = SarlFileTemplateUtil.getTemplateShortName(templateName)
            val icon = getIcon(fileTemplate)
            builder.addKind(shortName, icon, templateName)
        }

        builder.setValidator(object : InputValidatorEx {
            override fun getErrorText(inputString: String): String? {
                return if (inputString.length > 0 && !StringUtil.isJavaIdentifier(inputString)) {
                    SarlBundle.message("error.no_valid_sarl_name")
                } else null
            }

            override fun checkInput(inputString: String): Boolean {
                return true
            }

            override fun canClose(inputString: String): Boolean {
                return !StringUtil.isEmptyOrSpaces(inputString) && getErrorText(inputString) == null
            }
        })
    }

    private fun getIcon(fileTemplate: FileTemplate): Icon {
        if(fileTemplate.name.contains("Agent")) {
            return SarlIcons.AGENT
        }
        if(fileTemplate.name.contains("Behavior")) {
            return SarlIcons.BEHAVIOR
        }
        if(fileTemplate.name.contains("Capacity")) {
            return SarlIcons.CAPACITY
        }
        if(fileTemplate.name.contains("Class")) {
            return SarlIcons.CLASS
        }
        if(fileTemplate.name.contains("Interface")) {
            return SarlIcons.INTERFACE
        }
        if(fileTemplate.name.contains("Event")) {
            return SarlIcons.EVENT
        }
        if(fileTemplate.name.contains("Skill")) {
            return SarlIcons.SKILL
        }

        return SarlIcons.FILE
    }
}
