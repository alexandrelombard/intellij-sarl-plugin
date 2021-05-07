package io.sarl.idea.message

import com.intellij.DynamicBundle
import org.jetbrains.annotations.PropertyKey

const val BUNDLE: String = "messages.SarlBundle"

object SarlBundle : DynamicBundle(BUNDLE) {
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String {
        return getMessage(key, *params)
    }
}
