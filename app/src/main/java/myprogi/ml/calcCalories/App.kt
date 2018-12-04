package myprogi.ml.calcCalories

import android.app.Application
import myprogi.ml.calcCalories.di.AppComponent
import myprogi.ml.calcCalories.di.AppModule

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getAppComponent() = appComponent
}