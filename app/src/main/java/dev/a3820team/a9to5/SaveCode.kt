package dev.a3820team.a9to5
import android.content.Context
import dev.a3820team.a9to5.MeQuestionsActivity.ME_PREFS_PREFIX
import dev.a3820team.a9to5.MeQuestionsActivity.mQuestionAmount
import dev.a3820team.a9to5.OrgQuestionsActivity.ORG_PREFS_PREFIX

class SaveCode {
    val ME_PREFIX  = ME_PREFS_PREFIX
    val ORG_PREFIX = ORG_PREFS_PREFIX
    val QUESTION_AMOUNT = mQuestionAmount
    val CHARACTERS = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', '+', '=', '!', '@',
        '#', '$', '%', '^', '&', '*', '(', ')', '<', '>', ';', '/', '?', '|')

    fun isValidCode(code: String ): Boolean {
        // Returns whether a code is valid or not
        return true
    }

    fun generateCode(context: Context): String {
        // Returns a string, containing sharedPreference values in encoded form
        return ""
    }

    fun loadCode(context: Context, code: String): Boolean {
        // Attempts to lead the code into sharedPreference values
        // Returns whether the loading operation was successful
        return false
    }
}