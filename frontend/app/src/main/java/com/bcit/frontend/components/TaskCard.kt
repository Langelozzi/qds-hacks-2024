package com.bcit.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.frontend.R
import com.bcit.frontend.dataClasses.Task

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskCard(
    positionState: AnchoredDraggableState<DragAnchors>,
    task: Task,
    onUserSwipe : (Task) -> Unit
) {
    var cardSwiped by remember { mutableStateOf(false) }
    LaunchedEffect(cardSwiped) {
        if (cardSwiped) {
            positionState.animateTo(DragAnchors.OnScreen)
            cardSwiped = false
        }
    }
    val onSwipe = fun (task: Task) {
        onUserSwipe(task)
        cardSwiped = true
    }

    SwipeableCard(
        draggableCardState = positionState,
        onSwipeLeft = { onSwipe(task) },
        onSwipeRight = { onSwipe(task) },

    ) {

        Column (modifier = Modifier
            .background(color = Color(0xFF72B3DF))
            .padding(15.dp)
//            .background(Color.Transparent)
        )
        {

                Text(text = "${task.course}: ${task.title}",
                    style = MaterialTheme.typography.bodyLarge
                        .copy(
                        fontFamily = customFontFamily,
                        fontSize = 30.sp,
                            color = Color(0xFF1D0101)
                    ),
                    modifier = Modifier
                        .padding(3.dp))
                Row ( horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()

                ){
                    Image(
                        painter = painterResource(id = task.imageId),
                        contentDescription = task.title
                        , modifier = Modifier
                            .border(BorderStroke(5.dp, Color(0xFFFFE264)))
//                            .size(200.dp)
                    )

                }
//                    Text(
//                        text = "${task.course}: ${task.}",
//                        style = MaterialTheme.typography.bodyLarge                        .copy(
//                            fontFamily = customFontFamily,
//                            fontSize = 25.sp,
//                            color = Color(0xFF1D0101)
//                        ),
//                        modifier = Modifier.padding(vertical = 4.dp)
//                    )
                    Text(
                        text = "Due: ${task.dueDate}",
                        style = MaterialTheme.typography.bodyLarge                        .copy(
                            fontFamily = customFontFamily,
                            fontSize = 25.sp,
                            color = Color(0xFF1D0101)
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = task.type.toString(),
                        style = MaterialTheme.typography.bodyLarge                        .copy(
                            fontFamily = customFontFamily,
                            fontSize = 25.sp,
                            color = Color(0xFF1D0101)
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Worth: ${task.weight}%",
                        style = MaterialTheme.typography.bodyLarge                        .copy(
                            fontFamily = customFontFamily,
                            fontSize = 25.sp,
                            color = Color(0xFF1D0101)
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
        }
    }
}
