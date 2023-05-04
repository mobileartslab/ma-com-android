package com.mobileartslab.ma_com_android.ui.message

import com.mobileartslab.ma_com_android.R
import com.mobileartslab.ma_com_android.Tags.TAG_MESSAGE_ACTIONS
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun MessageActions(
  modifier: Modifier = Modifier,
  onDismiss: () -> Unit,
  onUnsendMessage: () -> Unit
) {
  Dialog(
    onDismissRequest = {
      onDismiss()
    }
  ) {
    Box(
      modifier =
      modifier
        .background(MaterialTheme.colors.surface)
        .sizeIn(minWidth = 280.dp, minHeight = 80.dp)
        .testTag(TAG_MESSAGE_ACTIONS),
        contentAlignment = Alignment.Center
      ) {
        TextButton(onClick = { onUnsendMessage() }) {
          Text(text = stringResource(id = R.string.action_unsend_message))
      }
    }
  }
}

@Preview
@Composable
fun Preview_MessageActions() {
  MaterialTheme {
    MessageActions(
      modifier = Modifier.fillMaxSize(),
      onDismiss = { },
      onUnsendMessage =  { }
    )
  }
}