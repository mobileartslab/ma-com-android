package com.mobileartslab.ma_com_android

import com.mobileartslab.ma_com_android.ui.Messaging
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mobileartslab.ma_com_android.ui.theme.ComposeByExampleTheme
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi

@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MessagingActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Messaging()
    }
  }
}
