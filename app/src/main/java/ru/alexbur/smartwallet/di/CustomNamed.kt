package ru.alexbur.smartwallet.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AllRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AllClient
