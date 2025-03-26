
package com.example.afya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.afya.ui.theme.AfyaTheme
import com.example.afya.view.MainScreen
import com.example.afya.viewmodel.DrugViewModel
import com.example.afya.viewmodel.PostViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AfyaTheme {
                Box {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        val postViewModel = viewModel<PostViewModel>()
                        val drugViewModel = viewModel<DrugViewModel>()

                        MainScreen(
                            postViewModel = postViewModel,
                            drugViewModel = drugViewModel,
                            modifier = Modifier.padding(innerPadding),
                            )
                    }
                }
            }
        }
    }
}



/*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.afya.ui.theme.AfyaTheme
import com.example.afya.presentation.view.MainScreen
import com.example.afya.presentation.viewmodel.DrugViewModel
import com.example.afya.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
=======
import com.example.afya.ui.theme.AfyaTheme
>>>>>>> 3c8320f66b07010eabaaa9da1b8ac4d702af8f9f


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AfyaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
<<<<<<< HEAD
                    val postViewModel = hiltViewModel<PostViewModel>()
                    val drugViewModel = hiltViewModel<DrugViewModel>()

                    MainScreen(
                        postViewModel,
                        drugViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
=======
                    FirstUI(modifier = Modifier.padding(innerPadding))
>>>>>>> 3c8320f66b07010eabaaa9da1b8ac4d702af8f9f
                }
            }
        }
    }
}

@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // State variables for text input and items list
    var textValue by remember { mutableStateOf("") }
    val itemsList = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = textValue,
            onTextValueChange = { newValue -> textValue = newValue },
            onAddItem = {
                if (textValue.isNotBlank()) {
                    itemsList.add(textValue)
                    textValue = ""
                }
            },
            onSearch = { query ->
                searchQuery = query
            }
        )

        // Display filtered list of items using CardsList composable
        val filteredList = if (searchQuery.isBlank()) {
            itemsList
        } else {
            itemsList.filter { it.contains(searchQuery, ignoreCase = true) }
        }
        CardsList(filteredList)
    }
}

@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Column {
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter text...") }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onAddItem(textValue) }) {
                Text("Add")
            }

            Button(onClick = { onSearch(textValue) }) {
                Text("Search")
            }
        }
    }
}

@Composable
fun CardsList(items: List<String>) {
    LazyColumn {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AfyaTheme {
        FirstUI()
    }
}


 */
