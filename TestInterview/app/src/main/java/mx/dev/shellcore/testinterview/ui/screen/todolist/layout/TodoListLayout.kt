package mx.dev.shellcore.testinterview.ui.screen.todolist.layout

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.dev.shellcore.testinterview.core.model.Todo
import mx.dev.shellcore.testinterview.ui.screen.todolist.vm.TodoListViewModel
import mx.dev.shellcore.testinterview.ui.theme.TestInterviewTheme
import androidx.hilt.navigation.compose.hiltViewModel
import mx.dev.shellcore.testinterview.R
import mx.dev.shellcore.testinterview.ui.route.BaseRoute

@Preview(showBackground = true)
@Composable
private fun TodoListItemPreview() {
    TestInterviewTheme {
        TodoListItem(
            item = Todo(
                id = 1,
                userId = 1,
                title = "Title",
                completed = false
            )
        ) {}
    }

}

@Preview(showBackground = true)
@Composable
private fun TodoListPreview() {
    TestInterviewTheme {
        TodoListLayout()
    }
}

@Composable
fun TodoListLayout(
    navController: NavController? = null
) {
    val vm = hiltViewModel<TodoListViewModel>()
    val list = vm.todoList.collectAsState().value

    LaunchedEffect(key1 = "") {
        vm.fetchTodoList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        list.forEach { item ->
            TodoListItem(
                modifier = Modifier
                    .fillMaxWidth(),
                item = item
            ) {
                navController?.navigate(
                    BaseRoute.TodoDetail.route.replace(
                        "{id}",
                        item.id.toString()
                    )
                )
            }
        }
    }
}

@Composable
private fun TodoListItem(
    modifier: Modifier = Modifier,
    item: Todo,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.weight(4f),
            text = item.title,
        )

        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            fontSize = 10.sp,
            text = if (item.completed)
                stringResource(R.string.todoList_item_state_completed)
            else
                stringResource(R.string.todoList_item_state_pending)
        )
    }
}