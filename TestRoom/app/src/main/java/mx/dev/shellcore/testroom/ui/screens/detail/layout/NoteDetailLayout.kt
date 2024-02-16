package mx.dev.shellcore.testroom.ui.screens.detail.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.dev.shellcore.testroom.core.model.NoteBo
import mx.dev.shellcore.testroom.ui.screens.detail.vm.NoteDetailViewModel
import mx.dev.shellcore.testroom.ui.theme.TestRoomTheme

@Preview(showBackground = true)
@Composable
fun NoteDetailLayoutPreview() {
    val note = NoteBo(
        id = 1,
        title = "Title",
        content = "Content"
    )
    TestRoomTheme {
        NoteDetail(note)
    }
}

@Composable
fun NoteDetailLayout(navController: NavController? = null, id: Int = 0) {
    val vm: NoteDetailViewModel = hiltViewModel()
    val note = vm.note.collectAsState().value
    val saved = vm.saved.collectAsState().value

    if (id != 0) {
        LaunchedEffect(key1 = "") {
            vm.getNoteById(id)
        }
    }

    if (saved) {
        LaunchedEffect(key1 = id){
            navController?.popBackStack()
            vm.updateSaved(false)
        }
    }

    NoteDetail(
        note = note,
        updateTitle = {
            vm.updateNoteTitle(it)
        },
        updateContent = {
            vm.updateNoteContent(it)
        },
        onClickSave = {
            vm.saveNote()
        }
    )
}

@Composable
private fun NoteDetail(
    note: NoteBo,
    updateTitle: (String) -> Unit = {},
    updateContent: (String) -> Unit = {},
    onClickSave: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Note Detail",
            )

            Button(
                modifier = Modifier.wrapContentWidth(),
                onClick = onClickSave
            ) {
                Text(text = "Save")
            }
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = note.title,
            onValueChange = {
                updateTitle(it)
            }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(200.dp),
            value = note.content,
            onValueChange = {
                updateContent(it)
            }
        )
    }
}