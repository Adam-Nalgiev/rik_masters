package ru.rikmasters.viewers_list_impl.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ru.rikmasters.viewers_list_impl.R
import ru.rikmasters.viewers_list_impl.presentation.view.theme.Gray
import ru.rikmasters.viewers_list_impl.presentation.view.theme.Green
import ru.rikmasters.viewers_list_impl.presentation.view.theme.LightGray
import ru.rikmasters.viewers_list_impl.presentation.view.theme.Red
import ru.rikmasters.viewers_list_impl.presentation.view.theme.Typography
import ru.rikmasters.viewers_list_impl.presentation.view.theme.White
import ru.rikmasters.viewers_list_impl.presentation.viewmodel.ViewersListViewModel

@Composable
fun ViewersList(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(16.dp))
    ) {
        List()
    }
}

@Composable
private fun List(
    modifier: Modifier = Modifier,
    viewModel: ViewersListViewModel = koinViewModel(),
) {
    val usersList = viewModel.usersFlow.collectAsState()


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val topUsersList =
            if (usersList.value.size < 3) { //Убирать 15-летнего или нет нз вообще
                usersList.value.subList(
                    0,
                    usersList.value.size
                )//Нет информации об еще одном пользователе среди списка пользователей
            } else {
                usersList.value.subList(0, 3)
            }

        AnimatedVisibility(
            visible = usersList.value.isEmpty(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            CircularProgressIndicator(color = Red)
        }

        repeat(topUsersList.size) { id ->
            val currentUser = topUsersList[id]

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {}
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileImage(
                        isOnline = currentUser.isOnline,
                        url = currentUser.avatarUrl
                    )
                    Text(
                        text = "${currentUser.username}, ${currentUser.age}",
                        style = Typography.bodyMedium
                    )
                }

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow),
                    contentDescription = null,
                    tint = Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            if (id < topUsersList.lastIndex)
                HorizontalDivider(
                    color = LightGray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(start = 54.dp)
                        .fillMaxWidth()
                )
        }
    }

}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    isOnline: Boolean,
    url: String
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = url,
            contentDescription = stringResource(R.string.descr_avatar),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(54.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )

        if (isOnline) {
            Canvas(
                Modifier.align(Alignment.BottomEnd)
            ) {
                drawCircle(
                    color = Green
                )
            }
        }
    }
}