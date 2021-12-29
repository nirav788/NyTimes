package com.upwork.nytimes.ApiClient

import android.util.Log
import com.androidnetworking.common.Priority
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import org.json.JSONObject
import java.io.File
import java.util.*

open class ApiMethod {
    
    fun RxPostJsonRequest(
        path: String,
        jsonObject: JSONObject,
        header: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $header")
        Log.e("API", "Request : $jsonObject")
        return Rx2AndroidNetworking.post(path)
            .addHeaders(header)
            .addJSONObjectBody(jsonObject)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostJsonRequest(
        path: String,
        jsonObject: JSONObject,
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Request : $jsonObject")
        return Rx2AndroidNetworking.post(path)
            .addJSONObjectBody(jsonObject)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(path: String): Rx2ANRequest {
        Log.e("API", "Url : $path")
        return Rx2AndroidNetworking.post(path)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostHeaderRequest(path: String, headers: HashMap<String, String>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        return Rx2AndroidNetworking.post(path)
            .addHeaders(headers)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(path: String, request: HashMap<String, String>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.post(path) //                .addHeaders(headers)
            .addBodyParameter(request)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxDeleteRequest(path: String, headers: HashMap<String, String>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        return Rx2AndroidNetworking.delete(path)
            .addHeaders(headers)
            .setTag("DeleteRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPutRequest(path: String, request: HashMap<String, String>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.put(path) //                .addHeaders(headers)
            .addBodyParameter(request)
            .setTag("PutRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxDeleteRequest(path: String): Rx2ANRequest {
        Log.e("API", "Url : $path")
        return Rx2AndroidNetworking.delete(path) //                .addHeaders(headers)
            .setTag("DeleteRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostAllDynamicRequest(path: String, request: HashMap<Any, Any>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.post(path)
            .addBodyParameter(request)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostDynamicRequest(path: String, request: HashMap<String, Any>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.post(path) //                .addHeaders(headers)
            .addBodyParameter(request)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostDynamicRequest(
        path: String, request: HashMap<String, Any>,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.post(path)
            .addHeaders(headers)
            .addBodyParameter(request)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(
        path: String,
        proPic: File,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : ${proPic.name}")
        return Rx2AndroidNetworking.upload(path)
            .addHeaders(headers)
            .addMultipartFile("", proPic, "multipart/form-data")
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(
        path: String,
        request: HashMap<String, Any>,
        keyProfile: String,
        proPic: File,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : $request"
            .plus("\nKeyProfile : $keyProfile -> ${proPic.name}"))
        return Rx2AndroidNetworking.upload(path)
            .addHeaders(headers)
            .addMultipartFile(keyProfile, proPic, "multipart/form-data")
            .addMultipartParameter(request, "multipart/form-data")
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(
        path: String,
        request: HashMap<String, String>,
        keyFront: String,
        aadharFrontFile: File,
        keyBack: String,
        aadharBackFile: File,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : $request"
            .plus("\nKeyFront : ${aadharFrontFile.name}")
            .plus("\nKeyBack : ${aadharBackFile.name}"))
        return Rx2AndroidNetworking.upload(path)
            .addHeaders(headers)
            .addMultipartParameter(request, "multipart/form-data")
            .addMultipartFile(keyFront, aadharFrontFile, "multipart/form-data")
            .addMultipartFile(keyBack, aadharBackFile, "multipart/form-data")
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(
        path: String,
        request: HashMap<String, String>,
        keyFront: String,
        aadharFrontFile: File,
        keyBack: String,
        aadharBackFile: File,
        panCard: String,
        panCardFile: File,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : $request"
            .plus("\nKeyFront : ${aadharFrontFile.name}")
            .plus("\nKeyBack : ${aadharBackFile.name}")
            .plus("\nPanCard : ${panCardFile.name}"))
        return Rx2AndroidNetworking.upload(path)
            .addHeaders(headers)
            .addMultipartParameter(request, "multipart/form-data")
            .addMultipartFile(keyFront, aadharFrontFile, "multipart/form-data")
            .addMultipartFile(keyBack, aadharBackFile, "multipart/form-data")
            .addMultipartFile(panCard, panCardFile, "multipart/form-data")
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxPostRequest(
        path: String,
        request: HashMap<String, String>,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.post(path)
            .addHeaders(headers)
            .addBodyParameter(request)
            .setTag("PostRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxGetRequest(path: String, request: HashMap<String, Any>): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.get(path)
            .addQueryParameter(request)
            .setTag("GetRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxGetRequest(path: String): Rx2ANRequest {
        Log.e("API", "Url : $path")
        return Rx2AndroidNetworking.get(path)
            .setTag("GetRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxGetRequest(
        path: String,
        request: HashMap<String, String>,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "Header : $headers")
        Log.e("API", "Request : $request")
        return Rx2AndroidNetworking.get(path)
            .addHeaders(headers)
            .addQueryParameter(request)
            .setTag("GetRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun RxDownloadRequest(path: String, dirPath: String, fileName: String): Rx2ANRequest {
        Log.e("API", "Url : $path")
        Log.e("API", "DirPath : $dirPath")
        Log.e("API", "FileName : $fileName")
        return Rx2AndroidNetworking.download(path, dirPath, fileName)
            .setTag("DownloadRequest")
            .setPriority(Priority.HIGH)
            .build()
    }
//    }
}