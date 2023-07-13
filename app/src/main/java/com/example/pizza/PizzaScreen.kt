package com.example.pizza

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizza.composables.pager
import com.example.pizza.ui.theme.PizzaTheme

@Preview(showBackground = true)
@Composable
fun PizzaScreen(viewModel: PizzaViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    PizzaPreview(state = state)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaPreview(
    state: PizzaUiState,
) {
    val pagerState = rememberPagerState(initialPage = 1)

    PizzaTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBarWithIcons()
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                PlateAndBread(state, pagerState)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "\$17",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentHeight()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Sizes()
            }
            Text(
                text = "Customize Your Pizza",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color.Gray
            )
            IngredientsCustomization()
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(text = "Add to Cart", color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun IngredientsCustomization(
    onclick: () -> Unit = { }
) {
    LazyRow(modifier = Modifier.padding(start = 16.dp)) {
        val list = listOf(
            R.drawable.susage9,
            R.drawable.basil4,
            R.drawable.brocli1,
            R.drawable.mushroom1,
            R.drawable.onion1,

            )
        items(list.size) {
            Box(modifier = Modifier
                .padding(16.dp)
                .clickable { onclick() }) {
                Image(
                    painter = painterResource(id = list[it]),
                    contentDescription = "Plate",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 0.dp, max = 56.dp)
                )
            }
        }
    }
}


@Composable
private fun Sizes(
    onclick: () -> Unit = { }
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(48.dp),
    ) {
        Text(
            text = "S",
            fontWeight = FontWeight.W500,
            fontSize = 24.sp,
            modifier = Modifier
                .clickable { onclick }
        )
        Text(
            text = "M",
            fontWeight = FontWeight.W500,
            fontSize = 24.sp,
            modifier = Modifier
                .clickable { onclick }
        )
        Text(
            text = "L",
            fontWeight = FontWeight.W500,
            fontSize = 24.sp,
            modifier = Modifier
                .clickable { onclick }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PlateAndBread(
    state: PizzaUiState,
    pagerState: PagerState
) {
        pager(
            data = state.BreadList,
            state = pagerState,
            modifier = Modifier
        )
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithIcons() {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Pizza", modifier = Modifier,
                    textAlign = TextAlign.Justify,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,

                    )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Black)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.Black)
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
    )
}