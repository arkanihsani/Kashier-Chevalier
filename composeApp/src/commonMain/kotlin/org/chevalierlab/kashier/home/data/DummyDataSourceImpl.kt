package org.chevalierlab.kashier.home.data

import org.chevalierlab.kashier.home.domain.Item

class DummyDataSourceImpl : DummyDataSource {
    override fun getDatas(): List<Item> = listOf(
        // Produce 🍎
        Item(id = 1, userId = 101, name = "Pisang Cavendish (Cavendish Banana)", price = 25000.0),
        Item(id = 2, userId = 101, name = "Bayam Segar (Fresh Spinach)", price = 5000.0),

        // Dairy & Eggs 🥛
        Item(id = 3, userId = 101, name = "Susu UHT Full Cream 1L (UHT Milk)", price = 18500.0),
        Item(id = 4, userId = 101, name = "Telur Ayam Negeri 10 butir (Chicken Eggs)", price = 28000.0),

        // Meat & Poultry 🍗
        Item(id = 5, userId = 101, name = "Dada Ayam Fillet 500g (Chicken Breast)", price = 48000.0),

        // Bakery 🍞
        Item(id = 6, userId = 101, name = "Roti Tawar Kupas (White Bread)", price = 16000.0),

        // Pantry Staples 🥫
        Item(id = 7, userId = 101, name = "Beras Pandan Wangi 5kg (Rice)", price = 68000.0),
        Item(id = 8, userId = 101, name = "Minyak Goreng 1L (Cooking Oil)", price = 25000.0),
        Item(id = 9, userId = 101, name = "Indomie Goreng (Instant Noodles)", price = 3100.0),

        // Beverages ☕
        Item(id = 10, userId = 101, name = "Kopi Kapal Api Special 165g (Coffee Powder)", price = 15000.0),
        Item(id = 11, userId = 101, name = "Air Mineral 1.5L (Mineral Water)", price = 6500.0),

        // Snacks 🥨
        Item(id = 12, userId = 101, name = "Chitato Sapi Panggang (Potato Chips)", price = 11500.0),

        // Frozen Foods 🧊
        Item(id = 13, userId = 101, name = "Nugget Ayam 250g (Chicken Nuggets)", price = 29000.0),

        // Household & Cleaning 🧼
        Item(id = 14, userId = 101, name = "Sunlight Jeruk Nipis 750ml (Dish Soap)", price = 14000.0),

        // Personal Care 🧴
        Item(id = 15, userId = 101, name = "Pepsodent Pasta Gigi (Toothpaste)", price = 12500.0)
    )
}