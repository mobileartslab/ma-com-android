package com.mobileartslab.ma_com_android.util

import java.util.Calendar

fun isToday(
  calendar: Calendar
): Boolean {
  val today = Calendar.getInstance()
  return calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
    calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
    calendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
}