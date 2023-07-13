package com.example.pizza.composables


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pizza.BreadTypeUiState
import com.example.pizza.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun pager(
    data: List<BreadTypeUiState>,
    modifier: Modifier = Modifier,
    state: PagerState = rememberPagerState(data.size),
) {
    HorizontalPager(
        pageCount = data.size,
        state = state,
        modifier = modifier
            .height(250.dp)
            .paint(
                painter = painterResource(id = R.drawable.plate),
                contentScale = ContentScale.Inside
            ),
        verticalAlignment = Alignment.CenterVertically,

        ) { pageIndex ->

        Box(
            modifier = Modifier.padding(32.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data[pageIndex].Bread)
                    .crossfade(true)
                    .build(),
                contentDescription = "Bread",

                )

        }


    }
}




