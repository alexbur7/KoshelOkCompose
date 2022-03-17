package ru.alexbur.koshelok.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.koshelok.R
import ru.alexbur.koshelok.di.navigation.NavigationFactory
import ru.alexbur.koshelok.ui.theme.BackgroundColor
import ru.alexbur.koshelok.ui.theme.PingDarkColor
import ru.alexbur.koshelok.ui.theme.PingLightColor
import ru.alexbur.koshelok.ui.theme.TextGrayColor
import javax.inject.Inject

@Composable
fun AuthorizationScreen(
    navController: NavHostController?,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.auth_main),
            contentDescription = "Auth main image",
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = stringResource(id = R.string.title_welcome),
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.text_welcome),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            color = TextGrayColor,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(corner = CornerSize(24.dp)))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            PingLightColor,
                            PingDarkColor
                        )
                    )
                )
                .padding(14.dp)
                .clickable {

                }
            ,
            text = stringResource(id = R.string.text_button_google),
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}

@Preview
@Composable
fun PreviewAuthScreen() {
    AuthorizationScreen(navController = null)
}

class AuthorizationScreenFactory @Inject constructor() : NavigationFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            AuthorizationScreen(navGraph)
        }
    }
}