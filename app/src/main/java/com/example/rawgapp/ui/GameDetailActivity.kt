package com.example.rawgapp.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import com.example.rawgapp.AppController
import com.example.rawgapp.R
import com.example.rawgapp.ui.base.BaseActivity
import com.example.rawgapp.ui.viewmodel.GameDetailViewModel
import javax.inject.Inject

class GameDetailActivity : BaseActivity() {

    override val TAG: String get() = GameDetailActivity::class.java.simpleName

    @BindView(R.id.tvGameName)
    lateinit var tvGameName: TextView

    @BindView(R.id.tvGameReleasedDate)
    lateinit var tvGameReleasedDate: TextView

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var gameDetailViewModel: GameDetailViewModel

    var gameId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        (applicationContext as AppController).appComponent.inject(this)

        ButterKnife.bind(this)

        gameId=intent.getIntExtra(GAME_ID,0)

        Log.e(TAG,gameId.toString())

    }

    companion object{
        const val  GAME_ID="GAME_ID"

        fun newIntent(context: Context, id:Int): Intent {
            return Intent(context,GameDetailActivity::class.java).apply {
                putExtra(GAME_ID,id)
            }
        }
    }
}
