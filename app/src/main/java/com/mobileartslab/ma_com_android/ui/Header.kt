package com.mobileartslab.ma_com_android.ui

import com.mobileartslab.ma_com_android.R
import com.mobileartslab.ma_com_android.Tags.TAG_HEADER
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Header(
  modifier: Modifier = Modifier,
  onClose: () -> Unit
) {
  TopAppBar(modifier = modifier.testTag(TAG_HEADER)) {
    IconButton(onClick = {
      onClose()
    }) {
      Icon(
        imageVector = Icons.Default.Close,
        contentDescription = stringResource(id = R.string.cd_close_conversation)
      )
    }
    Text(text = stringResource(id = R.string.title_chat), fontSize = 18.sp)
  }
}

@Preview(showBackground = true)
@Composable
fun Preview_Header() {
  MaterialTheme {
    Header(modifier = Modifier.fillMaxWidth()) {
    }
  }
}