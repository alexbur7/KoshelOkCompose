package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.profile.toolbar.MainCollapsingToolbar
import ru.alexbur.smartwallet.ui.utils.theme.EmptyNotesTextColor

@OptIn(ExperimentalToolbarApi::class)
@Composable
fun ProfileFullScreen(
    modifier: Modifier,
    mainData: MainScreenDataEntity,
    name: String?,
    clickItem: (Long) -> Unit,
    editItem: (WalletEntity) -> Unit,
    deleteItem: (Long) -> Unit
) {
    val collapsingState = rememberCollapsingToolbarScaffoldState()

    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp),
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                )
                MainCollapsingToolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    isShimmer = mainData == MainScreenDataEntity.shimmerData,
                    name = name,
                    mainData = mainData,
                )
            },
            state = collapsingState
        ) {
            WalletsList(
                modifier = Modifier
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                wallets = mainData.wallets,
                clickItem = {
                    clickItem(it)
                },
                editItem = {
                    editItem(it)
                },
                deleteItem = {
                    deleteItem(it)
                }
            )

            if (mainData.wallets.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = (collapsingState.toolbarState.progress * 300).dp),
                    text = stringResource(id = R.string.empty_notes),
                    style = TextStyle(
                        color = EmptyNotesTextColor,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}