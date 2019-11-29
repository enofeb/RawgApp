package com.example.rawgapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.rawgapp.AppController
import com.example.rawgapp.R
import com.example.rawgapp.ui.base.BaseActivity
import com.example.rawgapp.ui.viewmodel.GameDetailViewModel
import javax.inject.Inject
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.databinding.ActivityGameDetailBinding

class GameDetailActivity : BaseActivity() {

    override val TAG: String get() = GameDetailActivity::class.java.simpleName

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var gameDetailViewModel: GameDetailViewModel
    private lateinit var binding: ActivityGameDetailBinding

    var gameId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        (applicationContext as AppController).appComponent.inject(this)

        gameId = intent.getIntExtra(GAME_ID, 0)

        initViewModel(gameId)

    }

    private fun initView(gameEntity: GameDetailEntity){
         binding = DataBindingUtil.setContentView(this, R.layout.activity_game_detail)
            binding.gamedetail=gameEntity
    }

    private fun initViewModel(gameId:Int) {
        gameDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameDetailViewModel::class.java)

        gameDetailViewModel.loadGameInfo(gameId)

        gameDetailViewModel.getGameLD().observe(this, Observer {
            initView(it)
        })

    }

    companion object {
        const val GAME_ID = "GAME_ID"

        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, GameDetailActivity::class.java).apply {
                putExtra(GAME_ID, id)
            }
        }
    }
}
