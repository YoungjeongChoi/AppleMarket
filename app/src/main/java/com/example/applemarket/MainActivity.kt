package com.example.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(binding.root)

//        val data = readExcelAssets()
        val dataList = mutableListOf<Items>()

        dataList.add(Items(R.drawable.sample1,
            getString(R.string.title_1),
            getString(R.string.context_1), getString(R.string.seller_1), 1000,
            getString(R.string.locate_1), 13, 25))
        dataList.add(Items(R.drawable.sample2,
            getString(R.string.title_2),
            getString(R.string.context_2), getString(R.string.seller_2), 20000,
            getString(R.string.locate_2), 8, 28))
        dataList.add(Items(R.drawable.sample3,
            getString(R.string.title_3),
            getString(R.string.context_3), getString(R.string.seller_3), 10000,
            getString(R.string.locate_3), 23,5))
        dataList.add(Items(R.drawable.sample4,
            getString(R.string.title_4),
            getString(R.string.context_4), getString(R.string.seller_4), 10000,
            getString(R.string.location_4), 14, 17))
        dataList.add(Items(R.drawable.sample5,
            getString(R.string.title_5),
            getString(R.string.context_5), getString(R.string.seller_5), 150000,
            getString(R.string.location_5), 22, 9))
        dataList.add(Items(R.drawable.sample6,
            getString(R.string.title_6),
            getString(R.string.context_6),
            getString(R.string.seller_6), 50000, getString(R.string.location_6), 25, 16))
        dataList.add(Items(R.drawable.sample7,
            getString(R.string.title_7),
            getString(R.string.context_7), getString(R.string.seller_7), 150000,
            getString(R.string.location_7), 142, 54))
        dataList.add(Items(R.drawable.sample8,
            getString(R.string.title_8),
            getString(R.string.context_8), getString(R.string.seller_8), 180000,
            getString(R.string.location_8), 31, 7))
        dataList.add(Items(R.drawable.sample9,
            getString(R.string.title_9),
            getString(R.string.context_9), getString(R.string.seller_9), 30000,
            getString(R.string.location_9), 7, 28))
        dataList.add(Items(R.drawable.sample10,
            getString(R.string.title_10),
            getString(R.string.context_10), getString(R.string.seller_10), 190000,
            getString(R.string.location_10), 40, 6))


        val adapter = ItemAdapter(dataList)

        binding.rvMainList.adapter = adapter

        binding.rvMainList.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : ItemAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("data", dataList[position])
                }
                startActivity(intent)
            }


        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showDialog()
            }
        }

//        this.onBackPressedDispatcher.addCallback(this, callback)
        onBackPressedDispatcher.addCallback(this, callback)

//        onBackPressed()
//        onBackInvokedDispatcher

        binding.clMainBarLocaBtn.setOnClickListener {
            //동네 선택
        }

        binding.ibMainNoti.setOnClickListener {
            notification()
        }
        
        binding.rvMainList.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->
            val fadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
            val fadeOut = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)
            if (scrollY > oldScrollY) {
                binding.fabMainTop.startAnimation(fadeIn)
                binding.fabMainTop.isVisible = true
            }
            if (binding.rvMainList.canScrollVertically(-1).not()) {
                binding.fabMainTop.startAnimation(fadeOut)
                binding.fabMainTop.isVisible = false
            }
        }

        binding.fabMainTop.setOnClickListener {
            binding.rvMainList.smoothScrollToPosition(0)
        }



    }

//    fun readExcelAssets() : MutableList<Items> {
//
//        val dataList = mutableListOf<Items>()
//
//        try {
//            val dataInput: InputStream
//
//            val assetManager = assets
//
//            dataInput = assetManager.open("dummy_data.xls")
//
//            val fileSystem = POIFSFileSystem(dataInput)
//
//            val workbook = HSSFWorkbookFactory.create(fileSystem)
////            val workbook = HSSFWorkbook(fileSystem)
//
//            val sheet = workbook.getSheetAt(0)
//
//            val rowIterator = sheet.rowIterator()
//            var rowNum = 0
//
//            while (rowIterator.hasNext()) {
//
//                val myRow = rowIterator.next() as XSSFRow
//
//                if (rowNum != 0) {
//                    val cellIterator = myRow.cellIterator()
//                    var colNum = 1
//
//                    var image = ""
//                    var title = ""
//                    var context = ""
//                    var seller = ""
//                    var price = ""
//                    var loca = ""
//                    var like = ""
//                    var chat = ""
//
//                    while (cellIterator.hasNext()) {
//                        val myCell = cellIterator.next() as XSSFCell
//                        when (colNum) {
//                            1 -> {
//                                image = "R.drawable.${myCell.toString()}"
//                            }
//                            2 -> {
//                                title = myCell.toString()
//                            }
//                            3 -> {
//                                context = myCell.toString()
//                            }
//                            4 -> {
//                                seller = myCell.toString()
//                            }
//                            5 -> {
//                                price = myCell.toString()
//                            }
//                            6 -> {
//                                loca = myCell.toString()
//                            }
//                            7 -> {
//                                like = myCell.toString()
//                            }
//                            8 -> {
//                                chat = myCell.toString()
//                            }
//                        }
//                        colNum++
//                        dataList.add(Items(image.toInt(), title, context, seller, price.toInt(), loca, like.toInt(), chat.toInt() ))
//
//                    }
//                }
//                rowNum++


//                for (i in 0..10) {
//                    var oneDataList = mutableListOf<String>()
//                    var currentCell = myRow.getCell(colNum) as HSSFRow
//                    Log.d("excel", "cell = $currentCell")
//                    when (colNum) {
//                        1 -> {
//                            oneDataList.add("R.drawable.${currentCell.toString()}")
//                        }
//                        else -> {
//                            oneDataList.add(currentCell.toString())
//                        }
//
//                    }
//                    dataList.add(Items(oneDataList[0].toInt(), oneDataList[1],oneDataList[2], oneDataList[3], oneDataList[4].toInt(), oneDataList[5], oneDataList[6].toInt(), oneDataList[7].toInt() ))
//                    colNum++
//                }

//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        Log.d("excel", "datalist = ${dataList.toString()}")
//        return dataList
//    }

    fun showDialog() {
        val exitDialog = DialogExitFragment()
        exitDialog.show(supportFragmentManager, "Dialog Fragment")
    }

//    override fun onDialogPositiveClick(dialog: DialogFragment) {
//        finish()
//    }

//    override fun onDialogNegativeClick(dialog: DialogFragment) {
//    }


    private fun notification() {

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "default"
            val channelName = "default notification"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "마켓 알림"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }

            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)

        } else {
            builder = NotificationCompat.Builder(this)
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.baseline_notifications_none_24)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        builder.run {
            setSmallIcon(R.drawable.baseline_notifications_none_24)
            setWhen(System.currentTimeMillis())
            setContentTitle("알림")
            setContentText("알림 내용")
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("긴텍스트 내용입니다")
            )
            setLargeIcon(bitmap)
//            setStyle(NotificationCompat.BigPictureStyle()
//                    .bigPicture(bitmap)
//                    .bigLargeIcon(null))  // hide largeIcon while expanding
            addAction(R.mipmap.ic_launcher, "Action", pendingIntent)
        }


        manager.notify(11, builder.build())
    }


}




//private fun showNotification() {
//    val builder = NotificationCompat.Builder(this, channelID)
//        .setSmallIcon(R.mipmap.ic_launcher)
//        .setContentTitle("title")
//        .setContentText("notification text")
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//    NotificationManagerCompat.from(this).notify(myNotiId, builder.build())
//}