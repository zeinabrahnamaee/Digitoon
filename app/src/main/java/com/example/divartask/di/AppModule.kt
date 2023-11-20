package com.example.divartask.di

import com.example.divartask.data.base.APIService
import com.example.divartask.data.repository.places.PlacesRepositoryImp
import com.example.divartask.domain.repository.places.PlacesRepository
import com.example.divartask.domain.usecase.places.GetPlacesUseCase
import com.example.divartask.domain.usecase.places.GetPlacesUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(Interceptor(){
            val token = ""

            var newRequest = it.request().newBuilder()
                .addHeader("x-access-token","$token")
                .build()

            it.proceed(newRequest)
        })

    @Provides
    @Singleton
    fun provideRemoteApi(): APIService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor(){
                val token = "YXBpa2V5OjY5Y1dxVW8wNGhpNFdMdUdBT2IzMmRXZXQjsllsVzBtSkNiwU9yLUxEamNDUXFMSzJnR29mS3plZg=="

                val newRequest = it.request().newBuilder()
                    .addHeader("x-access-token","Basic $token")
                    .build()

                it.proceed(newRequest)
            }).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://android-interview.divar.dev")
            .client(client)
            .build()
            .create(APIService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): LoginDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            LoginDatabase::class.java,
//            "Database"
//        ).build()
//    }

//    @Provides
//    @Singleton
//    fun provideDb(database: LoginDatabase): LoginDao {
//        return database.loginDao()
//    }

    @Provides
    @Singleton
    fun providePlacesRepository(apiService: APIService): PlacesRepository {
        return PlacesRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun providePlacesUseCase(repository: PlacesRepository): GetPlacesUseCase {
        return GetPlacesUseCaseImp(repository)
    }
}