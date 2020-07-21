plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.intellij") version "0.4.21"
    id("org.jetbrains.grammarkit") version "2020.1.2"
}

group = "io.sarl"
version = "0.12.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    sourceSets {
        val main by getting {
            kotlin.srcDir("src/main/kotlin")
        }
    }
}

sourceSets["main"].java.srcDirs(file("src/main/java"), file("src/main/gen"))

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2020.1.2"

    setPlugins("java")
}

grammarKit {
    // version of IntelliJ patched JFlex (see bintray link below), Default is 1.7.0-1
    jflexRelease = "1.7.0-1"

    // tag or short commit hash of Grammar-Kit to use (see link below). Default is 2020.1
    grammarKitRelease = "2020.1"
}

tasks.create("generateSarlLexer", org.jetbrains.grammarkit.tasks.GenerateLexer::class.java) {
    // source flex file
    source = "grammars/_SarlLexer.flex"

    // target directory for lexer
    targetDir = "src/main/gen/io/sarl/idea/language/parser/"

    // target classname, target file will be targetDir/targetClass.java
    targetClass = "_SarlLexer"

    // if set, plugin will remove a lexer output file before generating new one. Default: false
    purgeOldFiles = true
}

tasks.create("generateSarlParser", org.jetbrains.grammarkit.tasks.GenerateParser::class.java) {
    // source bnf file
    source = "grammars/sarl.bnf"

    // optional, task-specific root for the generated files. Default: none
    targetRoot = "src/main/gen"

    // path to a parser file, relative to the targetRoot
    pathToParser = "/io/sarl/idea/language/parser/SarlParserGenerated.java"

    // path to a directory with generated psi files, relative to the targetRoot
    pathToPsiRoot = "/io/sarl/idea/language/psi"

    // if set, plugin will remove a parser output file and psi output directory before generating new ones. Default: false
    purgeOldFiles = true
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      Add change notes here.<br>
      <em>most HTML tags may be used</em>""")
}