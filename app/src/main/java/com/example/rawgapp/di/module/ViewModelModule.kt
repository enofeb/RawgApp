package com.example.rawgapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rawgapp.di.ViewModelKey
import com.example.rawgapp.factory.ViewModelFactory
import com.example.rawgapp.ui.viewmodel.GameListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GameListViewModel::class)
    protected abstract fun gameListViewModel(gameListViewModel: GameListViewModel):ViewModel
}