package com.piyawat.android_coinranking.repository.base

import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun<T: Any> safeApiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            throw ApiException(
                response.code().toString()
            )
        }
    }

}

class ApiException(message: String): IOException(message)