package mx.dev.shellcore.testroom.ui.screens.list.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import mx.dev.shellcore.testroom.ui.route.BaseRoute
import mx.dev.shellcore.testroom.ui.screens.list.vm.NoteListViewModel
import mx.dev.shellcore.testroom.ui.theme.TestRoomTheme

@Preview
@Composable
fun NoteItemPreview() {
    val note = NoteBo(
        id = 1,
        title = "Title",
        content = "Content"
    )

    TestRoomTheme {
        NoteItem(note)
    }
}

@Preview
@Composable
fun NoteListLayoutPreview() {
    val list = listOf(
        NoteBo(
            id = 1,
            title = "Title",
            content = "Content"
        ),
        NoteBo(
            id = 2,
            title = "Title 2",
            content = "Content 2"
        ),
        NoteBo(
            id = 3,
            title = "Title 3",
            content = "Content 3"
        )
    )

    TestRoomTheme {
        NoteList(list)
    }
}

@Composable
fun NoteListLayout(navController: NavController? = null) {
    val vm: NoteListViewModel = hiltViewModel()
    val notes = vm.notes.collectAsState().value

    LaunchedEffect(key1 = "") {
        vm.getNotes()
    }

    NoteList(navController = navController, notes = notes)
}

@Composable
fun NoteList(
    notes: List<NoteBo>,
    navController: NavController? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Notes"
            )
            Button(onClick = {
                navController?.navigate(
                    BaseRoute.NoteDetail.route.replace("{id}", "0")
                )
            }) {
                Text(text = "Add")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            notes.forEach { note ->
                NoteItem(
                    modifier = Modifier.fillMaxWidth(),
                    note = note
                ) {
                    navController?.navigate(
                        BaseRoute.NoteDetail.route.replace("{id}", it.toString())
                    )
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    note: NoteBo,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onClick(note.id)
            }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(
                text = note.title ?: "",
            )
        }
    }
}