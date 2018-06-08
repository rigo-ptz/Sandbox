package com.jollypanda.sandbox.data.sources.network.paging

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
class NetworkState private constructor(
    val status: Satus,
    val msg: String? = null
) {
    companion object {
        val LOADED by lazy { NetworkState(Satus.SUCCESS) }
        val LOADING by lazy { NetworkState(Satus.RUNNING) }
        fun ERROR(msg: String) = NetworkState(
            Satus.FAILED, msg
        )
    }
}

enum class Satus {
    RUNNING,
    SUCCESS,
    FAILED
}