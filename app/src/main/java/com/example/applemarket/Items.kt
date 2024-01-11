package com.example.applemarket

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.ss.usermodel.WorkbookFactory

data class Items(
    val image: Int,
    val title: String?,
    val context: String?,
    val seller: String?,
    val price: Int,
    val loca: String?,
    val like: Int,
    val chat: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        image = parcel.readInt(),
        title = parcel.readString(),
        context = parcel.readString(),
        seller = parcel.readString(),
        price = parcel.readInt(),
        loca = parcel.readString(),
        like = parcel.readInt(),
        chat = parcel.readInt()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(title)
        parcel.writeString(context)
        parcel.writeString(seller)
        parcel.writeInt(price)
        parcel.writeString(loca)
        parcel.writeInt(like)
        parcel.writeInt(chat)
    }



//    companion object DetailData {
//
//        fun loadData(context: Context): MutableList<Items> {
//            val dataList: MutableList<Items> = arrayListOf()
//            try {
//                val assetManager = context.assets
//
//                val dataInput = assetManager.open("dummy.xls")
//
//                val workbook = WorkbookFactory.create(dataInput)
//
//                val sheet = workbook.getSheetAt(0)
//
//                val rowIterator = sheet.iterator()
//
//                var rowNum = 1
//
//                while (rowIterator.hasNext()) {
//                    var myRow = rowIterator.next() as HSSFRow
//
//                    if (myRow.getCell(1).toString().isBlank()) break
//
//                    for (colNum in 1..8) {
//                        var oneDataList = mutableListOf<String>()
//                        var currentCell = myRow.getCell(colNum).toString()
//                        when (colNum) {
//                            1 -> {
//                                oneDataList.add("R.drawable.$currentCell")
//                            }
//
//                            2 -> {
//                                oneDataList.add(currentCell)
//                            }
//
//                            3 -> {
//                                oneDataList.add(currentCell)
//                            }
//
//                            4 -> {
//                                oneDataList.add(currentCell)
//                            }
//
//                            5 -> {
//                                oneDataList.add(currentCell)
//                            }
//
//                            6 -> {
//                                oneDataList.add(currentCell)
//                            }
//
//                            7 -> {
//                                oneDataList.add(currentCell)
//                            }
//
//                            8 -> {
//                                oneDataList.add(currentCell)
//                            }
//                        }
//                        dataList.add(
//                            Items(
//                                oneDataList[1].toInt(),
//                                oneDataList[2],
//                                oneDataList[3],
//                                oneDataList[4],
//                                oneDataList[5].toInt(),
//                                oneDataList[6],
//                                oneDataList[7].toInt(),
//                                oneDataList[8].toInt()
//                            )
//                        )
//                    }
//
//                    rowNum++
//                }
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return dataList
//        }
//    }
//
    companion object CREATOR : Parcelable.Creator<Items> {
        override fun createFromParcel(parcel: Parcel): Items {
            return Items(parcel)
        }

        override fun newArray(size: Int): Array<Items?> {
            return arrayOfNulls(size)
        }
    }

}
