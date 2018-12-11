package myprogi.ml.calcCalories.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import myprogi.ml.calcCalories.roomDb.ProfileDao
import myprogi.ml.calcCalories.roomDb.ProfileDatabase
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideContext() = application.applicationContext

}


@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun dbProvider(appContext: Context): ProfileDao {
        return Room.databaseBuilder(appContext,
                ProfileDatabase::class.java, "Calories.db")
                .fallbackToDestructiveMigration()
                .build().dao()
    }
}
