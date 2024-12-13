package com.softmintindia.pgsdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.softmintindia.pgsdk.ui.theme.PGSDKTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PGSDKTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { AppBar() }
                ) { innerPadding ->
                    MainContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = "PGSDK",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF3F51B5), // AppBar background color
            titleContentColor = Color.White    // Title text color
        )
    )
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hello, welcome to our SDK!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Add action here */ },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Get Started",
                fontSize = 16.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Adding ExpansionTile
        ExpansionTile(
            title = "Learn More",
            content = {
                Text(
                    text = "This SDK helps you integrate payment gateway features easily into your application. Follow the documentation to get started quickly.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Created by Ravi Kant",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ExpansionTile(title: String, content: @Composable () -> Unit) {
    val expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = !expanded.value },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xFF3F51B5)
            )
        }
        if (expanded.value) {
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    PGSDKTheme {
        Scaffold(
            topBar = { AppBar() }
        ) {
            MainContent(Modifier.padding(it))
        }
    }
}
