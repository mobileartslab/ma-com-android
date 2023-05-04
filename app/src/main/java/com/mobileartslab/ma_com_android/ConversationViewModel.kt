package com.mobileartslab.ma_com_android

import com.mobileartslab.ma_com_android.model.ConversationEvent
import com.mobileartslab.ma_com_android.model.ConversationState
import com.mobileartslab.ma_com_android.model.Message
import com.mobileartslab.ma_com_android.model.MessageDirection
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

class ConversationViewModel : ViewModel() {

  val uiState = MutableStateFlow(ConversationState())

  fun handleEvent(authenticationEvent: ConversationEvent) {
    when (authenticationEvent) {
      is ConversationEvent.SendMessage -> {
        uiState.value = uiState.value.copy(
          messages = uiState.value.messages.toMutableList().apply {
          add(
            Message(
              uiState.value.messages.count().toString(),
              MessageDirection.SENT, Calendar.getInstance(), "me",
              authenticationEvent.message
            )
          )
        }.toList()
      )
   }
   is ConversationEvent.UnsendMessage -> {
     uiState.value = uiState.value.copy(
       messages = uiState.value.messages.toMutableList().apply {
         removeAt(uiState.value.messages.indexOfFirst {
         it.id == authenticationEvent.id
       })
     }.toList(),
     selectedMessage = null
   )
   }

   is ConversationEvent.SelectMessage -> {
     uiState.value = uiState.value.copy(
       selectedMessage = uiState.value.messages.find { it.id == authenticationEvent.id }
     )
   }

   ConversationEvent.UnselectMessage -> {
     uiState.value = uiState.value.copy(selectedMessage = null)
   }
   }
   }
}


