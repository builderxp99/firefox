/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.settings.advanced

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import mozilla.components.support.locale.LocaleManager
import org.mozilla.fenix.R
import org.mozilla.fenix.databinding.LocaleSettingsItemBinding
import java.util.Locale

class LocaleViewHolder(
    view: View,
    selectedLocale: Locale,
    private val interactor: LocaleSettingsViewInteractor,
) : BaseLocaleViewHolder(view, selectedLocale) {

    private val binding = LocaleSettingsItemBinding.bind(view)

    override fun bind(locale: Locale) {
        if (locale.toString().equals("vec", ignoreCase = true)) {
            locale.toString()
        }
        if (locale.language == "zh") {
            bindChineseLocale(locale)
        } else {
            // Capitalisation is done using the rules of the appropriate locale (endonym and exonym).
            binding.localeTitleText.text = getDisplayName(locale)
            // Show the given locale using the device locale for the subtitle.
            binding.localeSubtitleText.text = locale.getProperDisplayName()
        }
        binding.localeSelectedIcon.isVisible = isCurrentLocaleSelected(locale, isDefault = false)

        itemView.setOnClickListener {
            interactor.onLocaleSelected(locale)
        }
    }

    private fun bindChineseLocale(locale: Locale) {
        if (locale.country == "CN") {
            binding.localeTitleText.text =
                Locale.forLanguageTag("zh-Hans").getDisplayName(locale).capitalize(locale)
            binding.localeSubtitleText.text =
                Locale.forLanguageTag("zh-Hans").displayName.capitalize(Locale.getDefault())
        } else if (locale.country == "TW") {
            binding.localeTitleText.text =
                Locale.forLanguageTag("zh-Hant").getDisplayName(locale).capitalize(locale)
            binding.localeSubtitleText.text =
                Locale.forLanguageTag("zh-Hant").displayName.capitalize(Locale.getDefault())
        }
    }

    private fun getDisplayName(locale: Locale): String {
        val displayName = locale.getDisplayName(locale).capitalize(locale)
        if (displayName.equals(locale.toString(), ignoreCase = true)) {
            return LOCALE_TO_DISPLAY_NATIVE_NAME_MAP[locale.toString()] ?: displayName
        }
        return displayName
    }

    @SuppressWarnings("LargeClass")
    companion object {
        val LOCALE_TO_DISPLAY_NATIVE_NAME_MAP: Map<String, String> = mapOf(
            "an" to "Aragon??s",
            "anp" to "??????????????????",
            "ar" to "??????????????",
            "ast" to "Asturianu",
            "ay" to "Aimara",
            "az" to "Az??rbaycan dili",
            "be" to "???????????????????? ????????",
            "bg" to "?????????????????? ????????",
            "bn" to "???????????????",
            "br" to "Brezhoneg",
            "bs" to "Bosanski jezik",
            "ca" to "Catal??",
            "cak" to "Kaqchikel",
            "ceb" to "Cebuano",
            "co" to "Corsu",
            "cs" to "??e??tina",
            "cy" to "Cymraeg",
            "da" to "dansk",
            "de" to "Deutsch",
            "dsb" to "dolnoserbski",
            "el" to "????????????????",
            "eo" to "Esperanto",
            "es" to "Espa??ol",
            "et" to "Eesti",
            "eu" to "Euskara",
            "fa" to "??????????",
            "ff" to "Fulfulde",
            "fi" to "Suomi",
            "fr" to "Fran??ais",
            "fy-NL" to "Frisian",
            "ga-IE" to "Gaeilge",
            "gd" to "G??idhlig",
            "gl" to "Galego",
            "gn" to "Ava??e'???",
            "gu-IN" to "?????????????????????",
            "he" to "??????????",
            "hi-IN" to "??????????????????",
            "hil" to "Ilonggo",
            "hr" to "hrvatski jezik",
            "hsb" to "Hornjoserbsce",
            "hu" to "Magyar",
            "hus" to "T??nek",
            "hy-AM" to "??????????????",
            "ia" to "Interlingua",
            "id" to "Bahasa Indonesia",
            "is" to "??slenska",
            "it" to "Italiano",
            "ixl" to "Ixil",
            "ja" to "????????? (????????????)",
            "jv" to "Basa Jawa",
            "ka" to "?????????????????????",
            "kab" to "Taqbaylit",
            "kk" to "?????????? ????????",
            "kmr" to "Kurmanc??",
            "kn" to "???????????????",
            "ko" to "?????????",
            "lij" to "Ligure",
            "lo" to "?????????????????????",
            "lt" to "lietuvi?? kalba",
            "mix" to "Tu'un savi",
            "ml" to "??????????????????",
            "mr" to "???????????????",
            "ms" to "Bahasa Melayu ?????????????",
            "my" to "???????????????",
            "meh" to "Tu??un savi ??uu Yasi'?? Yuku Iti",
            "nb-NO" to "Bokm??l",
            "ne-NP" to "??????????????????",
            "nl" to "Nederlands",
            "nn-NO" to "Nynorsk",
            "nv" to "Din?? bizaad",
            "oc" to "Occitan",
            "pa-IN" to "Panj??b??",
            "pl" to "Polszczyzna",
            "pt" to "Portugu??s",
            "pai" to "Paa ipai",
            "ppl" to "N??huat Pipil",
            "quy" to "Chanka Qhichwa",
            "quc" to "K'iche'",
            "rm" to "Rumantsch Grischun",
            "ro" to "Rom??n??",
            "ru" to "??????????????",
            "sat" to "?????????????????????",
            "sk" to "Slovak",
            "skr" to "??????????????",
            "sl" to "Slovenian",
            "sn" to "ChiShona",
            "sq" to "Shqip",
            "sr" to "???????????? ??????????",
            "su" to "Basa Sunda",
            "sv-SE" to "Svenska",
            "ta" to "???????????????",
            "te" to "??????????????????",
            "tg" to "????????????, to??ik??, ???????????????",
            "th" to "?????????",
            "tl" to "Wikang Tagalog",
            "tok" to "Toki Pona",
            "tr" to "T??rk??e",
            "trs" to "Triqui",
            "tt" to "??????????????",
            "tsz" to "P'urhepecha",
            "uk" to "????????????????????",
            "ur" to "????????",
            "uz" to "O??zbek",
            "vec" to "V??neto",
            "vi" to "Ti???ng Vi???t",
            "wo" to "Wolof",
            "zam" to "D????zt??",
        )

        val LOCALE_TO_DISPLAY_ENGLISH_NAME_MAP: Map<String, String> = mapOf(
            "an" to "Aragonese",
            "ar" to "Arabic",
            "ast" to "Asturianu",
            "az" to "Azerbaijani",
            "be" to "Belarusian",
            "bg" to "Bulgarian",
            "bn" to "Bengali",
            "br" to "Breton",
            "bs" to "Bosnian",
            "ca" to "Catalan",
            "cak" to "Kaqchikel",
            "ceb" to "Cebuano",
            "co" to "Corsican",
            "cs" to "Czech",
            "cy" to "Welsh",
            "da" to "Danish",
            "de" to "German",
            "dsb" to "Sorbian, Lower",
            "el" to "Greek",
            "eo" to "Esperanto",
            "es" to "Spanish",
            "et" to "Estonian",
            "eu" to "Basque",
            "fa" to "Persian",
            "ff" to "Fulah",
            "fi" to "Finnish",
            "fr" to "French",
            "fy-NL" to "Frisian",
            "ga-IE" to "Irish",
            "gd" to "Gaelic",
            "gl" to "Galician",
            "gn" to "Guarani",
            "gu-IN" to "Gujarati",
            "he" to "Hebrew",
            "hi-IN" to "Hindi",
            "hil" to "Hiligaynon",
            "hr" to "Croatian",
            "hsb" to "Sorbian, Upper",
            "hu" to "Hungarian",
            "hy-AM" to "Armenian",
            "id" to "Indonesian",
            "is" to "Icelandic",
            "it" to "Italian",
            "ja" to "Japanese",
            "ka" to "Georgian",
            "kab" to "Kabyle",
            "kk" to "Kazakh",
            "kmr" to "Kurmanji Kurdish",
            "kn" to "Kannada",
            "ko" to "Korean",
            "lij" to "Ligurian",
            "lo" to "Lao",
            "lt" to "Lithuanian",
            "mix" to "Mixtepec Mixtec",
            "ml" to "Malayalam",
            "mr" to "Marathi",
            "ms" to "Malay",
            "my" to "Burmese",
            "nb-NO" to "Norwegian Bokm??l",
            "ne-NP" to "Nepali",
            "nl" to "Dutch, Flemish",
            "nn-NO" to "Norwegian Nynorsk",
            "nv" to "Navajo, Navaho",
            "oc" to "Occitan",
            "pa-IN" to "Punjabi",
            "pl" to "Polish",
            "pt-BR" to "",
            "pt-PT" to "",
            "rm" to "Romansh",
            "ro" to "Rom??n??",
            "ru" to "Russian",
            "sat" to "Santali",
            "sk" to "Slovak",
            "skr" to "Saraiki",
            "sl" to "Slovenian",
            "sq" to "Albanian",
            "sr" to "Serbian",
            "su" to "Sundanese",
            "sv-SE" to "Swedish",
            "ta" to "Tamil",
            "te" to "Telugu",
            "tg" to "Tajik",
            "th" to "Thai",
            "tl" to "Tagalog",
            "tok" to "Toki Pona",
            "tr" to "Turkish",
            "trs" to "Triqui",
            "uk" to "Ukrainian",
            "ur" to "Urdu",
            "uz" to "Uzbek",
            "vec" to "Venitian",
            "vi" to "Vietnamese",
        )
    }
}

