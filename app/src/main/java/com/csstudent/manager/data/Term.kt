package com.csstudent.manager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terms")
data class Term(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val term: String,
    val definition: String,
    val category: String
)

enum class TermCategory(val displayName: String) {
    ALL("전체"),
    ALGORITHM("알고리즘"),
    DATA_STRUCTURE("자료구조"),
    NETWORK("네트워크"),
    OS("운영체제"),
    DATABASE("데이터베이스"),
    PROGRAMMING("프로그래밍"),
    WEB("웹"),
    ETC("기타");

    companion object {
        fun fromDisplayName(displayName: String): TermCategory {
            return values().find { it.displayName == displayName } ?: ETC
        }
    }
}
