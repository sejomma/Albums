package com.jommaa.albums.usecases

import com.jommaa.albums.fakeObject.FakeLocalAlbumsRepositoryWithData
import com.jommaa.albums.fakeObject.FakeLocalAlbumsRepositoryWithoutData
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.fakeObject.FakeNetworkAlbumsRepositoryWithData
import com.jommaa.domain.fakeObject.FakeNetworkAlbumsRepositoryWithoutData
import com.jommaa.domain.usecases.GetAlbumsFromLocalUseCase
import com.jommaa.domain.usecases.GetAlbumsFromNetworkUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetAlbumsListFromUseCaseTest {
    private lateinit var getAlbumsFromNetworkUseCase: GetAlbumsFromNetworkUseCase
    private lateinit var getAlbumsFromLocalUseCase: GetAlbumsFromLocalUseCase

    @Test
    fun `assert the albums returned by the network repo is the same list returned by the use case`() {
        runBlocking {
            getAlbumsFromNetworkUseCase =
                GetAlbumsFromNetworkUseCase(FakeNetworkAlbumsRepositoryWithData())
            val result = getAlbumsFromNetworkUseCase.execute()
            assert(result is DataResult.Success<List<Album>>)
            assert((result as DataResult.Success<List<Album>>).data.size == 2)
        }
    }

    @Test
    fun `assert that network use case return success when repository returns zero albums`() {
        runBlocking {
            getAlbumsFromNetworkUseCase =
                GetAlbumsFromNetworkUseCase(FakeNetworkAlbumsRepositoryWithoutData())
            val result = getAlbumsFromNetworkUseCase.execute()
            assert(result is DataResult.Failure)
            assertNull((result as DataResult.Failure).exp.ex)
            assert(result.exp.message == "No Data available from API")

        }
    }

    @Test
    fun `assert the albums returned by the local repo is the same list returned by the use case`() {
        runBlocking {
            getAlbumsFromLocalUseCase =
                GetAlbumsFromLocalUseCase(FakeLocalAlbumsRepositoryWithData())
            val result = getAlbumsFromLocalUseCase.execute()
            assert(result is DataResult.Success<List<Album>>)
            assert((result as DataResult.Success<List<Album>>).data.size == 1)
        }
    }

    @Test
    fun `assert that local use case return success when repository returns zero albums`() {
        runBlocking {
            getAlbumsFromLocalUseCase =
                GetAlbumsFromLocalUseCase(FakeLocalAlbumsRepositoryWithoutData())
            val result = getAlbumsFromLocalUseCase.execute()
            assert(result is DataResult.Failure)
            assertNull((result as DataResult.Failure).exp.ex)
            assert(result.exp.message == "No data available to display, please make sure you have internet connection and try again")
        }
    }

}