package org.spisoft.sync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_sync_folder_setting.*

class SyncFolderSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sync_folder_setting)
        setSupportActionBar(toolbar)

    }
    companion object {
        val SYNC_ITEM = "sync_item"
    }
}
