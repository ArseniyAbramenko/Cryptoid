package com.arseniy.cryptoid.domain.common

data class ResultWrapper<T>(val data: T, val throwable: Throwable? = null)
