package com.upwork.nytimes.Base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.androidnetworking.error.ANError
import com.google.gson.Gson
import com.upwork.nytimes.ApiClient.ApiClient
import com.upwork.nytimes.ApiClient.ResponseCode
import com.upwork.nytimes.R
import com.upwork.nytimes.Utills.checkInternetConnection
import com.upwork.nytimes.Utills.hideDialog
import com.upwork.nytimes.Utills.showDialog
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import org.json.JSONObject

abstract class AppBaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T
    lateinit var api: ApiClient

    abstract fun setViewBinding(): T
    abstract fun initView()
    abstract fun initOnClick()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setViewBinding()
        setContentView(binding.root)
        api = ApiClient()
        initView()
        initOnClick()
    }


    fun <T> callApi(
        observable: Observable<T>,
        showLoader: Boolean = true,
        responseBlock: (T) -> Unit
    ) {
        if (!checkInternetConnection()) {
            return
        }

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    if (showLoader) {
                        showDialog()
                    }
                }

                override fun onNext(response: T) {
                    Log.e("API", "Success : ${Gson().toJson(response)}")
                    responseBlock(response)
                }

                override fun onError(e: Throwable) {
                    if (showLoader) {
                        hideDialog()
                    }

                    onResponseFailure(e)
                }

                override fun onComplete() {
                    if (showLoader) {
                        hideDialog()
                    }
                }
            })
    }

    fun <T> callApiError(
        observable: Observable<T>,
        showLoader: Boolean = true,
        responseBlock: (T) -> Unit,
        responseError: () -> Unit
    ) {
        if (!checkInternetConnection()) {
            return
        }

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    if (showLoader) {
                        showDialog()
                    }
                }

                override fun onNext(response: T) {
                    Log.e("API", "Success : ${Gson().toJson(response)}")
                    responseBlock(response)
                }

                override fun onError(e: Throwable) {
                    if (showLoader) {
                        hideDialog()
                    }

                    responseError()
                    onResponseFailure(e)
                }

                override fun onComplete() {
                    if (showLoader) {
                        hideDialog()
                    }
                }
            })
    }

    fun <T> callApiErrorCode(
        observable: Observable<T>,
        showLoader: Boolean = true,
        responseBlock: (T) -> Unit,
        responseError: (Int, String) -> Unit
    ) {
        if (!checkInternetConnection()) {
            return
        }

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    if (showLoader) {
                        showDialog()
                    }
                }

                override fun onNext(response: T) {
                    Log.e("API", "Success : ${Gson().toJson(response)}")
                    responseBlock(response)
                }

                override fun onError(e: Throwable) {
                    if (showLoader) {
                        hideDialog()
                    }

                    var error = e as (ANError)
                    responseError(error.errorCode, (error.errorBody ?: "").replace("\"", ""))
                }

                override fun onComplete() {
                    if (showLoader) {
                        hideDialog()
                    }
                }
            })
    }


    private fun onResponseFailure(throwable: Throwable) {
        var error = throwable as (ANError)
        Log.e("API", "Error : ${error.message.toString()}")
        Log.e("API", "Error : ${error.errorCode}")
        handleResponseError(error)
        /*when (error.) {
            is HttpException -> {
                handleResponseError(throwable)
            }
            is ConnectException -> {
                sneakerError(getString(R.string.msg_no_internet))
            }
            is SocketTimeoutException -> {
                sneakerError(getString(R.string.time_out))
            }
        }*/
    }

    private fun handleResponseError(throwable: ANError) {
        when (throwable.errorCode) {
            ResponseCode.InValidateData.code -> {
                val errorRawData = throwable.errorBody
                if (!errorRawData.isNullOrEmpty()) {
                    if (errorRawData.contains("{") && errorRawData.contains("msg")) {
                        errorDialog(
                            JSONObject(errorRawData).getString("msg"),
                            getString(R.string.alert)
                        )
                    } else {
                        errorDialog(errorRawData.toString())
                    }
                }
            }
            ResponseCode.Unauthenticated.code -> {
//                onAuthFail()
                val errorRawData = throwable.errorBody
                if (!errorRawData.isNullOrEmpty()) {
                    if (errorRawData.contains("{") && errorRawData.contains("msg")) {
                        alert(JSONObject(errorRawData).getString("msg"), getString(R.string.alert)) {
                            okButton { }
                        }.show()
                    } else {
                        errorDialog(errorRawData.toString())
                    }
                } else {

                }
            }
            ResponseCode.ForceUpdate.code -> {

            }
            ResponseCode.ServerError.code -> errorDialog(getString(R.string.internal_server_error))
            ResponseCode.NotFound.code -> errorDialog(getString(R.string.page_not_found))
            ResponseCode.BadRequest.code,
            ResponseCode.Unauthorized.code,
            ResponseCode.RequestTimeOut.code,
            ResponseCode.Conflict.code,
            ResponseCode.Blocked.code -> {
                val errorRawData = throwable.errorBody
                if (!errorRawData.isNullOrEmpty()) {
                    if (errorRawData.contains("{") && errorRawData.contains("msg")) {
                        errorDialog(JSONObject(errorRawData).getString("msg"))
                    } else {
                        errorDialog(errorRawData.toString())
                    }
                }
            }
        }
    }

    private fun errorDialog(optString: String?, title: String = getString(R.string.app_name)) {
        optString?.let {
            alert(it, title) { okButton { } }.build().show()
        }
    }

    fun sneakerError(message: String) {
        toast(message)
    }


}