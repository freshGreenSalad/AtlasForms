package com.example.atlasforms.Di


import com.example.atlasforms.features.newForm.data.FormRepository
import com.example.atlasforms.features.newForm.data.FormRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryDI {

    @Binds
    fun provideSignInRepository(signinRepo: FormRepository): FormRepositoryInterface

    /*@Binds
    fun provideEmailRepository(emailRepo: EmailRepository): EmailRepositoryInterface*/
}