package com.jommaa.albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jommaa.albums.viewmodel.MainViewModel
import com.jommaa.domain.dataresult.CustomException
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.usecases.GetAlbumsFromLocalUseCase
import com.jommaa.domain.usecases.GetAlbumsFromNetworkUseCase
import com.jommaa.domain.usecases.PutAlbumsInLocalDBUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock
    lateinit var fromLocalUseCase:GetAlbumsFromLocalUseCase
    @Mock
    lateinit var fromNetworkUseCase:GetAlbumsFromNetworkUseCase
    @Mock
    lateinit var putAlbumsInLocalDBUseCase: PutAlbumsInLocalDBUseCase

    private lateinit var mainViewModel:MainViewModel
    @Mock
    private lateinit var albumsObserver: Observer<in DataResult<List<Album>>>

    @Before
    fun setUp(){
        mainViewModel = MainViewModel(fromNetworkUseCase,fromLocalUseCase,putAlbumsInLocalDBUseCase)
    }

    @Test
    fun `when fetching results ok then return a list successfully`(){
        val emptyList = arrayListOf<Album>()
        mainViewModel.getAlbums().observeForever(albumsObserver)
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(fromNetworkUseCase.execute()).thenAnswer {
                DataResult.Success(emptyList)
            }
            mainViewModel.fetchAlbums()
            Assert.assertNotNull(mainViewModel.getAlbums().value)

            Assert.assertEquals(
                0,
                (mainViewModel.getAlbums().value as DataResult.Success).data.size
            )
        }
    }

    @Test
    fun `when calling for results then return loading`() {
        testCoroutineRule.runBlockingTest {
            mainViewModel.getAlbums().observeForever(albumsObserver)
            mainViewModel.fetchAlbums()
            Mockito.verify(albumsObserver).onChanged(DataResult.Loading)
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val exception = Mockito.mock(HttpException::class.java)
        testCoroutineRule.runBlockingTest {
            mainViewModel.getAlbums().observeForever(albumsObserver)
            Mockito.`when`(
                fromNetworkUseCase.execute()
            ).thenAnswer {
                DataResult.Failure(CustomException(null,""))
            }
            mainViewModel.fetchAlbums()
            Assert.assertNull(mainViewModel.getAlbums().value)
        }
    }
}