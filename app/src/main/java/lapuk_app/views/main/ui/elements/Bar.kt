package lapuk_app.views.main.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lapuk_app.R
import lapuk_app.views.main.ui.theme.LapukTheme
import lapuk_app.views.main.ui.theme.br1
import lapuk_app.views.main.ui.theme.br5

@Composable
fun TopBar() {
    LapukTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.10f)
                .background(br5)
                .offset(y = 12.5.dp)
        ) {
            ShowLogo(Color.White, false)
        }
    }
}

@Composable
fun BottomBar( navController: NavController) {
    val selectedItemState = remember { mutableIntStateOf(1) }
    var selectedItem by remember { selectedItemState }

    val items = listOf(
        "Home", "Segregate", "Articles", "Heatmap", "Info"
    )

    val icons = listOf(
        painterResource(id = R.drawable.home),
        painterResource(id = R.drawable.segregate),
        painterResource(id = R.drawable.articles),
        painterResource(id = R.drawable.heatmaps),
        painterResource(id = R.drawable.infocenter)
    )

    val navigationLabel = listOf(
        "home", "segregate", "articles", "heatmap", "info"
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.135f),
        containerColor = br1,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(icon = {
                Icon(
                    icons[index], contentDescription = item
                )
            }, label = { Text(item) }, selected = selectedItem == index, onClick = {
                selectedItem = index
                navController.navigate(navigationLabel[index]) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }, colors = NavigationBarItemColors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.Black,
                selectedIndicatorColor = br5,
                unselectedIconColor = Color.Black,
                unselectedTextColor = Color.Black,
                disabledIconColor = Color.Red,
                disabledTextColor = Color.Red
            )
            )
        }
    }
}