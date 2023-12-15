package com.example.digitoon.di

import com.example.digitoon.data.remote.APIService
import com.example.digitoon.data.remote.network.NetworkResponseAdapterFactory
import com.example.digitoon.data.repository.detail.DetailRepositoryImp
import com.example.digitoon.data.repository.movies.MoviesRepositoryImp
import com.example.digitoon.domain.repository.detail.DetailRepository
import com.example.digitoon.domain.repository.movies.MoviesRepository
import com.example.digitoon.domain.usecase.detail.GetDetailUseCase
import com.example.digitoon.domain.usecase.detail.GetDetailUseCaseImp
import com.example.digitoon.domain.usecase.movies.GetMoviesUseCase
import com.example.digitoon.domain.usecase.movies.GetMoviesUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.omdbapi.com")
            .client(client)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun providePlacesRepository(apiService: APIService): MoviesRepository {
        return MoviesRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(apiService: APIService): DetailRepository {
        return DetailRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun providePlacesUseCase(repository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCaseImp(repository)
    }


    @Provides
    @Singleton
    fun provideDetailUseCase(repository: DetailRepository): GetDetailUseCase {
        return GetDetailUseCaseImp(repository)
    }
}