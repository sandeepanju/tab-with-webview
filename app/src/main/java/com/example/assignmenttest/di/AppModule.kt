package com.example.assignmenttest.di

import android.content.res.Resources
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME="App Module"

val appModule = Kodein.Module(MODULE_NAME,false){
    bind<Resources>() with singleton { instance<ApplicationModule>().resources }
}

