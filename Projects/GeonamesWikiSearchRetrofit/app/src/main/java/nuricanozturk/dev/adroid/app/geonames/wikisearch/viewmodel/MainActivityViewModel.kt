package nuricanozturk.dev.adroid.app.geonames.wikisearch.viewmodel

import nuricanozturk.dev.adroid.app.geonames.wikisearch.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity : MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleSearchButtonClicked() = mWeakReference.get()?.handleSearchButtonClicked()

    fun handleSavedWikiInfosButtonClicked() = mWeakReference.get()?.    handleSavedWikiInfosButtonClicked()
}