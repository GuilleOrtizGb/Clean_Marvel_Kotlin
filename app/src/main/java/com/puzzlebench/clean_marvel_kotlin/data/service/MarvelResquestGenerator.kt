package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.BuildConfig
import com.puzzlebench.clean_marvel_kotlin.Utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date


class MarvelResquestGenerator {
    companion object {
        val MD5 = "MD5"
    }
    private val PRIVATE_API_KEY_ARG = "hash"
    private val PUBLIC_API_KEY_ARG = "apikey"
    private val ORDER_BY = "orderBy"
    private val ORDER_BY_TYPE = "-modified"
    private val TS = "ts"
    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val defaultRequest = chain.request()
        val timestamp = Date().time

        val hash = createMD5Hash(timestamp.toString() + BuildConfig.PRIVATE_API_KEY_VALUE +
                BuildConfig.PUBLIC_API_KEY_VALUE)

        val defaulthttpUrl = defaultRequest.url()
        val httpUrl = defaulthttpUrl.newBuilder()
                .addQueryParameter(ORDER_BY, ORDER_BY_TYPE)
                .addQueryParameter(TS, timestamp.toString())
                .addQueryParameter(PUBLIC_API_KEY_ARG, BuildConfig.PUBLIC_API_KEY_VALUE)
                .addQueryParameter(PRIVATE_API_KEY_ARG, hash)
                .build()

        val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

        chain.proceed(requestBuilder.build())
    }

    private val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    private fun createMD5Hash(stringToHash: String): String {
        try {
            val digest = MessageDigest.getInstance(MD5)
            digest.update(stringToHash.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0" + h
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return Constant.EMPTY_STRING
    }
}