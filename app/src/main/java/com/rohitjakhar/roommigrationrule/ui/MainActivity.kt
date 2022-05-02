package com.rohitjakhar.roommigrationrule.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rohitjakhar.roommigrationrule.data.local.AppDatabase
import com.rohitjakhar.roommigrationrule.data.model.UserProfileEntity
import com.rohitjakhar.roommigrationrule.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSubmitUser.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(this@MainActivity).appDao().insertUser(
                        UserProfileEntity(
                            userId = Random.nextLong(),
                            binding.inputName.editText?.text.toString(),
                            20
                        )
                    )
                }
            }
        }
    }
}
