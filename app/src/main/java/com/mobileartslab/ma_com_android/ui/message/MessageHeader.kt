package com.mobileartslab.ma_com_android.ui.message

import com.mobileartslab.ma_com_android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageHeader(
  modifier: Modifier = Modifier,
  isToday: Boolean,
  date: Calendar
) {
  val label = if (isToday) {
    stringResource(id = R.string.label_today)
  } else {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy") }
    dateFormat.format(date.time)
  }

  Text(
    modifier =
    modifier
      .background(MaterialTheme.colors.onSurface.copy(alpha = 0.05f))
      .padding(vertical = 4.dp),
    text = label,
    fontSize = 14.sp,
    textAlign = TextAlign.Center
  )
}

@Preview
@Composable
fun Preview_MessageHeader() {
  MaterialTheme {
    MessageHeader(
      isToday = true,
      date = Calendar.getInstance()
    )
  }
}