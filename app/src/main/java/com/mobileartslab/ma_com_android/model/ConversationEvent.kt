package com.mobileartslab.ma_com_android.model

sealed class ConversationEvent {
  class SelectMessage(val id: String) : ConversationEvent()
  object UnselectMessage : ConversationEvent()
  class UnsendMessage(val id: String) : ConversationEvent()
  data class SendMessage(val message: String) : ConversationEvent()
}