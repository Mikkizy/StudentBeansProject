package com.lord_ukaka.projectbymiracle.presentation.photos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import com.lord_ukaka.projectbymiracle.utils.Dimensions

@Composable
fun ListContent(items: List<AlbumInfo>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = Dimensions.pagePadding.minus(8.dp)),
        verticalArrangement = Arrangement.spacedBy(Dimensions.oneSpacer.times(0.8f))
    ) {
        items(items = items) { albumInfo ->
            AlbumItem(albumInfo = albumInfo)
        }
    }
}

@Composable
fun AlbumItem(albumInfo: AlbumInfo) {
    val painter = rememberAsyncImagePainter(model = albumInfo.thumbnailUrl)
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.3f))
            .shadow(shape = MaterialTheme.shapes.medium, elevation = 1.dp)
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .shadow(shape = MaterialTheme.shapes.medium, elevation = 1.dp)
                .size(80.dp, 80.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = "Album Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimensions.pagePadding.minus(10.dp))
        ) {
            Text(
                text = albumInfo.title,
                modifier = Modifier
                    .align(Alignment.Center),
                style = MaterialTheme.typography.body2
            )
        }
    }
}