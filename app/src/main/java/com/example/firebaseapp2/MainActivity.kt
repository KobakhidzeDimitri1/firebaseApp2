package com.example.firebaseapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseapp2.databinding.ActivityMainBinding
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var homeRVAdapter : HomeRecyclerViewAdapter
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseRef = FirebaseDatabase.getInstance().reference.child("emails")

        binding.apply {
            homeRVAdapter = HomeRecyclerViewAdapter(getData())
            homeRV.adapter = homeRVAdapter
            homeRV.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        fetchEmailsFromFirebase()
    }

    private fun fetchEmailsFromFirebase() {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataList = mutableListOf<Email>()
                for (snapshot in dataSnapshot.children) {
                    val author = snapshot.child("author").getValue(String::class.java)
                    val subject = snapshot.child("subject").getValue(String::class.java)
                    val content = snapshot.child("content").getValue(String::class.java)
                    val email = Email(author, subject, content)
                    dataList.add(email)
                }
                homeRVAdapter.setData(dataList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        }

        databaseRef.addValueEventListener(valueEventListener)
    }

    private fun getData(): MutableList<Email> {
        val dataList = mutableListOf<Email>()

        return dataList
    }

//    private lateinit var binding : ActivityMainBinding
//
//    private lateinit var homeRVAdapter : HomeRecyclerViewAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.apply {
//            homeRVAdapter = HomeRecyclerViewAdapter(getData())
//            homeRV.adapter = homeRVAdapter
//            homeRV.layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//    }
//
//    private fun getData(): MutableList<Email> {
//        var dataList = ArrayList<Email>()
//        dataList.add(Email("author 1", "subject 1", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 2", "subject 2", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 3", "subject 3", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 4", "subject 4", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 5", "subject 5", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 6", "subject 6", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 7", "subject 7", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 8", "subject 8", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 9", "subject 9", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 10", "subject 10", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 11", "subject 11", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 12", "subject 12", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 13", "subject 13", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 14", "subject 14", "content 1 content 1 content 1 content 1 content 1"))
//        dataList.add(Email("author 15", "subject 15", "content 1 content 1 content 1 content 1 content 1"))
//        return dataList
//    }


}