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
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.ss.usermodel.WorkbookFactory

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(binding.root)

//        val data = Items()
        val dataList = mutableListOf<Items>()

        dataList.add(Items(R.drawable.sample1, "산진 한달된 선풍기 팝니다", "이사가서 필요가 없어졌어요 급하게 내놓습니다", "대현동", 1000, "서울 서대문구 창천동", 13, 25))
        dataList.add(Items(R.drawable.sample2, "김치냉장고", "이사로인해 내놔요", "안마담", 20000, "인천 계양구 귤현동", 8, 28))
        dataList.add(Items(R.drawable.sample3, "샤넬 카드지갑", "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다", "코코유", 10000, "수성구 범어동", 23,5))
//        dataList.add(Items(R.drawable.sample4))
//        dataList.add(Items(R.drawable.sample5))
//        dataList.add(Items(R.drawable.sample6))
//        dataList.add(Items(R.drawable.sample7))
//        dataList.add(Items(R.drawable.sample8))
//        dataList.add(Items(R.drawable.sample9))
//        dataList.add(Items(R.drawable.sample10))


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


    }

//    private fun readExcelAssets() : MutableList<Items> {
//
//        val dataList = mutableListOf<Items>()
//
//        try {
//            val assetManager = assets
//
//            val dataInput = assetManager.open("dummy.xls")
//
//            val workbook = WorkbookFactory.create(dataInput)
//
//            val sheet = workbook.getSheetAt(0)
//
//            val rowIterator = sheet.iterator()
//
//            var rowNum = 1
//
//            while (rowIterator.hasNext()) {
//                var myRow = rowIterator.next() as HSSFRow
//
//                var colNum = 1
//
//                while (myRow.getCell(colNum).toString().isNotBlank()) {
//                    var oneDataList = mutableListOf<String>()
//                    var currentCell = myRow.getCell(colNum).toString()
//                    when (colNum) {
//                        1 -> {
//                            oneDataList.add("R.drawable.$currentCell")
//                        }
//                        2 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        3 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        4 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        5 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        6 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        7 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        8 -> {
//                            oneDataList.add(currentCell)
//                        }
//                        else -> continue
//                    }
//                    dataList.add(Items(oneDataList[1].toInt(), oneDataList[2],oneDataList[3], oneDataList[4], oneDataList[5].toInt(), oneDataList[6], oneDataList[7].toInt(), oneDataList[8].toInt() ))
//                    colNum++
//                }
//
//                rowNum++
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        Log.d("excel", dataList.toString())
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