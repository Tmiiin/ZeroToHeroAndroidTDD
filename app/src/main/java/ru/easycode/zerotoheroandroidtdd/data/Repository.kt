package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.SimpleResponse

interface Repository {
    suspend fun load(): SimpleResponse

    class Base(val service: SimpleService, val url: String): Repository {

        override suspend fun load(): SimpleResponse = service.fetch(url)

    }
}