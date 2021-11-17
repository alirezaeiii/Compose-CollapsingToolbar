package com.android.sample.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.sample.app.feature.list.ui.Notifier
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Notifier.init(this)
    }
}