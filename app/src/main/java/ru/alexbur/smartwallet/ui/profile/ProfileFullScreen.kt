package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.profile.toolbar.MainCollapsingToolbar

@Composable
fun ProfileFullScreen(
    modifier: Modifier,
    mainData: MainScreenDataEntity,
    name: String?,
    clickItem: (Long) -> Unit,
    editItem: (WalletEntity) -> Unit,
    deleteItem: (Long) -> Unit
) {
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
            state = rememberCollapsingToolbarScaffoldState()
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
        }
    }
}