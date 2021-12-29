package com.upwork.nytimes.ApiClient

import io.reactivex.Observable

class ApiClient : ApiMethod() {

    var BASE_URL : String = "http://api.nytimes.com/svc/mostpopular/v2/viewed/7.json"

    fun getMostPopularList(request: HashMap<String, Any>): Observable<MostPopularResponse> {
        return RxGetRequest(
            BASE_URL,
            request
        ).getObjectObservable(MostPopularResponse::class.java)
    }

}