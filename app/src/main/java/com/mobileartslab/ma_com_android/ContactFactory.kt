package com.mobileartslab.ma_com_android

import com.mobileartslab.ma_com_android.model.Contact

object ContactFactory {
    fun makeContacts(): List<Contact> {
      return listOf(
        Contact("Joe"),
        Contact("Ellie"),
        Contact("Anna"),
        Contact("Rachel"),
        Contact("Ross"),
        Contact("Mark"),
        Contact("Jake")
      )
    }
}