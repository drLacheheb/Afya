package com.example.afya

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
import com.example.afya.ui.theme.AfyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AfyaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // TODO 1: Create state variables for text input and items list
    var textValue by remember { mutableStateOf("") }
    val itemsList = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = textValue, // TODO 2: Connect to state
            onTextValueChange = { textValue = it }, // TODO 3: Update text state
            onAddItem = {
                if (it.isNotBlank()) { // TODO 4: Add item to list
                    itemsList.add(it)
                    textValue = "" // Clear input after adding
                }
            },
            onSearch = { query -> // TODO 5: Implement search functionality
                // For now, we will just display all items
                // You can implement filtering logic here if needed
            }
        )

        // TODO 6: Display list of items using CardsList composable
        CardsList(itemsList)
    }
}

/**
 * Composable for search and input controls
 * @param textValue Current value of the input field
 * @param onTextValueChange Callback for text changes
 * @param onAddItem Callback for adding new items
 * @param onSearch Callback for performing search
 */
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
            Button(onClick = { onAddItem(textValue) }) { // TODO 7: Handle add button click
                Text("Add")
            }

            Button(onClick = { onSearch(textValue) }) { // TODO 8: Handle search button click
                Text("Search")
            }
        }
    }
}

/**
 * Composable for displaying a list of items in cards
 * @param displayedItems List of items to display
 */
@Composable
fun CardsList(displayedItems: List<String>) {
    // TODO 9: Implement LazyColumn to display items
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // TODO 10: Create cards for each item in the list
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(text = item, modifier = Modifier.padding(16.dp)) // Display the actual item
            }
        }
    }
}