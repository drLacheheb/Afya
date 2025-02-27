package com.example.afya2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TopSection(searchQuery) { searchQuery = it }
            DrugList(searchQuery)
        } } }
@Composable
fun TopSection(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.afya),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth().height(100.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search") },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(40.dp)),
                shape = RoundedCornerShape(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.kk),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } } }
@Composable
fun DrugList(searchQuery: String) {
    val drugs = listOf(
        "Aviptect sirop" to R.drawable.m,
        "Otrivin" to R.drawable.y,
        "Voltaren" to R.drawable.l,
        "Nurofen enfants" to R.drawable.y,
        "Aviptect sirop" to R.drawable.l,
        "Otrivin" to R.drawable.m,
        "Voltaren" to R.drawable.y,
        "Voltaren" to R.drawable.y,
        "Nurofen enfants" to R.drawable.l
    )
    val filteredDrugs = drugs.filter { it.first.contains(searchQuery, ignoreCase = true) }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(filteredDrugs.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.CenterHorizontally)
            ) {
                rowItems.forEach { (name, imageRes) -> DrugItem(imageRes, name) }
            }
            Spacer(modifier = Modifier.height(1.dp))
        } } }
@Composable
fun DrugItem(imageRes: Int, name: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(160.dp)
            .height(150.dp)
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                fontSize = 10.sp,
                color = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.call),
                    contentDescription = "Icon 1",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.c),
                    contentDescription = "Icon 2",
                    modifier = Modifier.size(20.dp)
                ) }}}}
@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        containerColor = Color(0xFF00C853),
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem("Home", R.drawable.h, selectedItem) { selectedItem = it }
            BottomNavItem("Grid", R.drawable.g, selectedItem) { selectedItem = it }
            BottomNavItem("Chat", R.drawable.c, selectedItem) { selectedItem = it }
            BottomNavItem("Profile", R.drawable.p, selectedItem) { selectedItem = it }
        } } }

@Composable
fun BottomNavItem(name: String, iconRes: Int, selectedItem: String, onSelect: (String) -> Unit) {
    val isSelected = selectedItem == name

    IconButton(
        onClick = { onSelect(name) },
        modifier = Modifier
            .clip(CircleShape)
            .background(if (isSelected) Color.LightGray.copy(alpha = 0.4f) else Color.Transparent)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = name,
            tint = if (isSelected) Color.White else Color.Black
        ) } }

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
