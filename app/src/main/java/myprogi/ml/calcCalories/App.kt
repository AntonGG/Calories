package myprogi.ml.calcCalories

import android.app.Application
import myprogi.ml.calcCalories.di.AppComponent
import myprogi.ml.calcCalories.di.AppModule
import myprogi.ml.calcCalories.di.DaggerAppComponent

class App : Application() {

    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}