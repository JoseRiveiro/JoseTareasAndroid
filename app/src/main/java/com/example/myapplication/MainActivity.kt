package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.features.tareasDesc.tareasDescripcionScreen
import com.example.myapplication.features.tareasScreen.TareasScreen_ROUTE
import com.example.myapplication.features.tareasScreen.todoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            val navHostController = rememberNavController()
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text("Tareas") },
                        navigationIcon = {
                            IconButton(onClick = { navHostController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")
                            }

                        })
                },
                /*
                bottomBar = {
                    tareasNavigationBar(navController = navHostController)

                },


                 */

            ) {paddingValues ->



     Box(modifier = Modifier.padding(paddingValues))
                NavHost(navController = navHostController, startDestination = TareasScreen_ROUTE) {



                        todoScreen(navHostController = navHostController)
                        tareasDescripcionScreen()
                        //currentTareaScreen()


                    //tareasDescripcionScreen(navHostController)

                }
            }


        }
    }
}




@Composable
fun TodoBottonNavigation(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
   BottomNavigation {
       BottonNavigationScreens.entries.forEach {
BottomNavigationItem(
    label = {Text(text = stringResource(id = it.label))},
    selected = navBackStackEntry?.destination?.hierarchy?.any{ entry -> entry.route == it.route}== true,
    onClick = {
            navController.navigate(it.route){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
    },

    icon = {  Icon(imageVector =it.icon, contentDescription = "Edit Task") })
   }
}
}

@Composable
fun tareasNavigationBar(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {

            BottonNavigationScreens.entries.forEach {
                val selected = navBackStackEntry?.destination?.hierarchy?.any{ entry -> entry.route == it.route}== true
                BottomNavigationItem(
                    label = {Text(text = stringResource(id = it.label))},
                    selected = selected,
                    onClick = {
                        navController.navigate(it.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },

                    icon = {
                        Icon(imageVector =it.icon, contentDescription = "Edit Task", tint = if(selected) Color.Red else Color.Black) })

        }
    }
}



enum class BottonNavigationScreens(val route: String, val icon: ImageVector, @StringRes val label:Int){


    Lista(TareasScreen_ROUTE, Icons.TwoTone.Home,R.string.todotitle)
}








