package myprogi.ml.calcCalories.di

import dagger.Component
import myprogi.ml.calcCalories.FavoriteActivity
import myprogi.ml.calcCalories.MyDataActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DatabaseModule::class))
interface AppComponent {
    fun inject(favoriteActivity: FavoriteActivity)
    fun inject(myDataActivity: MyDataActivity)
}