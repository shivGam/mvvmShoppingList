package edu.first.mvvmshopping.ui

import android.app.Application
import edu.first.mvvmshopping.data.db.entities.ShoppingDatabase
import edu.first.mvvmshopping.data.repositories.ShoppingRepositories
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApp : Application(),KodeinAware{
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApp))
        bind() from singleton {instance()}
        bind() from singleton { ShoppingRepositories(instance())}
        bind() from provider {
            ShoppingViewModelFactory(instance())
        }
        bind<ShoppingDatabase>() with singleton {
            ShoppingDatabase.getInstance(context = applicationContext)
        }
    }
}