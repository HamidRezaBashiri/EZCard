import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleManager {

    fun setLocale(context: Context, languageTag: String) {
        val locale = Locale.forLanguageTag(languageTag)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.createConfigurationContext(config)
    }
}
