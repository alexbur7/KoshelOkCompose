package ru.alexbur.smartwallet.ui.transactions.categories.createcategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.transactions.createtransaction.TypeOperationChooser
import ru.alexbur.smartwallet.ui.utils.OutlinedEditText
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.TitleWithBackButtonToolbar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.SelectedRadioButtonColor
import javax.inject.Inject

@Composable
fun CreateCategoryScreen(
    navController: NavController,
    viewModel: CreateCategoryViewModel = hiltViewModel()
) {
    val createCategoryState = viewModel.createCategoryFlow.collectAsState()
    val iconsState = viewModel.listIconModel.collectAsState()
    val snackBarHostState = SnackbarHostState()
    val snackBarMessage = viewModel.snackBarMessage.collectAsState("")

    LaunchedEffect(key1 = snackBarMessage.value) {
        if (snackBarMessage.value.isNotBlank()) {
            snackBarHostState.showSnackbar(
                message = snackBarMessage.value
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(bottom = BottomNavigationHeight)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            TitleWithBackButtonToolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                titleText = stringResource(
                    id = R.string.new_category
                )
            ) {
                navController.popBackStack()
            }

            OutlinedEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textLabel = stringResource(id = R.string.title_category),
                onValueChanged = {
                    viewModel.obtainEvent(CreateCategoryViewModel.Event.UpdateNameCategory(it))
                },
                initialField = createCategoryState.value.operation,
                maxLength = 100 //TODO можем командой обсудить
            )

            TypeOperationChooser(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textLabel = stringResource(id = R.string.type),
                currentOperationType = createCategoryState.value.type
            ) {
                viewModel.obtainEvent(CreateCategoryViewModel.Event.UpdateTypeOperation(it))
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                text = stringResource(id = R.string.icon),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700)
                )
            )

            LazyVerticalGrid(modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(6)) {
                items(iconsState.value.size) { position ->
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                if (createCategoryState.value.iconId == iconsState.value[position].resIcon)
                                    SelectedRadioButtonColor else Color.Transparent
                            )
                            .clickable {
                                viewModel.obtainEvent(
                                    CreateCategoryViewModel.Event.UpdateIconCategory(
                                        iconsState.value[position].resIcon
                                    )
                                )
                            }
                            .padding(16.dp)
                            .size(24.dp),
                        painter = painterResource(id = iconsState.value[position].resIcon),
                        contentDescription = stringResource(id = R.string.category_image_description),
                        contentScale = ContentScale.None
                    )
                }
            }
        }

        SnackbarHost(hostState = snackBarHostState) {
            SmartWalletSnackBar(snackbarData = it)
        }
    }
}

class CreateCategoryScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            CreateCategoryScreen(navController = navGraph)
        }
    }
}