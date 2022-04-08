package ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import dagger.hilt.android.EntryPointAccessors
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.ui.MainActivity
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.transactions.categories.createcategory.CreateCategoryScreenFactory
import ru.alexbur.smartwallet.ui.utils.ChooseDataToolbar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel
) {

    val state = rememberLazyListState()
    val categoriesState = viewModel.listCategory.collectAsState(initial = emptyList())
    var currentCategory by remember {
        mutableStateOf(
            viewModel.chooseCategory.value?.categoryEntity
        )
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
                .padding(vertical = 24.dp)
        ) {
            ChooseDataToolbar(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), close = {
                navController.popBackStack()
            }) {
                currentCategory?.let {
                    viewModel.obtainEvent(CategoriesViewModel.Event.ChooseCategory(it))
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                state = state
            ) {
                items(categoriesState.value.size) { position ->
                    CategoryItem(
                        modifier = Modifier.fillMaxWidth(),
                        categoryEntity = categoriesState.value[position],
                        isSelect = currentCategory?.id == categoriesState.value[position].id,
                        onClick = { category ->
                            currentCategory = category
                        }
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(CreateCategoryScreenFactory.route)
                    }
                    .padding(vertical = 12.dp, horizontal = 24.dp),
                text = stringResource(id = R.string.create_category),
                style = TextStyle(color = Color.White, fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun categoriesViewModel(
    typeOperation: TypeOperation
): CategoriesViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as MainActivity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).categoriesViewModelFactory()

    return viewModel(
        factory = CategoriesViewModel.provideFactory(
            factory,
            type = typeOperation
        )
    )
}

class CategoriesScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion {
        private const val TYPE_OPERATION_CODE_KEY = "type_operation_code_key"
    }

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = "$route/{${
                TYPE_OPERATION_CODE_KEY
            }}",
            arguments = listOf(navArgument(TYPE_OPERATION_CODE_KEY) { type = NavType.IntType })
        ) {
            it.arguments?.getInt(TYPE_OPERATION_CODE_KEY)?.let { code ->
                CategoriesScreen(
                    navGraph, viewModel = categoriesViewModel(
                        TypeOperation.convertCodeToType(code)
                    )
                )
            }
        }
    }
}