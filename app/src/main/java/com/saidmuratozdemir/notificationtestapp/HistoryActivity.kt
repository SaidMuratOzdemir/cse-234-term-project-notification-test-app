package com.saidmuratozdemir.notificationtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityHistoryBinding
import com.saidmuratozdemir.notificationtestapp.listeners.ItemClickListener
import com.saidmuratozdemir.notificationtestapp.model.CardModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeCardList = listOf(
            CardModel(
                "notification 1",
                "Notification 1 Title",
                "Notification 1 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 2",
                "Notification 2 Title",
                "Notification 2 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 3",
                "Notification 3 Title",
                "Notification 3 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 4",
                "Notification 4 Title",
                "Notification 4 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 5",
                "Notification 5 Title",
                "Notification 5 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 6",
                "Notification 6 Title",
                "Notification 6 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 7",
                "Notification 7 Title",
                "Notification 7 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 8",
                "Notification 8 Title",
                "Notification 8 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 9",
                "Notification 9 Title",
                "Notification 9 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 10",
                "Notification 10 Title",
                "Notification 10 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 11",
                "Notification 11 Title",
                "Notification 11 Description",
                R.drawable.history,
                false
            ),
            CardModel(
                "notification 12",
                "Notification 12 Title",
                "Notification 12 Description",
                R.drawable.history,
                false
            )
        )

        val cardAdapter = CardAdapter(itemClickListenner)
        cardAdapter.setList(homeCardList)

        binding.recyclerView.adapter = cardAdapter

        binding.buttonDeleteAll.setOnClickListener {
            cardAdapter.setList(emptyList())
        }

    }


    private val itemClickListenner = object : ItemClickListener {
        override fun onClick(objects: Any?) {

        }

        override fun onLongClick(view: View, objects: Any?) {

        }


    }
}