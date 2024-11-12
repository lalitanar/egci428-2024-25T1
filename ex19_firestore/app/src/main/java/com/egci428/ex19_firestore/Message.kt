package com.egci428.ex19_firestore

/*
* Created by Lalita N. on 8/11/24
*/

class Message(val id: String, val message: String, val rating: Int, val timeStamp: String) {
    constructor(): this("", "",0,"")
}