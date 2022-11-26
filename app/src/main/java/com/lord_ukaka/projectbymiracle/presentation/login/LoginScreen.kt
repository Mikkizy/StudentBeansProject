package com.lord_ukaka.projectbymiracle.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lord_ukaka.projectbymiracle.R
import com.lord_ukaka.projectbymiracle.presentation.Screen
import com.lord_ukaka.projectbymiracle.utils.Dimensions
import com.lord_ukaka.projectbymiracle.utils.ValidationEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = remember {
        viewModel.state
    }.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collectLatest { event ->
            when (event) {
                is ValidationEvents.Success -> {
                    Toast.makeText(
                        context,
                        "Login successful",
                        Toast.LENGTH_LONG
                    ).show()

                    //Navigate to photos screen
                    navController.navigate(Screen.PhotosScreen.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.pagePadding),
        horizontalAlignment = Alignment.Start   
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(Dimensions.oneSpacer))
        Text(
            text = stringResource(id = R.string.details),
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(Dimensions.twoSpacers))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.email,
            onValueChange = {
                viewModel.onEvent(LoginEvents.EnteredEmail(it))
            },
            isError = state.value.emailError != null,
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        if (state.value.emailError != null) {
            Text(
                text = state.value.emailError ?: "",
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.oneSpacer))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.password,
            onValueChange = {
                viewModel.onEvent(LoginEvents.EnteredPassword(it))
            },
            isError = state.value.passwordError != null,
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.value.passwordError != null) {
            Text(
                text = state.value.passwordError ?: "",
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.fourSpacers))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
            ,
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color(0, 138, 0),
                contentColor = Color.White
            ),
            onClick = { viewModel.onEvent(LoginEvents.ClickedLoginButton) }
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}