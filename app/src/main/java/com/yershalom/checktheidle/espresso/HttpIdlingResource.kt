package com.yershalom.checktheidle.espresso

import android.support.annotation.CheckResult
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import okhttp3.Dispatcher
import okhttp3.OkHttpClient


class HttpIdlingResource(private val name: String, private val dispatcher: Dispatcher) : IdlingResource {
    @Volatile
    internal var callback: IdlingResource.ResourceCallback? = null

    init {
        dispatcher.setIdleCallback {
            val callback = this@HttpIdlingResource.callback
            callback?.onTransitionToIdle()
        }
    }

    override fun getName(): String {
        return name
    }

    override fun isIdleNow(): Boolean {
        return dispatcher.runningCallsCount() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    companion object {
        /**
         * Create a new [IdlingResource] from `client` as `name`. You must register
         * this instance using `Espresso.registerIdlingResources`.
         */
        @CheckResult
        // Extra guards as a library.
        fun create(name: String, client: OkHttpClient): HttpIdlingResource {
            return HttpIdlingResource(name, client.dispatcher())
        }

        fun register(client: OkHttpClient) {
            IdlingRegistry.getInstance().register(create("okHttp", client))
        }
    }
}