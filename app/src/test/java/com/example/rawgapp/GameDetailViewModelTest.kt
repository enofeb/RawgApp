package com.example.rawgapp

import androidx.annotation.NonNull
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.viewmodel.GameDetailViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import org.junit.BeforeClass
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class GameDetailViewModelTest{

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var gameDetailViewModel : GameDetailViewModel

    @Mock
    lateinit var gameRepository: GameRepository

    @Mock
    lateinit var observer: Observer<GameDetailEntity>


    companion object{
        @JvmStatic
        @BeforeClass
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker( Executor { it.run() },true)
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        gameDetailViewModel= GameDetailViewModel(gameRepository)
    }

    @Test
    fun testViewModel(){

        val EXPECTED_RESULT= GameDetailEntity(28,"red-dead-redemption-2")

        Mockito. `when`(gameRepository.getGameDetail(28))
            .thenReturn(Single.just(EXPECTED_RESULT))


        gameDetailViewModel.getGameLD().observeForever(observer)
        gameDetailViewModel.loadGameInfo(28)

        assert(gameDetailViewModel.getGameLD().value==EXPECTED_RESULT)

    }
}