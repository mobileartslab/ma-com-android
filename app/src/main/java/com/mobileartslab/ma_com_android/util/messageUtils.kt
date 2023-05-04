package com.mobileartslab.ma_com_android.util

import com.mobileartslab.ma_com_android.model.Message
import java.util.*

fun groupMessagesByDate(
  messages: List<Message>
): Map<Calendar, List<Message>> {
  return messages.sortedByDescending {
    it.dateTime.timeInMillis
  }
  .groupBy {
    it.dateTime.apply {
      set(Calendar.HOUR_OF_DAY, 0)
      set(Calendar.MINUTE, 0)
      set(Calendar.SECOND, 0)
      set(Calendar.MILLISECOND, 0)
    }
  }
}