package ru.alexbur.smartwallet.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.theme.PingDarkColor
import ru.alexbur.smartwallet.ui.theme.PingLightColor
import ru.alexbur.smartwallet.ui.theme.TextGrayColor
import javax.inject.Inject

@Composable
fun AuthorizationScreen(
    navController: NavHostController?,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {

    val signInRequestCode = 1
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account != null) {
                    val email = account.email
                    val displayName = account.givenName
                    if (email != null) {
                        viewModel.obtainEvent(
                            AuthorizationViewModel.Event.RegistrationStarted(
                                email,
                                displayName
                            )
                        )
                    } else {
                        viewModel.obtainEvent(AuthorizationViewModel.Event.RegistrationFailed("Email is null"))
                    }
                } else {
                    viewModel.obtainEvent(AuthorizationViewModel.Event.RegistrationFailed("Account is null"))
                }
            } catch (e: ApiException) {
                viewModel.obtainEvent(AuthorizationViewModel.Event.RegistrationFailed(e.localizedMessage))
            }
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
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
        }

        Text(
            modifier = Modifier
                .padding(bottom = 8.dp)
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
                .clickable {
                    authResultLauncher.launch(signInRequestCode)
                }
                .padding(14.dp)
                .align(Alignment.BottomCenter),
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

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestProfile()
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context, signInOptions)
}

class AuthResultContract : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
    override fun createIntent(context: Context, input: Int): Intent =
        getGoogleSignInClient(context).signInIntent.putExtra("input", input)

    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        return when (resultCode) {
            Activity.RESULT_OK -> GoogleSignIn.getSignedInAccountFromIntent(intent)
            else -> null
        }
    }
}

class AuthorizationScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            AuthorizationScreen(navGraph)
        }
    }
}