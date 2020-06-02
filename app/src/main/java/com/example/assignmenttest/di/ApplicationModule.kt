package com.example.assignmenttest.di

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class ApplicationModule :Application(),KodeinAware {
    override val kodein by Kodein.lazy {
  bind<Context>("ApplicationContext") with singleton { this@ApplicationModule }
            bind<ApplicationModule>() with singleton { this@ApplicationModule }
            import(appModule)
            import(networkModule)
        }
}