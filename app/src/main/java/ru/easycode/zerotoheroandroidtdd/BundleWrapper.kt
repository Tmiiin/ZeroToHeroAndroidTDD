package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle


interface BundleWrapper {

    interface Mutable : Save, Restore

    interface Save : BundleWrapper {

        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY) ?: arrayListOf()
        }

        companion object {
            private const val KEY = "listItemsKey"
        }
    }

}