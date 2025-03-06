package com.example.afya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    val filteredItems = allItems.filter { it.contains(searchQuery, ignoreCase = true) }

    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        SearchInputBar(
            textValue = textValue,
            onTextValueChange = { textValue = it },
            onAddItem = {
                if (it.isNotBlank()) {
                    allItems.add(it)
                    textValue = ""
                }
            },
            onSearch = { searchQuery = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CardsList(displayedItems = filteredItems)
    }
}

@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = textValue,
            onValueChange = {
                onTextValueChange(it)
                onSearch(it) // البحث مباشرة دون الحاجة للضغط على زر
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter text...") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onAddItem(textValue) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B))
            ) {
                Text("Add", color = Color.White)
            }
            Button(
                onClick = { onSearch(textValue) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD84315))
            ) {
                Text("Search", color = Color.White)
            }
        }
    }
}

@Composable
fun CardsList(displayedItems: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF37474F)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstUIPreview() {
    AfyaTheme {
        FirstUI()
    }
}