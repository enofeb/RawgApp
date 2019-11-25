package com.example.rawgapp.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.rawgapp.AppController
import com.example.rawgapp.R
import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.base.BaseActivity
import com.example.rawgapp.ui.viewmodel.GameListViewModel
import javax.inject.Inject

class GameListActivity : BaseActivity() {

    override val TAG: String get() = GameListActivity::class.java.simpleName

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var gameListViewModel: GameListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)

        (applicationContext as AppController).appComponent.inject(this)

    }
}
