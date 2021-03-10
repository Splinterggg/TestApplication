package cvdevelopers.takehome.dagger

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cvdevelopers.takehome.api.ClientsDataSource
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.data.ClientDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = ClientDatabase.getDatabase(appContext)

    @Provides
    fun provideTaskDao(db: ClientDatabase) = db.clientDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(RandomUserApiEndpoint.SERVER)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideClientsDataSource(randomUserApiEndpoint: RandomUserApiEndpoint) = ClientsDataSource(randomUserApiEndpoint)


    @Provides
    @Singleton
    fun provideClientsApi(retrofit: Retrofit): RandomUserApiEndpoint =
        retrofit.create(RandomUserApiEndpoint::class.java)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope