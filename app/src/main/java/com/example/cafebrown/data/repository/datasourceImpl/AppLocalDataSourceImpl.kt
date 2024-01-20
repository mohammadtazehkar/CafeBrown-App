package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.db.AppDAO
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource

class AppLocalDataSourceImpl(
    private val appDAO: AppDAO
): AppLocalDataSource {

}