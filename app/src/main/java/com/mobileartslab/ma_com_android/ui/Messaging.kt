package com.mobileartslab.ma_com_android.ui

import com.mobileartslab.ma_com_android.ConversationViewModel
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Messaging() {
  val viewModel: ConversationViewModel = viewModel()
  MaterialTheme {
    Conversation(
      modifier = Modifier.fillMaxSize(),
      state = viewModel.uiState.collectAsState().value,
      handleEvent = viewModel::handleEvent
    )
  }
}