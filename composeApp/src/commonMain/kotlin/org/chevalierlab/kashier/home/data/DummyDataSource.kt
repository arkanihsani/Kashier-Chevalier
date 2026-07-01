package org.chevalierlab.kashier.home.data

import org.chevalierlab.kashier.home.domain.Item

interface DummyDataSource {

    fun getDatas(): List<Item>

}