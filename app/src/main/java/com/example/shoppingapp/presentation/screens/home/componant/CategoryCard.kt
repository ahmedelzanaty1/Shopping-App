package com.example.shoppingapp.presentation.screens.home.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.DataItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryCard(category : DataItem) {
    Box(modifier = Modifier.padding(15.dp)
        .fillMaxWidth()
        .height(120.dp)
        .shadow(4.dp)
        .clip(
            shape = RoundedCornerShape(10.dp)
        ) , contentAlignment = Alignment.BottomCenter) {
        GlideImage(
            model = painterResource(id = R.drawable.splash),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                , contentScale = ContentScale.Crop
        )
        Text(
            text = category.name,
            modifier = Modifier
                .background(color = Color.Transparent)
            , fontSize = 25.sp
            , color = Color.White

        )

    }

}
