# Project structure

- *grammars*: contains the Grammar-Kit BNF definition of the language
- *src/main/resources/META-INF/plugin.xml*: contains the plugin definition and references to required classes

- *src/main/kotlin*: main package
    - *src/main/kotlin/language/formatting*: code formatting
    - *src/main/kotlin/language/highlighter*: syntax highlighter
    - *src/main/kotlin/language/psi*: PSI elements related definitions (language structure)
    - *src/main/kotlin/language/reference*: reference contributor (for auto-complete)
    - *src/main/kotlin/language/structure*: structure view (for displaying the AST)
