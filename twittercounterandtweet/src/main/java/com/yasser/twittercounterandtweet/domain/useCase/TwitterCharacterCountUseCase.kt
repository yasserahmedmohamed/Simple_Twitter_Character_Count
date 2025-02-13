package com.yasser.twittercounterandtweet.domain.useCase

import java.util.regex.Pattern
import javax.inject.Inject

class TwitterCharacterCountUseCase @Inject constructor() {
    operator fun invoke(text: String): Int {
        val urlPattern = Pattern.compile(
            "(https?://)?(www\\.)?[a-zA-Z0-9\\-]+(\\.[a-zA-Z]{2,})+(/[a-zA-Z0-9#\\-_/?.=]*)?",
            Pattern.CASE_INSENSITIVE
        )

        var count = 0
        var remainingText = text
        val matcher = urlPattern.matcher(text)

        while (matcher.find()) {
            val url = matcher.group() ?: continue
            count += 23 // Twitter counts all URLs as 23 characters
            remainingText = remainingText.replace(url, "") // Remove counted URLs from text
        }

        for (char in remainingText) {
            count += if (char.isEmoji() || char.isCJK()) 2 else 1
        }

        return count
    }


    private fun Char.isEmoji(): Boolean {
        val type = Character.getType(this)
        return type == Character.SURROGATE.toInt() || type == Character.OTHER_SYMBOL.toInt()
    }


    fun Char.isCJK(): Boolean {
        val unicodeBlock = Character.UnicodeBlock.of(this)
        return unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
                unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
                unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B ||
                unicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                unicodeBlock == Character.UnicodeBlock.HIRAGANA ||
                unicodeBlock == Character.UnicodeBlock.KATAKANA ||
                unicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES
    }
}