package com.example.pizza.composables


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.pizza.pizzaScreen.Bread
import com.example.pizza.R
import com.example.pizza.pizzaScreen.Size

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    bread: List<Bread>,
    modifier: Modifier = Modifier,
    state: PagerState = rememberPagerState(bread.size),
) {
    HorizontalPager(
        pageCount = bread.size,
        state = state,
        modifier = modifier
            .height(250.dp)
            .paint(
                painter = painterResource(id = R.drawable.plate),
                contentScale = ContentScale.Inside
            ),
        verticalAlignment = Alignment.CenterVertically,

        ) {

        val size by animateDpAsState(
            when (bread[it].size) {
                Size.SMALL -> 32.dp
                Size.MEDIUM -> 24.dp
                Size.LARGE -> 16.dp
            }, label = "size Animation",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        Box(
            modifier = Modifier.padding(size)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(bread[it].image)
                        .scale(Scale.FILL)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Bread",

                    )
            bread[it].toppings.forEach { item ->
                ToppingItem(
                    image = item.image,
                    isSelected = item.isSelected,
                    modifier = Modifier
                        .size(180.dp)
                        .padding(size)
                )
            }

        }
    }
}




