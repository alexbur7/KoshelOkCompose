package ru.alexbur.smartwallet.ui.listwallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.listwallet.BalanceEntity
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun MainScreen(
    navController: NavController? = null,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val name = mainViewModel.nameFlow.collectAsState(initial = stringResource(id = R.string.unknown))
    val mainData = mainViewModel.mainScreenData.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.hello, name),
                color = Color.White,
                fontWeight = FontWeight(700),
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = stringResource(id = R.string.main_text_welcome, name),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )

            if (mainData.value == MainScreenDataEntity.shimmerData){
                //
            }else{

            }
        }
    }
}

@Composable
@Preview
fun PreviewMainScreen(){
    MainScreen()
}

@Composable
fun MainCard(
    balance: BalanceEntity
){

}

class MainScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            MainScreen(navGraph)
        }
    }
}