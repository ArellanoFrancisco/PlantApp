package com.example.plantapp.Model.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantapp.Model.Local.Dao.FlowerDao
import com.example.plantapp.Model.Local.Entities.FlowerDetails
import com.example.plantapp.Model.Local.Entities.FlowerList

@Database(entities = [FlowerList::class, FlowerDetails::class], version = 1, exportSchema = false)
abstract class FlowerDataBase : RoomDatabase() {
    abstract fun getFlowerDao(): FlowerDao

    companion object {


        @Volatile
        private var INSTANCE: FlowerDataBase? = null

        fun getdatabase(context: Context): FlowerDataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlowerDataBase::class.java,
                    "Flowers_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}