package com.example.cafebrown

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cafebrown.ui.screens.*
import com.example.cafebrown.utils.ArgumentKeys.CAPACITY
import com.example.cafebrown.utils.ArgumentKeys.CATEGORY_ID
import com.example.cafebrown.utils.ArgumentKeys.DESK_ID
import com.example.cafebrown.utils.ArgumentKeys.FROM
import com.example.cafebrown.utils.ArgumentKeys.MOBILE_NUMBER
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_ID
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_TITLE
import com.example.cafebrown.utils.ArgumentKeys.STATUS
import com.example.cafebrown.utils.ArgumentKeys.TABLE_NUMBER
import com.example.cafebrown.utils.Constants.NAV_INFO
import com.example.cafebrown.utils.Constants.NAV_MENU
import com.example.cafebrown.utils.Constants.NAV_PROFILE
import com.example.cafebrown.utils.Constants.NAV_RESERVE
import com.example.cafebrown.utils.Constants.NAV_TABLE
import com.example.cafebrown.utils.Constants.NAV_TRANSACTION
import com.example.cafebrown.utils.Destinations.DESK_SCREEN
import com.example.cafebrown.utils.Destinations.ABOUT_US_SCREEN
import com.example.cafebrown.utils.Destinations.HOME_SCREEN
import com.example.cafebrown.utils.Destinations.LOGIN_SCREEN
import com.example.cafebrown.utils.Destinations.MENU_LIST_SCREEN
import com.example.cafebrown.utils.Destinations.PRODUCT_DETAIL_SCREEN
import com.example.cafebrown.utils.Destinations.PRODUCT_LIST_SCREEN
import com.example.cafebrown.utils.Destinations.PROFILE_SCREEN
import com.example.cafebrown.utils.Destinations.RESERVE_HISTORY_SCREEN
import com.example.cafebrown.utils.Destinations.RESERVE_SCREEN
import com.example.cafebrown.utils.Destinations.SPLASH_SCREEN
import com.example.cafebrown.utils.Destinations.TRANSACTION_SCREEN
import com.example.cafebrown.utils.Destinations.VERIFY_SCREEN

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN,
    ) {

        composable(
            route = SPLASH_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(LOGIN_SCREEN) {
                        popUpTo(SPLASH_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(HOME_SCREEN) {
                        popUpTo(SPLASH_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToProfile = {
                    navController.navigate("$PROFILE_SCREEN/$VERIFY_SCREEN") {
                        popUpTo(SPLASH_SCREEN) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = LOGIN_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            LoginScreen(
                onNavigateToVerify = { mobileNumber ->
                    navController.navigate("$VERIFY_SCREEN/$mobileNumber")
                },
            )
        }
        composable(
            route = "$VERIFY_SCREEN/{$MOBILE_NUMBER}",
            arguments = listOf(
                navArgument(MOBILE_NUMBER) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            VerifyScreen(
                onNavigateToProfile = {
                    navController.navigate("$PROFILE_SCREEN/$VERIFY_SCREEN") {
                        popUpTo("$VERIFY_SCREEN/{$MOBILE_NUMBER}") {
                            inclusive = true
                        }
                        popUpTo(LOGIN_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(HOME_SCREEN) {
                        popUpTo("$VERIFY_SCREEN/{$MOBILE_NUMBER}") {
                            inclusive = true
                        }
                        popUpTo(LOGIN_SCREEN) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(
            route = HOME_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            HomeScreen(
                onClickItem = { navType ->
                    when (navType) {
                        NAV_MENU -> {
                            navController.navigate("$MENU_LIST_SCREEN/$HOME_SCREEN")
                        }

                        NAV_TABLE -> {
                            navController.navigate(DESK_SCREEN)
                        }

                        NAV_RESERVE -> {
                            navController.navigate(RESERVE_HISTORY_SCREEN)
                        }
                        NAV_TRANSACTION -> {
                            navController.navigate(TRANSACTION_SCREEN)
                        }
                        NAV_PROFILE -> {
                            navController.navigate("$PROFILE_SCREEN/$HOME_SCREEN")
                        }
                        NAV_INFO -> {
                            navController.navigate(ABOUT_US_SCREEN)
                        }
                    }

                }
            )
        }
        composable(
            route = "$PROFILE_SCREEN/{$FROM}",
            arguments = listOf(
                navArgument(FROM) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            ProfileScreen(
                onSignUpCompleted = {
                    navController.navigate(HOME_SCREEN) {
                        popUpTo("$PROFILE_SCREEN/{$FROM}") {
                            inclusive = true
                        }
                    }
                },
                onNavUp = navController::navigateUp,
                onExpiredToken = {
                    navController.navigate(SPLASH_SCREEN){
                        popUpTo("$PROFILE_SCREEN/{$FROM}") {
                            inclusive = true
                        }
                        popUpTo(HOME_SCREEN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "$MENU_LIST_SCREEN/{$FROM}",
            arguments = listOf(
                navArgument(FROM) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            MenuListScreen(
                onNavigateToProductList = { categoryId, from ->
                    navController.navigate("$PRODUCT_LIST_SCREEN/$categoryId/$from")
                },
                onNavUp = navController::navigateUp,
                onExpiredToken = {
                    navController.navigate(SPLASH_SCREEN){
                        popUpTo("$MENU_LIST_SCREEN/{$FROM}") {
                            inclusive = true
                        }
                        popUpTo(HOME_SCREEN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "$PRODUCT_LIST_SCREEN/{$CATEGORY_ID}/{$FROM}",
            arguments = listOf(
                navArgument(CATEGORY_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            ProductListScreen(
                onNavigateToDetail = { productId, productTitle ->
                    navController.navigate("$PRODUCT_DETAIL_SCREEN/$productId/$productTitle")
                },
                onExpiredToken = {
                    navController.navigate(SPLASH_SCREEN){
                        popUpTo("$PRODUCT_LIST_SCREEN/{$CATEGORY_ID}/{$FROM}") {
                            inclusive = true
                        }
                        popUpTo("$MENU_LIST_SCREEN/{$FROM}") {
                            inclusive = true
                        }
                        popUpTo(HOME_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                onNavUp = navController::navigateUp
            )
        }
        composable(
            route = TRANSACTION_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            TransactionScreen(
                onNavUp = navController::navigateUp
            )
        }

        composable(
            route = "$PRODUCT_DETAIL_SCREEN/{$PRODUCT_ID}/{$PRODUCT_TITLE}",
            arguments = listOf(
                navArgument(PRODUCT_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(PRODUCT_TITLE) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            ProductDetailScreen(
                onNavUp = navController::navigateUp,
                onExpiredToken = {
                    navController.navigate(SPLASH_SCREEN){
                        popUpTo("$PRODUCT_DETAIL_SCREEN/{$PRODUCT_ID}/{$PRODUCT_TITLE}") {
                            inclusive = true
                        }
                        popUpTo("$PRODUCT_LIST_SCREEN/{$CATEGORY_ID}/{$FROM}") {
                            inclusive = true
                        }
                        popUpTo("$MENU_LIST_SCREEN/{$FROM}") {
                            inclusive = true
                        }
                        popUpTo(HOME_SCREEN) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(
            route = DESK_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            DeskListScreen(
                onNavigateToReserve = { deskId, capacity, tableNumber, status ->
                    navController.navigate("$RESERVE_SCREEN/$deskId/$capacity/$tableNumber/$status")
                },
                onNavUp = navController::navigateUp
            )
        }
        composable(
            route = "$RESERVE_SCREEN/{$DESK_ID}/{$CAPACITY}/{$TABLE_NUMBER}/{$STATUS}",
            arguments = listOf(
                navArgument(DESK_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(CAPACITY) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(TABLE_NUMBER) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(STATUS) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {

            ReserveScreen(
                onNavigateToMenu = {
                    navController.navigate("$MENU_LIST_SCREEN/$RESERVE_SCREEN")
                },
                onNavUp = navController::navigateUp
            )
        }
        composable(
            route = RESERVE_HISTORY_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {

            ReserveHistoryScreen(
                onNavigateToReserve = {
                    navController.navigate(RESERVE_SCREEN)
                },
                onNavUp = navController::navigateUp
            )
        }

        composable(
            route = ABOUT_US_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            AboutUsScreen(
                onNavUp = navController::navigateUp
            )
        }
    }
}