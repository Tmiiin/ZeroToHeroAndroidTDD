package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>
    fun add(new: CharSequence)

    fun save(bundle: BundleWrapper.Save)

    fun update(list: List<CharSequence>)
    class Base : ListLiveDataWrapper {

        private val items = mutableListOf<CharSequence>()
        private var data: MutableLiveData<List<CharSequence>> = SingleLiveEvent()
        override fun liveData(): LiveData<List<CharSequence>> {
            return data
        }

        override fun add(new: CharSequence) {
            items.add(new)
            data.value = items.toList()
        }

        override fun save(bundle: BundleWrapper.Save) {
            bundle.save(ArrayList(items))
        }

        override fun update(list: List<CharSequence>) {
            data.value = list
        }

    }
}