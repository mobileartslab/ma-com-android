package com.mobileartslab.ma_com_android.model

import com.mobileartslab.ma_com_android.ContactFactory
import com.mobileartslab.ma_com_android.MessageFactory

data class ConversationState(
  val messages: List<Message> = MessageFactory.makeMessages(),
  val contacts: List<Contact> = ContactFactory.makeContacts(),
  val selectedMessage: Message? = null
)