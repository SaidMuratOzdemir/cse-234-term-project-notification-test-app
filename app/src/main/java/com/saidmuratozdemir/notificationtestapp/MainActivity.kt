package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityMainBinding
import com.saidmuratozdemir.notificationtestapp.listeners.ItemClickListener
import com.saidmuratozdemir.notificationtestapp.model.CardModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeCardList = listOf(
            CardModel(
                "firebase_config",
                "Enter Firebase Configurations",
                "Enter Firebase configurations or import google-services.json file",
                R.drawable.settings,
                false
            ),
            CardModel(
                "notification_permission",
                "Check Notification Permission",
                "Check if notification permission is granted",
                R.drawable.check,
                false
            ),
            CardModel(
                "see_token",
                "See Device Token",
                "See Firebase token that is generated for your device",
                R.drawable.phone,
                false
            ),
            CardModel(
                "notification_history",
                "See Notification History",
                "See notifications that has been sent to your device",
                R.drawable.history,
                false
            ),
            CardModel(
                "language",
                "Language",
                "Select language",
                R.drawable.language,
                false
            ),
            CardModel(
                "change_theme",
                "Change Theme",
                "Switch between light and dark theme",
                R.drawable.darkmode,
                true
            ),
            CardModel(
                "about_us",
                "About Us",
                "See information about us",
                R.drawable.info,
                false
            )
        )

        val cardAdapter = CardAdapter(itemClickListener)
        cardAdapter.setList(homeCardList)
        binding.recyclerView.adapter = cardAdapter

    }

    private val itemClickListener = object : ItemClickListener {
        override fun onClick(objects: Any?) {
            val cardModel = objects as CardModel

            when (cardModel.id) {
                "firebase_config" -> {
                    val intent = Intent(this@MainActivity, FirebaseConfigActivity::class.java)
                    startActivity(intent)

                }

                "notification_permission" -> {
                    // TODO: write
                }

                "see_token" -> {
                    val intent = Intent(this@MainActivity, TokenActivity::class.java)
                    intent.putExtra(
                        "token",
                        "d2nZK1JLTsuijPN__h9IwS:APA91bGTJFrjYViIIWkUvEoKcoT4XZavxXS3YObcjKghiP8olWHaNJnwJBzrYwrSmKx1tEYPOmkGr6ytBkTA3bcf6nbIaNx21dvnQ4GM2_XxFEK297Ze_xzRJJRn0Bh-12cK8YxyaHqK"
                    )
                    startActivity(intent)
                }

                "notification_history" -> {
                    val intent = Intent(this@MainActivity, HistoryActivity::class.java)
                    startActivity(intent)
                }
                "language" -> {

                    val checkedItem = intArrayOf(0)

                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

                    alertDialog.setIcon(R.drawable.language)

                    alertDialog.setTitle("Choose a Language")

                    val listItems = arrayOf("English", "Turkish", "Spanish")

                    alertDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
                        checkedItem[0] = which
                        dialog.dismiss()
                    }

                    alertDialog.setNegativeButton("Cancel") { _, _ -> }

                    val customAlertDialog: AlertDialog = alertDialog.create()

                    customAlertDialog.show()
                }

                "about_us" -> {
                    val intent = Intent(this@MainActivity, AboutUsActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        override fun onLongClick(view: View, objects: Any?) {

        }
    }
}