class SystemLocaleViewHolder(
    view: View,
    selectedLocale: Locale,
    private val interactor: LocaleSettingsViewInteractor,
) : BaseLocaleViewHolder(view, selectedLocale) {

    private val binding = LocaleSettingsItemBinding.bind(view)

    override fun bind(locale: Locale) {
        binding.localeTitleText.text = itemView.context.getString(R.string.default_locale_text)
        if (locale.script == "Hant") {
            binding.localeSubtitleText.text =
                Locale.forLanguageTag("zh-Hant").displayName.capitalize(Locale.getDefault())
        } else if (locale.script == "Hans") {
            binding.localeSubtitleText.text =
                Locale.forLanguageTag("zh-Hans").displayName.capitalize(Locale.getDefault())
        } else {
            // Use the device locale for the system locale subtitle.
            binding.localeSubtitleText.text = locale.getDisplayName(locale).capitalize(locale)
        }
        binding.localeSelectedIcon.isVisible = isCurrentLocaleSelected(locale, isDefault = true)
        itemView.setOnClickListener {
            interactor.onDefaultLocaleSelected()
        }
    }
}

abstract class BaseLocaleViewHolder(
    view: View,
    private val selectedLocale: Locale,
) : RecyclerView.ViewHolder(view) {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal fun isCurrentLocaleSelected(locale: Locale, isDefault: Boolean): Boolean {
        return if (isDefault) {
            locale == selectedLocale && LocaleManager.isDefaultLocaleSelected(itemView.context)
        } else {
            locale == selectedLocale && !LocaleManager.isDefaultLocaleSelected(itemView.context)
        }
    }

    abstract fun bind(locale: Locale)
}

/**
 * Similar to Kotlin's capitalize with locale parameter, but that method is currently experimental
 */
private fun String.capitalize(locale: Locale): String {
    return substring(0, 1).uppercase(locale) + substring(1)
}

/**
 * Returns the locale in the selected language, with fallback to English name
 */
private fun Locale.getProperDisplayName(): String {
    val displayName = this.displayName.capitalize(Locale.getDefault())
    if (displayName.equals(this.toString(), ignoreCase = true)) {
        return LocaleViewHolder.LOCALE_TO_DISPLAY_ENGLISH_NAME_MAP[this.toString()] ?: displayName
    }
    return displayName
}
