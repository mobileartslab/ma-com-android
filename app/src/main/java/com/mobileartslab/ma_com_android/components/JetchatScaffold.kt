package com.mobileartslab.ma_com_android.components

import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.runtime.Composable
import com.mobileartslab.ma_com_android.ui.theme.JetchatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetchatDrawer(
  drawerState: DrawerState = rememberDrawerState(initialValue = Closed),
  onProfileClicked: (String) -> Unit,
  onChatClicked: (String) -> Unit,
  content: @Composable () -> Unit
) {
  JetchatTheme {
    ModalNavigationDrawer(
      drawerState = drawerState,
      drawerContent = {
        ModalDrawerSheet {
          JetchatDrawerContent(
            onProfileClicked = onProfileClicked,
            onChatClicked = onChatClicked
          )
        }
      },
      content = content
    )
  }
}
