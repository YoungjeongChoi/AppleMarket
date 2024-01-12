package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.applemarket.databinding.ActivityDetailBinding
import java.text.DecimalFormat

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
            binding.tvDetailPrice.text = "${DecimalFormat("#,###").format(receivedData.price)}원"
            binding.tvDetailProfileLoca.text = receivedData.loca
            if (receivedData.isLike) {
                binding.ibDetailPriceLike.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                binding.ibDetailPriceLike.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }


        setContentView(binding.root)


        binding.ibDetailBack.setOnClickListener {
            if (isFinishing.not()) finish()
        }

        binding.ibDetailPriceLike.setOnClickListener {
            receivedData!!.isLike = !receivedData.isLike
            if (receivedData.isLike) {
                binding.ibDetailPriceLike.setImageResource(R.drawable.baseline_favorite_24)
                receivedData.like++
                Toast.makeText(this, "리스트에 추가", Toast.LENGTH_SHORT).show()
            } else {
                binding.ibDetailPriceLike.setImageResource(R.drawable.baseline_favorite_border_24)
                receivedData.like--
                Toast.makeText(this, "리스트에서 제거", Toast.LENGTH_SHORT).show()
            }
        }
    }
}