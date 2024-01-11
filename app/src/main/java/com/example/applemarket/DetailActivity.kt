package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedData = intent.getParcelableExtra<Items>("data")

        if (receivedData != null) {
            binding.ivDetailPicture.setImageResource(receivedData.image)
            binding.tvDetailDescriptionTitle.text = receivedData.title
            binding.tvDetailDescriptionContext.text = receivedData.context
            binding.tvDetailProfileName.text = receivedData.seller
            binding.tvDetailPrice.text = receivedData.price.toString()
            binding.tvDetailProfileLoca.text = receivedData.loca
        }


        setContentView(binding.root)


        binding.ibDetailBack.setOnClickListener {
            if (isFinishing.not()) finish()
        }
    }
}