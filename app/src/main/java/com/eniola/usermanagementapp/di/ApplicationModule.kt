package com.eniola.usermanagementapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.multidex.BuildConfig
import com.eniola.usermanagementapp.database.AppRoomDatabase
import com.eniola.usermanagementapp.utility.SharedPreferenceUtil
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule  {

    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideRetrofit(loggingInterceptor: HttpLoggingInterceptor) : Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return  Retrofit.Builder().baseUrl(BuildConfig.VERSION_NAME)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppRoomDatabase {
        return AppRoomDatabase.getInstance(context)!!
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        return loggingInterceptor
    }

    @Provides
    fun providesSharedPreferenceName(context: Context): String {
        return context.packageName + "_shared_pref"
    }

    @Provides
    fun provideSharedPreferenceUtil(sharedPreferences: SharedPreferences): SharedPreferenceUtil {
        return SharedPreferenceUtil(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@SharedPreferenceInfo prefName: String,
                                context: Context): SharedPreferences {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }
}