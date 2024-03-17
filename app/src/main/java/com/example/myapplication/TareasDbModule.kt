package com.example.myapplication
import android.content.Context
import androidx.room.Room
import com.example.myapplication.bd.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TareasDbModule {
    @Provides
    fun providesTaskDao(@ApplicationContext context:Context)=
        Room.databaseBuilder(context, AppDatabase::class.java,"ToDoDB").build().taskDao()
}
