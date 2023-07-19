package com.example.pizza.pizzaScreen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizza.composables.Pager
import com.example.pizza.ui.theme.PizzaTheme

@Preview(showBackground = true)
@Composable
fun PizzaScreen(viewModel: PizzaViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    PizzaPreview(
        state = state,
        onSizeClick = viewModel::updateBreadSize,
        onToppingClick = viewModel::updateToppingSelection
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaPreview(
    state: PizzaOrderingUiState,
    onSizeClick: (Size, Int) -> Unit,
    onToppingClick: (Int, Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)

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
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$${state.breads[pagerState.currentPage].totalPrice}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentHeight()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                PizzaSizes(
                    size = state.breads[pagerState.currentPage].size,
                    modifier = Modifier.padding(top = 16.dp),
                    onSizeClick = { size -> onSizeClick(size, pagerState.currentPage) },
                )
            }
            Text(
                text = "Customize Your Pizza",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color.Gray
            )
            IngredientsCustomization(
                toppings = state.breads[pagerState.currentPage].toppings,
                onClick = { index -> onToppingClick(index, pagerState.currentPage
                , ) },
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AddToCartButton()
        }
    }
}

@Composable
private fun AddToCartButton() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){

        Button(
            onClick = {  },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
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

@Composable
private fun IngredientsCustomization(
    toppings: List<Topping>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        itemsIndexed(toppings, key = { _, item -> item.id }) { index, topping ->
            PizzaToppingItem(
                image = topping.mainImage,
                isSelected = topping.isSelected,
                onClick = { onClick(index) },
            )
        }
    }
}

@Composable
fun PizzaToppingItem(
    image: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(64.dp)
            .background(
                if (isSelected) Color(0XFFDBF3E2)
                else Color.Transparent, CircleShape
            ),
        onClick = onClick,
    ) {
        Image(
            modifier = Modifier.size(52.dp),
            painter = painterResource(id = image),
            contentDescription = "Topping",
        )
    }
}

@Composable
fun PizzaSizes(
    size: Size,
    modifier: Modifier = Modifier,
    onSizeClick: (Size) -> Unit,
) {
    val position by animateDpAsState(
        targetValue = when (size) {
            Size.SMALL -> 0.dp
            Size.MEDIUM -> 48.dp
            Size.LARGE -> 95.dp
        }, label = "size Animation",
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .matchParentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier
                    .size(48.dp)
                    .offset(x = position, y = 0.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {

            }
        }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
        ) {
            PizzaSize(
                text = "S",
                onClick = { onSizeClick(Size.SMALL) },
            )
            PizzaSize(
                text = "M",
                onClick = { onSizeClick(Size.MEDIUM) },
            )
            PizzaSize(
                text = "L",
                onClick = { onSizeClick(Size.LARGE) },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PlateAndBread(
    state: PizzaOrderingUiState,
    pagerState: PagerState
) {
    Pager(
        bread = state.breads,
        state = pagerState,
        modifier = Modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithIcons() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Pizza",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }

        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Black
                )
            }
        },
        modifier = Modifier.padding(4.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)

    )
}

@Composable
fun PizzaSize(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        modifier = modifier
            .size(48.dp)
            .wrapContentHeight()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        text = text,
        textAlign = TextAlign.Center,
        color = Color.Black,
        style = MaterialTheme.typography.bodyLarge,
    )
}
