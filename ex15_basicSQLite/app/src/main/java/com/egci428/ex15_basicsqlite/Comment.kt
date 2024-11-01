package com.egci428.ex15_basicsqlite

/*
* Created by Lalita N. on 1/11/24
*/

class Comment {
    var id: Long = 0
    var message: String = ""

    override fun toString(): String {
        return message.toString()
    }
}