package com.hsmnzaydn.satellites.base

import com.hsmnzaydn.satellites.utils.event
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector


public inline fun CoreDataState<out Any?>.managementUI(viewModel: BaseViewModel) {
    if(this.status == CoreDataState.Status.LOADING){
        viewModel.showLoading()
    }else{
        when(this.code){
            200 -> {
                viewModel?.hideLoading()

            }
            else -> {
                viewModel?.hideLoading()
                viewModel?.errorHandle?.value =this.message?.event()

            }
        }
    }

}


@OptIn(InternalCoroutinesApi::class)
public suspend inline fun <T> Flow<T>.collectUI(viewModel: BaseViewModel, crossinline action: suspend (value: T) -> Unit ): Unit =
    collect(object : FlowCollector<T> {

        override suspend fun emit(value: T) {
            (value as CoreDataState<*>).managementUI(viewModel)
            return action(value)
        }
    })