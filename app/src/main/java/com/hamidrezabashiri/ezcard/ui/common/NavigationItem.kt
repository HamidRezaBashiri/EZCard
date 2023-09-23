package com.hamidrezabashiri.ezcard.ui.common

import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.navigation.MainDestinations

sealed class NavigationItem(val route: String,val iconResId: Int,val titleResId: Int) {
    object Home : NavigationItem(MainDestinations.HOME_ROUTE, R.drawable.icon_home, R.string.home)
    object Wallet :
        NavigationItem(MainDestinations.WALLET_ROUTE, R.drawable.icon_card, R.string.wallet)

    object Setting :
        NavigationItem(MainDestinations.SETTINGS_ROUTE, R.drawable.icon__setting, R.string.setting)


}