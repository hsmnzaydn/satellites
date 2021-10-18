package com.hsmnzaydn.satellites.base

data class CoreDataState<out T>(val status: Status, val data: T?,  val code:Int? ,val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): CoreDataState<T> {
            return CoreDataState(Status.SUCCESS, data, null,null)
        }

        fun <T> error(): CoreDataState<T> {
            return CoreDataState(Status.ERROR, null,null, null)
        }

        fun <T> loading(data: T? = null): CoreDataState<T> {
            return CoreDataState(Status.LOADING, data, null,null)
        }
    }
}