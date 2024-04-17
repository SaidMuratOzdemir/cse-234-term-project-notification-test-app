package com.saidmuratozdemir.notificationtestapp.listeners

import android.view.View

interface ItemClickListener {
    fun onClick(objects: Any?)

    fun onLongClick(view: View, objects: Any?)
}

