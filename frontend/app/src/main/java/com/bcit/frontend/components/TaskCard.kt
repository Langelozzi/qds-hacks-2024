package com.bcit.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bcit.frontend.R
import com.bcit.frontend.classes.TaskViewModel
import com.bcit.frontend.dataClasses.Task
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskCard(
    positionState: AnchoredDraggableState<DragAnchors>,
    task: Task,
    onClick: () -> Unit,
    onUserSwipe: () -> Unit = { }
) {
    SwipeableCard(
        draggableCardState = positionState,
        onClick = { TaskViewModel().handleComparisonResult(task, null)},
        onSwipeLeft = {  },
        onSwipeRight = { },
    ) {

        Column (modifier = Modifier
            .padding(15.dp)
        )
        {

                Text(text = task.title)
                Row ( horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()

                ){
                    Image(
                        painter = painterResource(id = R.drawable.stats2),
                        contentDescription = task.title
                        , modifier = Modifier
                            .border(BorderStroke(5.dp, Color(0xFFFFE264)))
                    )

                }

                    Text(
                        text = task.course,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = task.type.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Worth: ${task.weight}%",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
        }
    }
}
