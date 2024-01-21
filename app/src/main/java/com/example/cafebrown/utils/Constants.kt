package com.example.cafebrown.utils

object Constants {
    const val NAV_MENU = 1
    const val NAV_TABLE = 2
    const val NAV_RESERVE = 3
    const val NAV_TRANSACTION = 4
    const val NAV_PROFILE = 5
    const val NAV_INFO = 6

    const val USER_DATA_EMPTY = 801
    const val USER_DATA_TOKEN = 802
    const val USER_DATA_FULL = 803
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
    const val BASE_URL = "http://cafeapi.mohammadtazehkar.ir/"
    const val SUB_URL_POST_MOBILE = "api/Users/SendVerification"
    const val SUB_URL_POST_VERIFICATION = "api/Users/Verify"
    const val SUB_URL_PUT_UPDATE_PROFILE = "api/Users/UpdateProfile"
    const val SUB_URL_GET_MENU = "api/Menus/GetMain"
    const val SUB_URL_GET_SUB_MENU_AND_PRODUCT = "api/Menus/SubMenuAndProducts"
    const val SUB_URL_GET_TABLES = "api/tables/get"
    const val SUB_URL_GET_RESERVE_TIMES = "api/Reserves/ReserveTimes"
    const val SUB_URL_GET_PRODUCT_DETAIL = "api/Products/Details"
    const val SUB_URL_POST_COMMENT = "api/Products/AddComment"
    const val SUB_URL_GET_COMMENT = "api/Products/Comments"
    const val SUB_URL_GET_COFFEE_SHOP_DATA = "api/CoffeeShopData"
    const val SUB_URL_GET_TABLE_RESERVES = "api/Tables/Reserves"
    const val SUB_URL_POST_ADD_RESERVES = "/api/Reserves/add"
    const val SUB_URL_POST_ADD_KEY = "/api/Users/AddKey"
    const val SUB_URL_GET_USER_RESERVES = "/api/reserves/get"
    const val SUB_URL_GET_TRANSACTIONS = "/api/transactions/getAll"
    const val SUB_URL_GET_RESERVE_DETAIL = "/api/reserves/getById"
    const val SUB_URL_POST_COMPLETE_PAY = "/api/reserves/CompletePay"
    const val SUB_URL_POST_INCREASE_BALANCE = "/api/users/IncreaseWalletBalance"
    const val SUB_URL_POST_RESERVE_CHECK = "/api/reserves/check"
    const val SUB_URL_POST_COMPLAINT = "/api/complaints/add"

    const val AUTHORIZATION = "Authorization"
    const val TOKEN_TYPE = "bearer"

}
//endregion

//region JSonStatusCode
object JSonStatusCode{
    const val SUCCESS = 200
    const val EXPIRED_TOKEN = 401
    const val BAD_REQUEST = 400
    const val NOT_AUTHORIZED = 402
    const val SERVER_CONNECTION = 404
    const val INTERNET_CONNECTION = 13720818
}
//endregion

enum class AppKeyboard {
    Opened, Closed
}

