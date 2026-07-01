package org.chevalierlabsas.kashier.home.data

import org.chevalierlabsas.kashier.home.domain.Item

class DummyDataSource {

    fun getData(): List<Item> = listOf(
        Item(
            id = 1,
            userId = 1,
            name = "Item 1",
            price = 100000.0
        ),
        Item(
            id = 2,
            userId = 1,
            name = "Item 2",
            price = 200000.0
        ),
        Item(
            id = 3,
            userId = 1,
            name = "Item 3",
            price = 300000.0
        ),
        Item(
            id = 4,
            userId = 1,
            name = "Item 4",
            price = 400000.0
        ),
        Item(
            id = 5,
            userId = 1,
            name = "Item 5",
            price = 500000.0
        ),
        Item(
            id = 6,
            userId = 1,
            name = "Item 7",
            price = 700000.0
        ),
        Item(
            id = 8,
            userId = 1,
            name = "Item 8",
            price = 800000.0
        ),
        Item(
            id = 9,
            userId = 1,
            name = "Item 9",
            price = 900000.0
        ),
        Item(
            id = 10,
            userId = 1,
            name = "Item 10",
            price = 1000000.0
        ),
        Item(
            id = 11,
            userId = 1,
            name = "Item 11",
            price = 1100000.0
        ),
        Item(
            id = 12,
            userId = 1,
            name = "Item 12",
            price = 1200000.0
        ),
    )

}