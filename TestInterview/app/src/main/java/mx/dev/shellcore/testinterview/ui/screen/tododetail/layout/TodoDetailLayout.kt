package mx.dev.shellcore.testinterview.ui.screen.tododetail.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mx.dev.shellcore.testinterview.R
import mx.dev.shellcore.testinterview.ui.screen.tododetail.vm.TodoDetailViewModel
import mx.dev.shellcore.testinterview.ui.theme.TestInterviewTheme

@Preview(showBackground = true)
@Composable
private fun TodoDetailLayoutPreview() {
    TestInterviewTheme {
        TodoDetailLayout()
    }
}

@Composable
fun TodoDetailLayout(
    id: Int = 0
) {
    val vm = hiltViewModel<TodoDetailViewModel>()
    val todo = vm.todo.collectAsState().value

    LaunchedEffect(key1 = "", block = {
        vm.getTodoDetailById(id)
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.todoDetail_title)
        )

        todo?.let { todo ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.todoDetail_description, todo.title)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(stringResource(R.string.todoDetail_complete_swtich))
                Switch(checked = todo.completed, onCheckedChange = {
                    todo.completed = it
                })
            }
        }
    }
}