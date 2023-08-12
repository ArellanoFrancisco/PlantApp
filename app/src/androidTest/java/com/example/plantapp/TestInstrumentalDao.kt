package com.example.plantapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plantapp.Model.Local.Dao.FlowerDao
import com.example.plantapp.Model.Local.Database.FlowerDataBase
import com.example.plantapp.Model.Local.Entities.FlowerDetails
import com.example.plantapp.Model.Local.Entities.FlowerList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestInstrumentalDao {

    private lateinit var fDao: FlowerDao
    private lateinit var fDB: FlowerDataBase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        fDB = Room.inMemoryDatabaseBuilder(context, FlowerDataBase::class.java).build()
        fDao = fDB.getFlowerDao()
    }

    @After
    fun shutDown() {
        fDB.close()
    }

    @Test
    fun insertListFlowers() = runBlocking {
        val flowerList = listOf(
            FlowerList(1, "Flor1", "tipo1", "imagen1", "descripcion1"),
            FlowerList(2, "Flor2", "tipo2", "imagen2", "descripcion2"),
            FlowerList(3, "Flor3", "tipo3", "imagen3", "descripcion3"),
            FlowerList(4, "Flor4", "tipo4", "imagen4", "descripcion4"),
            FlowerList(1, "Flor5", "tipo5", "imagen5", "descripcion5")
        )
        fDao.insertAllFlowers(flowerList)

        val flowerLiveData = fDao.getAllFlowers()
        val listFlowers: List<FlowerList> = flowerLiveData.value ?: emptyList()

        assertThat(listFlowers, not(emptyList()))
        assertThat(listFlowers.size, equalTo(5))

    }

    @Test
    fun insertFlowerDetails() = runBlocking {
        val details = FlowerDetails(1, "Flor1", "tipo1", "imagen1", "descripcion1")
        fDao.insertFlowersDetail(details)
        val flowerLiveData = fDao.getFlowersDetailById(1)
        val flower = flowerLiveData.value

        assertThat(flower?.id, equalTo(1))
        assertThat(flower?.nombre, equalTo("Flor1"))


    }
}