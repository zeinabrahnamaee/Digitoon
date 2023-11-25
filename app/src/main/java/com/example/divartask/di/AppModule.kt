package com.example.divartask.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.divartask.data.database.AppDatabase
import com.example.divartask.data.database.dao.DetailDao
import com.example.divartask.data.database.dao.PlacesDao
import com.example.divartask.data.database.dao.WidgetsDao
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.network.NetworkResponseAdapterFactory
import com.example.divartask.data.repository.detail.DetailRepositoryImp
import com.example.divartask.data.repository.places.PlacesRepositoryImp
import com.example.divartask.data.repository.widgets.WidgetsRepositoryImp
import com.example.divartask.domain.repository.detail.DetailRepository
import com.example.divartask.domain.repository.places.PlacesRepository
import com.example.divartask.domain.repository.widgets.WidgetsRepository
import com.example.divartask.domain.usecase.detail.GetDetailUseCase
import com.example.divartask.domain.usecase.detail.GetDetailUseCaseImp
import com.example.divartask.domain.usecase.places.GetPlacesUseCase
import com.example.divartask.domain.usecase.places.GetPlacesUseCaseImp
import com.example.divartask.domain.usecase.usercity.GetUserLocUseCase
import com.example.divartask.domain.usecase.usercity.GetUserLocUseCaseImp
import com.example.divartask.domain.usecase.widgets.GetWidgetsUseCase
import com.example.divartask.domain.usecase.widgets.GetWidgetsUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRemoteApi(): APIService {

        val logging = HttpLoggingInterceptor()
// set your desired log level
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor{
                val token = "YXBpa2V5OjY5Y1dxVW8wNGhpNFdMdUdBT2IzMmRXZXQjsllsVzBtSkNiwU9yLUxEamNDUXFMSzJnR29mS3plZg=="

                val newRequest = it.request().newBuilder()
                    .addHeader("x-access-token","Basic $token")
                    .build()

                Log.e("Request", newRequest.toString(), )

                it.proceed(newRequest)

            }
            ).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://android-interview.divar.dev")
            .client(client)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: AppDatabase): PlacesDao {
        return database.placesDao()
    }

    @Provides
    @Singleton
    fun providePlacesRepository(apiService: APIService, dao: PlacesDao): PlacesRepository {
        return PlacesRepositoryImp(apiService, dao)
    }

    @Provides
    @Singleton
    fun providePlacesUseCase(repository: PlacesRepository): GetPlacesUseCase {
        return GetPlacesUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideWidgetsDao(database: AppDatabase): WidgetsDao {
        return database.widgetsDao()
    }

    @Provides
    @Singleton
    fun providePostsRepository(apiService: APIService, dao: WidgetsDao): WidgetsRepository {
        return WidgetsRepositoryImp(apiService, dao)
    }

    @Provides
    @Singleton
    fun providePostsUseCase(repository: WidgetsRepository): GetWidgetsUseCase {
        return GetWidgetsUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideLocUseCase(repository: WidgetsRepository): GetUserLocUseCase {
        return GetUserLocUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideDetailDao(database: AppDatabase): DetailDao {
        return database.detailDao()
    }

    @Provides
    @Singleton
    fun provideDetailRepository(apiService: APIService, dao: DetailDao): DetailRepository {
        return DetailRepositoryImp(apiService, dao)
    }

    @Provides
    @Singleton
    fun provideDetailUseCase(repository: DetailRepository): GetDetailUseCase {
        return GetDetailUseCaseImp(repository)
    }
}