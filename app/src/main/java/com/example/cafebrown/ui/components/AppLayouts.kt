package com.example.cafebrown.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.cafebrown.R

@Composable
fun MainColumn(
    modifier: Modifier = Modifier,
    verticalArrangement : Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment : Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    val image = ImageBitmap.imageResource(R.drawable.img_main_pattern)
    // Define the scale factor for the repeated image
    val scaleFactor = 3f // Adjust this value based on your desired size
    val patternModifier = Modifier.drawWithContent {
        drawContent()
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                shader = ImageShader(
                    image,
                    TileMode.Repeated,
                    TileMode.Repeated
                )
            }
            // Draw the repeated pattern on the canvas with the desired scale
            repeat((size.width / (image.width * scaleFactor)).toInt() + 1) { x ->
                repeat((size.height / (image.height * scaleFactor)).toInt() + 1) { y ->
                    canvas.save()
                    canvas.scale(scaleFactor, scaleFactor)
                    canvas.drawImage(
                        image,
                        // Apply the scale factor to the width and height
                        Offset(x * image.width.toFloat(), y * image.height.toFloat()),
                        paint = paint
                    )
                    canvas.restore()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = patternModifier.fillMaxSize()
        ) {}
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    }
}
@Composable
fun MainBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val image = ImageBitmap.imageResource(R.drawable.img_main_pattern)
    // Define the scale factor for the repeated image
    val scaleFactor = 3f // Adjust this value based on your desired size
    val patternModifier = Modifier.drawWithContent {
        drawContent()
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                shader = ImageShader(
                    image,
                    TileMode.Repeated,
                    TileMode.Repeated
                )
            }
            // Draw the repeated pattern on the canvas with the desired scale
            repeat((size.width / (image.width * scaleFactor)).toInt() + 1) { x ->
                repeat((size.height / (image.height * scaleFactor)).toInt() + 1) { y ->
                    canvas.save()
                    canvas.scale(scaleFactor, scaleFactor)
                    canvas.drawImage(
                        image,
                        // Apply the scale factor to the width and height
                        Offset(x * image.width.toFloat(), y * image.height.toFloat()),
                        paint = paint
                    )
                    canvas.restore()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = patternModifier.fillMaxSize()
        ) {}
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            content()
        }
    }

}

@Composable
fun CardMediumCorner(
    modifier : Modifier = Modifier,
    content: @Composable () -> Unit
){
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = modifier
    ) {
        content()
    }
}

@Composable
fun CardColumnMediumCorner(
    modifier : Modifier = Modifier,
    cardModifier : Modifier = Modifier,
    columnModifier : Modifier = Modifier,
    columnHorizontalAlignment : Alignment.Horizontal = Alignment.CenterHorizontally ,
    content: @Composable () -> Unit
) {
    CardMediumCorner (
        modifier = cardModifier
    ){
        Column(
            modifier = columnModifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = columnHorizontalAlignment
        ) {
            content()
        }
    }
}

@Composable
fun CardBoxMediumCorner(
    modifier : Modifier = Modifier,
    cardModifier : Modifier = Modifier,
    boxModifier : Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
){
    CardMediumCorner (
        modifier = cardModifier
    ){
        Box(
            modifier = boxModifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp))
        {
            content()
        }
    }
}



@Composable
fun CardRowMediumCorner(
    modifier : Modifier = Modifier,
    cardModifier : Modifier = Modifier,
    rowModifier : Modifier = Modifier,
    rowVerticalAlignment : Alignment.Vertical = Alignment.CenterVertically ,
    content: @Composable RowScope.() -> Unit
) {
    CardMediumCorner (
        modifier = cardModifier
    ){
        Row(
            modifier = rowModifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalAlignment = rowVerticalAlignment
        ) {
            content()
        }
    }
}