/*
 * Copyright (c) 2023 Shushant Tiwari.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.shushant.chattiez.data.datastore

import com.shushant.chattiez.data.utils.ConnectivityManagerNetworkMonitor
import com.shushant.chattiez.data.utils.NetworkMonitor
import org.koin.dsl.module


val datastoreModule = module {
    single<PrefStorage> { AppPreferences(get()) }
    single { DatastoreUtils(get()) }
    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(get()) }
}