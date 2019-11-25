package com.example.rawgapp.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.rawgapp.AppController
import com.example.rawgapp.R
import com.example.rawgapp.ui.adapter.GameAdapter
import com.example.rawgapp.ui.base.BaseActivity
import com.example.rawgapp.ui.viewmodel.GameListViewModel
import javax.inject.Inject

class GameListActivity : BaseActivity() {

    override val TAG: String get() = GameListActivity::class.java.simpleName

    @BindView(R.id.rvGameList)
    lateinit var rvMonsterList: RecyclerView

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var gameListViewModel: GameListViewModel

    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)
        (applicationContext as AppController).appComponent.inject(this)
        ButterKnife.bind(this)

        initView()
        initViewModel()

    }

    private fun initView() {
        gameAdapter = GameAdapter(applicationContext)

        rvMonsterList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = gameAdapter
        }

    }

    private fun initViewModel() {
        gameListViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GameListViewModel::class.java)

        gameListViewModel.loadGames()

        gameListViewModel.getGameList().observe(this, Observer {
            gameAdapter.updateGameList(it!!)
        })
    }
}
