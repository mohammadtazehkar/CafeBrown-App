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
import com.example.cafebrown.utils.ArgumentKeys.CATEGORY_ID
import com.example.cafebrown.utils.ArgumentKeys.FROM
import com.example.cafebrown.utils.ArgumentKeys.MOBILE_NUMBER
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_ID
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_TITLE
import com.example.cafebrown.utils.Destinations.HOME_SCREEN
import com.example.cafebrown.utils.Destinations.LOGIN_SCREEN
import com.example.cafebrown.utils.Destinations.MENU_LIST_SCREEN
import com.example.cafebrown.utils.Destinations.PRODUCT_DETAIL_SCREEN
import com.example.cafebrown.utils.Destinations.PRODUCT_LIST_SCREEN
import com.example.cafebrown.utils.Destinations.PROFILE_SCREEN
import com.example.cafebrown.utils.Destinations.SPLASH_SCREEN
import com.example.cafebrown.utils.Destinations.VERIFY_SCREEN

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
//        startDestination = SPLASH_SCREEN,
        startDestination = MENU_LIST_SCREEN,
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
                }
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
//                onNavigateToAddService = { catId,catTitle ->
//                    navController.navigate("$ADD_SERVICE_SCREEN/$catId/$catTitle")
//                },
//                onDrawerItemClick = { navigateTo ->
//                    if (navigateTo.isNotEmpty()) {
//                        navController.navigate(navigateTo)
//                    }
//                },
//                onLogoutCompleted = {
//                    navController.navigate(SPLASH_SCREEN){
//                        popUpTo(HOME_SCREEN) {
//                            inclusive = true
//                        }
//                    }
//                }
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
                }
            )
        }

        composable(
            route = MENU_LIST_SCREEN,
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
                onNavigateToProductList = {categoryId->
                    navController.navigate("$PRODUCT_LIST_SCREEN/$categoryId")
                },
                onNavUp = navController::navigateUp
            )
        }

        composable(
            route = "$PRODUCT_LIST_SCREEN/{$CATEGORY_ID}",
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
                onNavigateToDetail = {productId,productTitle ->
                    navController.navigate("$PRODUCT_DETAIL_SCREEN/$productId/$productTitle")
                },
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
                onNavUp = navController::navigateUp
            )
        }
//        composable(
//            route = MY_SERVICES_SCREEN,
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            MyServiceListScreen (
//                onExpiredToken = {
//                    navController.navigate(SPLASH_SCREEN){
//                        popUpTo(MY_SERVICES_SCREEN) {
//                            inclusive = true
//                        }
//                        popUpTo(HOME_SCREEN) {
//                            inclusive = true
//                        }
//                    }
//                },
//                onNavUp = navController::navigateUp
//            )
//        }
//        composable(
//            route = MY_ADDRESSES_SCREEN,
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            MyAddressListScreen(
//                sharedViewModel = sharedViewModel,
//                onAddAddressClick = { navController.navigate("$ADD_ADDRESS_SCREEN/0/ / /0.00/0.00") },
//                onEditAddressClick = { addressId,addressTitle,address,latitude,longitude ->
//
//                    navController.navigate("$ADD_ADDRESS_SCREEN/$addressId/$addressTitle/$address/$latitude/$longitude")
//                },
//                onShowLocation = {
//                    navController.navigate("$MAP_SCREEN/$FOR_VIEW_LOCATION")
//                },
//                onExpiredToken = {
//                    navController.navigate(SPLASH_SCREEN){
//                        popUpTo(MY_ADDRESSES_SCREEN) {
//                            inclusive = true
//                        }
//                        popUpTo(HOME_SCREEN) {
//                            inclusive = true
//                        }
//                    }
//                },
//                onNavUp = navController::navigateUp
//            )
//        }
//        composable(
//            route = MESSAGES_SCREEN,
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            MessageListScreen (
//                onNavUp = navController::navigateUp
//            )
//        }
//        composable(
//            route = RULES_SCREEN,
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            RulesScreen(
//                onNavUp = navController::navigateUp
//            )
//        }
//        composable(
//            route = ABOUT_US_SCREEN,
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            AboutUsScreen(
//                onNavUp = navController::navigateUp
//            )
//        }
//        composable(
//            route = "$ADD_ADDRESS_SCREEN/{$ADDRESS_ID}/{$ADDRESS_TITLE}/{$ADDRESS}/{$LATITUDE}/{$LONGITUDE}",
//            arguments = listOf(
//                navArgument(ADDRESS_ID){
//                    type = NavType.IntType
//                    defaultValue = 0
//                },
//                navArgument(ADDRESS_TITLE){
//                    type = NavType.StringType
//                    defaultValue = ""
//                },
//                navArgument(ADDRESS){
//                    type = NavType.StringType
//                    defaultValue = ""
//                },
//                navArgument(LATITUDE){
//                    type = NavType.StringType
//                    defaultValue = ""
//                },
//                navArgument(LONGITUDE){
//                    type = NavType.StringType
//                    defaultValue = ""
//                }
//            ),
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            AddAddressScreen (
//                sharedViewModel = sharedViewModel,
//                onNavUp = navController::navigateUp,
//                onSelectLocation = {forWhat ->
//                    navController.navigate("$MAP_SCREEN/$forWhat")
//                },
//                onExpiredToken = {
//                    navController.navigate(SPLASH_SCREEN){
//                        popUpTo(ADD_ADDRESS_SCREEN) {
//                            inclusive = true
//                        }
//                        popUpTo(MY_ADDRESSES_SCREEN) {
//                            inclusive = true
//                        }
//                        popUpTo(HOME_SCREEN) {
//                            inclusive = true
//                        }
//                    }
//                },
//                onSuccess = {
//                    navController.navigate(MY_ADDRESSES_SCREEN){
//                        popUpTo(ADD_ADDRESS_SCREEN) {
//                            inclusive = true
//                        }
//                        popUpTo(MY_ADDRESSES_SCREEN) {
//                            inclusive = true
//                        }
//                    }
//                },
//            )
//        }
//        composable(
//            route = "$ADD_SERVICE_SCREEN/{$CATEGORY_ID}/{$CATEGORY_TITLE}",
//            arguments = listOf(
//                navArgument(CATEGORY_ID){
//                    type = NavType.IntType
//                    defaultValue = 0
//            },
//                navArgument(CATEGORY_TITLE){
//                    type = NavType.StringType
//                    defaultValue = ""
//                }
//            ),
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            AddServiceScreen(
//                sharedViewModel = sharedViewModel,
//                onSelectLocation = {
//                    navController.navigate("$MAP_SCREEN/$FOR_ADD_LOCATION")
//                },
//                onShowLocation = {
//                    navController.navigate("$MAP_SCREEN/$FOR_VIEW_LOCATION")
//                },
//                onExpiredToken = {
//                    navController.navigate(SPLASH_SCREEN){
//                        popUpTo(ADD_SERVICE_SCREEN) {
//                            inclusive = true
//                        }
//                        popUpTo(HOME_SCREEN) {
//                            inclusive = true
//                        }
//                    }
//                },
//                onNavUp = navController::navigateUp
//            )
//        }
//        composable(
//            route = "$MAP_SCREEN/{$FOR_WHAT}",
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            val forWhat = it.arguments?.getString(FOR_WHAT)!!
//            MapScreen(
//                sharedViewModel = sharedViewModel,
//                forWhat = forWhat,
//                onSubmitLocation = navController::navigateUp,
//                onNavUp = navController::navigateUp
//            )
//        }
    }
}