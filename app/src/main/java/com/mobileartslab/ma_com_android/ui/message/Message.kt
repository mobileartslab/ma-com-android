package com.mobileartslab.ma_com_android.ui.message

import com.mobileartslab.ma_com_android.R
import com.mobileartslab.ma_com_android.model.Message
import com.mobileartslab.ma_com_android.model.MessageDirection
import com.mobileartslab.ma_com_android.MessageFactory
import com.mobileartslab.ma_com_android.Tags.TAG_IMAGE
import com.mobileartslab.ma_com_android.Tags.TAG_TEXT
import com.mobileartslab.ma_com_android.util.buildAnnotatedStringWithColors
import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat

@ExperimentalAnimationApi
@Composable
fun Message(
  modifier: Modifier = Modifier,
  message: Message,
  onLongPress: (id: String) -> Unit
) {
  val parentModifier = if (message.direction == MessageDirection.SENT) {
    modifier.padding(end = 16.dp)
  }
  else modifier.padding(start = 16.dp)

  var displaySentTime by remember { mutableStateOf(false) }

  Column(modifier = parentModifier.pointerInput(Unit) {
    detectTapGestures(
      onLongPress = {
        if (message.direction == MessageDirection.SENT) {
          onLongPress(message.id)
        }
      },
      onTap = {
        displaySentTime = !displaySentTime
      })
    }) {
      val alignment = if (message.direction == MessageDirection.SENT) {
        Alignment.End
      }
      else Alignment.Start

      val dateFormat = remember {
        SimpleDateFormat("hh:mm")
      }
      val formattedSentTime = dateFormat.format(message.dateTime.time)

      val messageWithSentTime = stringResource(
        id = R.string.message_with_time,
        formattedSentTime, message.message ?: message.altText ?: ""
      )
      Box(
        modifier =
        Modifier
        .align(alignment = alignment)
        .semantics {
          text = AnnotatedString(messageWithSentTime)
         }
         .background(Color.LightGray, RoundedCornerShape(6.dp)).padding(8.dp)
    ) {
      MessageBody(modifier = Modifier.align(Alignment.Center), message = message)
    }

    AnimatedVisibility(modifier = Modifier.align(alignment = alignment), visible = displaySentTime) {
      Text(
        modifier = Modifier.align(alignment = alignment).padding(top = 4.dp),
        text = formattedSentTime,
        fontSize = 12.sp
      )
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_Message() {
  MaterialTheme {
    Column(modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()) {
      Message(
        modifier = Modifier.fillMaxWidth(),
        message = MessageFactory.makeMessage(text = "Hello there", direction = MessageDirection.SENT),
        onLongPress = { }
      )
      Message(
        modifier = Modifier.fillMaxWidth(),
        message = MessageFactory.makeMessage(text = "Hello there", direction = MessageDirection.RECEIVED),
        onLongPress = { }
      )
    }
  }
}

@Composable
fun MessageBody(
  modifier: Modifier = Modifier,
  message: Message
) {
  if (message.message != null) {
    Text(
      modifier = modifier.testTag(TAG_TEXT),
      text = buildAnnotatedStringWithColors(message.message, MaterialTheme.colors.primary)
    )
  }
  else if (message.image != null) {
    Image(
      modifier = modifier.size(120.dp).testTag(TAG_IMAGE),
      bitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources, message.image
      ).asImageBitmap(),
      contentDescription = message.altText
    )
  }
}

@Preview(showBackground = true)
@Composable
fun Preview_MessageBody_Text() {
  MaterialTheme {
    Box(modifier = Modifier.padding(32.dp)) {
      MessageBody(
        modifier = Modifier.wrapContentSize(),
        message = MessageFactory.makeMessage(text = "Hello there")
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun Preview_MessageBody_Image() {
  MaterialTheme {
    Box(modifier = Modifier.padding(32.dp)) {
      MessageBody(
        modifier = Modifier.wrapContentSize(),
        message = MessageFactory.makeMessage(image = R.drawable.roxy)
      )
    }
  }
}