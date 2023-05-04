package com.mobileartslab.ma_com_android.ui

import com.mobileartslab.ma_com_android.MessageFactory
import com.mobileartslab.ma_com_android.model.ConversationEvent
import com.mobileartslab.ma_com_android.model.ConversationState
import com.mobileartslab.ma_com_android.ui.input.InputBar
import com.mobileartslab.ma_com_android.ui.message.MessageActions
import com.mobileartslab.ma_com_android.ui.message.Messages
import com.mobileartslab.ma_com_android.ContactFactory
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Conversation(
  modifier: Modifier = Modifier,
  state: ConversationState,
  handleEvent: (event: ConversationEvent) -> Unit
) {
  Column(modifier = modifier) {
    Header(
      modifier = Modifier.fillMaxWidth(),
      onClose = {
        // finish activity
      }
    )
    Messages(
      modifier = Modifier.fillMaxSize().weight(1f),
      messages = state.messages,
      onMessageSelected = { messageId ->
        handleEvent(ConversationEvent.SelectMessage(messageId))
      },
      unSendMessage = { messageId ->
        handleEvent(ConversationEvent.UnsendMessage(messageId))
      }
    )
    InputBar(
      modifier = Modifier.fillMaxWidth(),
      sendMessage = { message ->
        handleEvent(ConversationEvent.SendMessage(message))
      },
      contacts = state.contacts
    )
  }
  if (state.selectedMessage != null) {
    MessageActions(
      onDismiss = {
        handleEvent(ConversationEvent.UnselectMessage)
      },
      onUnsendMessage = {
        handleEvent(ConversationEvent.UnsendMessage(state.selectedMessage.id))
      })
 }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_Conversation() {
  MaterialTheme {
    Conversation(
      modifier = Modifier.fillMaxSize(),
      state = ConversationState(
        messages = MessageFactory.makeMessages(),
        contacts = ContactFactory.makeContacts()
      ),
      handleEvent = { }
    )
  }
}