package org.chevalierlab.kashier.home.data.datasource

import org.chevalierlab.kashier.home.domain.models.Item

interface DummyDataSource {

    fun getDatas(): List<Item>

}