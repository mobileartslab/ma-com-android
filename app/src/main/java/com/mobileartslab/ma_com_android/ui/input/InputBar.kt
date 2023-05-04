package com.mobileartslab.ma_com_android.ui.input

import com.mobileartslab.ma_com_android.R
import com.mobileartslab.ma_com_android.model.Contact
import com.mobileartslab.ma_com_android.ContactFactory
import com.mobileartslab.ma_com_android.Tags.TAG_INPUT_BAR
import com.mobileartslab.ma_com_android.Tags.TAG_MENTIONS
import com.mobileartslab.ma_com_android.Tags.TAG_MESSAGE_INPUT
import com.mobileartslab.ma_com_android.Tags.TAG_SEND_MESSAGE
import com.mobileartslab.ma_com_android.util.replaceText
import com.mobileartslab.ma_com_android.util.buildAnnotatedStringWithColors
import com.mobileartslab.ma_com_android.util.inputShouldTriggerSuggestions
import com.mobileartslab.ma_com_android.util.selectedWord
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputBar(
  modifier: Modifier = Modifier,
  sendMessage: (message: String) -> Unit,
  contacts: List<Contact>
) {
  Column(modifier = modifier.testTag(TAG_INPUT_BAR)) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
      val showMentions by remember {
        derivedStateOf {
          textState.text.isNotEmpty() && inputShouldTriggerSuggestions(
            contacts,
            selectedWord(textState)
          )
        }
      }
      if (showMentions) {
        Mentions(
          modifier = Modifier.fillMaxWidth().testTag(TAG_MENTIONS),
          contacts = contacts,
          query = selectedWord(textState)
        ) { query, result ->
            val startIndex = textState.text.indexOf(query)
            textState = textState.replaceText(startIndex, startIndex + query.length, result)
          }
        }
        Divider()
        TextField(
          modifier = Modifier.fillMaxWidth().testTag(TAG_MESSAGE_INPUT),
          visualTransformation = MentionHighlightTransformation(MaterialTheme.colors.primary),
          value = textState,
          placeholder = {
            Text(text = stringResource(id = R.string.hint_send_message))
          },
          colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
          onValueChange = {
            textState = it
          },
          trailingIcon = {
            IconButton(
              modifier = Modifier.testTag(TAG_SEND_MESSAGE),
              onClick = {
                sendMessage(textState.text)
                textState = TextFieldValue()
              },
              enabled = textState.text.isNotEmpty()
            ) {
              Icon(
                imageVector = Icons.Default.Send,
                contentDescription = stringResource(id = R.string.cd_send_message),
                tint =
                if (textState.text.isNotEmpty()) {
                  MaterialTheme.colors.primary
                }
                else {
                  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                }
              )
            }
          }
        )
    }
}

class MentionHighlightTransformation(private val color: Color) : VisualTransformation {
  override fun filter(text: AnnotatedString): TransformedText {
    return TransformedText(
      buildAnnotatedStringWithColors(text.toString(), color),
      OffsetMapping.Identity
    )
  }
}

@Preview
@Composable
fun Preview_InputBar() {
  MaterialTheme {
    InputBar(
      modifier = Modifier.fillMaxWidth(),
      sendMessage = { },
      contacts = ContactFactory.makeContacts()
    )
  }
}