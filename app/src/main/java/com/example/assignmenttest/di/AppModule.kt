package com.example.assignmenttest.di

import android.content.Context
import android.content.res.Resources
import com.example.assignmenttest.util.SessionManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME="App Module"

val appModule = Kodein.Module(MODULE_NAME,false){
    bind<SessionManager>() with singleton { getSessionManager(instance("ApplicationContext")) }
    bind<Resources>() with singleton { instance<ApplicationModule>().resources }
}
// for local data saving
fun getSessionManager(context : Context): SessionManager = SessionManager(context)
