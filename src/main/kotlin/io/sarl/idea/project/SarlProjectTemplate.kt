package io.sarl.idea.project

import com.intellij.openapi.module.Module
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

abstract class SarlProjectTemplate {
    @Throws(IOException::class)
    abstract fun generateProject(module: Module,
                                 baseDir: VirtualFile): Collection<VirtualFile>

    companion object {
        val DEFAULT: SarlProjectTemplate = object : SarlProjectTemplate() {
            @Throws(IOException::class)
            override fun generateProject(
                module: Module,
                baseDir: VirtualFile): Collection<VirtualFile> {
                val fileList = ArrayList<VirtualFile>()

                // region Create directories
                val mainSarl = File(baseDir.path, "src/main/sarl")
                mainSarl.mkdirs()
                val testSarl = File(baseDir.path, "src/test/sarl")
                testSarl.mkdirs()
                refresh(mainSarl)
                refresh(testSarl)
                // endregion

                // region Create the POM file
                val pom = File(baseDir.path, "pom.xml")
                val success = pom.createNewFile()

                if (success) {
                    javaClass.getResourceAsStream("/module/pom.xml").use { `is` ->
                        FileOutputStream(pom).use { fos ->
                            val buffer = ByteArray(1024)
                            var len: Int = `is`.read(buffer)
                            while (len != -1) {
                                fos.write(buffer, 0, len)
                                len = `is`.read(buffer)
                            }
                        }
                    }

                    fileList.add(LocalFileSystem.getInstance().refreshAndFindFileByIoFile(pom)!!)
                }
                // endregion

                return fileList
            }
        }

        private fun refresh(file: File): VirtualFile? {
            return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file)
        }
    }
}
