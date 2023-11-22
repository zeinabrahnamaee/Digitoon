package com.example.divartask.di

import android.util.Log
import com.example.divartask.data.base.APIService
import com.example.divartask.data.repository.detail.DetailRepositoryImp
import com.example.divartask.data.repository.places.PlacesRepositoryImp
import com.example.divartask.data.repository.posts.PostsRepositoryImp
import com.example.divartask.domain.repository.detail.DetailRepository
import com.example.divartask.domain.repository.places.PlacesRepository
import com.example.divartask.domain.repository.posts.PostsRepository
import com.example.divartask.domain.usecase.detail.GetDetailUseCase
import com.example.divartask.domain.usecase.detail.GetDetailUseCaseImp
import com.example.divartask.domain.usecase.places.GetPlacesUseCase
import com.example.divartask.domain.usecase.places.GetPlacesUseCaseImp
import com.example.divartask.domain.usecase.posts.GetPostsUseCase
import com.example.divartask.domain.usecase.posts.GetPostsUseCaseImp
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

    @Provides
    @Singleton
    fun providePostsRepository(apiService: APIService): PostsRepository {
        return PostsRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun providePostsUseCase(repository: PostsRepository): GetPostsUseCase {
        return GetPostsUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(apiService: APIService): DetailRepository {
        return DetailRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideDetailUseCase(repository: DetailRepository): GetDetailUseCase {
        return GetDetailUseCaseImp(repository)
    }
}