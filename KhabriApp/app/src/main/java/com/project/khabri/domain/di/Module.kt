package com.project.khabri.domain.di

import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.project.khabri.domain.local.ArticleDatabase
import com.project.khabri.domain.remote.NewsAPI
import com.project.khabri.domain.repositories.AuthenticationService
import com.project.khabri.domain.repositories.AuthenticationServiceImpl
import com.project.khabri.domain.repositories.NewsRepository
import com.project.khabri.domain.repositories.NewsRepositoryImpl
import com.project.khabri.domain.repositories.SavedRepository
import com.project.pattagobhi.ui.authentication.createAccount.CreateAccountViewModel
import com.project.pattagobhi.ui.authentication.emailVerification.EmailVerificationViewModel
import com.project.khabri.ui.authentication.login.LoginViewModel
import com.project.khabri.domain.repositories.SavedRepositoryImpl
import com.project.pattagobhi.data.remote.HttpRoutes
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val pattagobhiModules = module {
    single {
        FirebaseAuth.getInstance()
    }
    single<AuthenticationService> {
        AuthenticationServiceImpl()
    }
    //Room Database
    single {
        Room.databaseBuilder(androidApplication(), ArticleDatabase::class.java,"article_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single<SavedRepository> {
        SavedRepositoryImpl(dao = get())
    }
    single {
        get<ArticleDatabase>().getArticleDAO()
    }

    single {
        //Now koin can understand and inject these dependencies whenever required
        Retrofit.Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }
    //News repository is actually the interface and NewsRepository is its implementation.
    //This is how we use them
    single<NewsRepository> {
        //Now I can access it directly
        NewsRepositoryImpl(api = get())
    }


    factory {
        CreateAccountViewModel(get(), get())
    }
    factory {
        EmailVerificationViewModel(get(), get())
    }
    factory {
        LoginViewModel(get(), get())
    }
}