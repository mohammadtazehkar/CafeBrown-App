package com.example.cafebrown.utils

object Constants {
    const val NAV_MENU = 1
    const val NAV_TABLE = 2
    const val NAV_RESERVE = 3
    const val NAV_TRANSACTION = 4
    const val NAV_PROFILE = 5
    const val NAV_INFO = 6
}

//region Destinations
object Destinations{
    const val SPLASH_SCREEN = "splash"
    const val LOGIN_SCREEN = "login"
    const val VERIFY_SCREEN = "verify"
    const val PROFILE_SCREEN = "profile"
    const val HOME_SCREEN = "home"
    const val MENU_LIST_SCREEN = "menuList"
    const val PRODUCT_LIST_SCREEN = "productList"
    const val PRODUCT_DETAIL_SCREEN = "productDetail"
    const val TRANSACTION_SCREEN = "transaction"
    const val DESK_SCREEN = "desk"
    const val RESERVE_SCREEN = "reserve"
    const val RESERVE_HISTORY_SCREEN = "reserveHistory"
    const val ABOUT_US_SCREEN = "aboutUS"
}
//endregion

//region ArgumentKeys
object ArgumentKeys{
    const val MOBILE_NUMBER = "mobileNumber"
    const val FROM = "from"
    const val CATEGORY_ID = "categoryId"
    const val PRODUCT_ID = "productId"
    const val PRODUCT_TITLE = "productTitle"

}
//endregion

//region ServerConstants
object ServerConstants{
    const val BASE_URL = "http://behsazan.mohammadtazehkar.ir/"
//    const val SUB_URL_REGISTER = "api/CustomerApp/AddCustomer"

}
//endregion

//region JSonStatusCode
object JSonStatusCode{
//    const val SUCCESS = 200
//    const val EXPIRED_TOKEN = 401
//    const val DUPLICATE_USERNAME = 409
//    const val INVALID_USERNAME = 404
//    const val SERVER_CONNECTION = 404
//    const val INTERNET_CONNECTION = 13720818
}
//endregion

enum class AppKeyboard {
    Opened, Closed
}

