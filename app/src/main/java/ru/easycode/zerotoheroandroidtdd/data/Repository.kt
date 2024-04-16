package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.SimpleResponse
import java.net.UnknownHostException

interface Repository {
    suspend fun load(): LoadResult

    class Base(val service: SimpleService, val url: String): Repository {

        override suspend fun load(): LoadResult {
            return try {
                val response = service.fetch(url)
                LoadResult.Success(SimpleResponse(response.text))
            } catch (e: UnknownHostException) {
                LoadResult.Error(true)
            } catch (e: Exception) {
                LoadResult.Error(false)
            }
        }

    }
}