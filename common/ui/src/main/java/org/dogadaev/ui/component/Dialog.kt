package org.dogadaev.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CbtDialog(
    onDismissRequest: () -> Unit,
    onActionRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    titleText: String,
    positiveText: String,
    negativeText: String,
    content: @Composable () -> Unit
) {
    CustomDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                ),
            textAlign = TextAlign.Center,
            text = titleText
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) { content() }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = negativeText.uppercase()
                )
            }
            TextButton(
                onClick = {
                    onActionRequest()
                    onDismissRequest()
                }
            ) {
                Text(
                    text = positiveText.uppercase()
                )
            }
        }
    }
}

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                content = content
            )
        }
    }
}

@Preview
@Composable
fun DialogPreview() {
    CustomDialog(onDismissRequest = { }) {
        Text(text = "Test")
    }
}