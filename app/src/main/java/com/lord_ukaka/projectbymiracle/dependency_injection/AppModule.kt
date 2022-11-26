package com.lord_ukaka.projectbymiracle.dependency_injection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lord_ukaka.projectbymiracle.data.remote.AlbumApi
import com.lord_ukaka.projectbymiracle.data.repository.AlbumRepositoryImpl
import com.lord_ukaka.projectbymiracle.domain.models.Validations
import com.lord_ukaka.projectbymiracle.domain.repository.AlbumRepository
import com.lord_ukaka.projectbymiracle.domain.usecases.GetAlbums
import com.lord_ukaka.projectbymiracle.domain.usecases.ValidateEmail
import com.lord_ukaka.projectbymiracle.domain.usecases.ValidatePassword
import com.lord_ukaka.projectbymiracle.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideAlbumApi(): AlbumApi {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(AlbumApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetAlbumsUsecase(repository: AlbumRepository): GetAlbums {
        return GetAlbums(repository)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(
        api: AlbumApi
    ): AlbumRepository {
        return AlbumRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideValidations(): Validations {
        return Validations(
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword()
        )
    }
}