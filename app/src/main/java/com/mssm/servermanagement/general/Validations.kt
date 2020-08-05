package com.mssm.servermanagement.general

object Validations {

    fun validateURL(url : String) : Boolean {
        return url.startsWith("https://") || url.startsWith("http://")
    }

    fun validateText(vararg text : String) : Boolean {
        return text.all { it.isNotBlank() }
    }
}