package com.csstudent.manager.data

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @SerializedName("login")
    val login: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("public_repos")
    val publicRepos: Int,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String?
)
