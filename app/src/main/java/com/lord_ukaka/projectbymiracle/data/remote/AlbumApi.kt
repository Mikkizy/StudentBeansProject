package com.lord_ukaka.projectbymiracle.data.remote

import com.lord_ukaka.projectbymiracle.data.remote.data_transfer_object.AlbumInfoDTO
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {

    @GET("/photos")
    suspend fun getAlbums() : Response<List<AlbumInfoDTO>>
}