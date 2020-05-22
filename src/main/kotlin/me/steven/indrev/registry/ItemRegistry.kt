package me.steven.indrev.registry

import me.steven.indrev.IndustrialRevolution
import me.steven.indrev.identifier
import me.steven.indrev.item
import me.steven.indrev.items.Upgrade
import me.steven.indrev.items.UpgradeItem
import me.steven.indrev.ore
import net.minecraft.item.Item

class ItemRegistry {

    fun registerAll() {
        identifier("pulverized_iron").item(PULVERIZED_IRON)
        identifier("pulverized_gold").item(PULVERIZED_GOLD)
        identifier("pulverized_coal").item(PULVERIZED_COAL)
        identifier("pulverized_diamond").item(PULVERIZED_DIAMOND)

        identifier("buffer_upgrade").item(BUFFER_UPGRADE)
        identifier("speed_upgrade").item(SPEED_UPGRADE)
        identifier("energy_upgrade").item(ENERGY_UPGRADE)
    }

    companion object {

        val COPPER_ORE = identifier("copper").ore(true)
        val TIN_ORE = identifier("tin").ore(true)

        val PULVERIZED_IRON = Item(Item.Settings().group(IndustrialRevolution.MOD_GROUP))
        val PULVERIZED_GOLD = Item(Item.Settings().group(IndustrialRevolution.MOD_GROUP))
        val PULVERIZED_COAL = Item(Item.Settings().group(IndustrialRevolution.MOD_GROUP))
        val PULVERIZED_DIAMOND = Item(Item.Settings().group(IndustrialRevolution.MOD_GROUP))

        val BUFFER_UPGRADE = UpgradeItem(Item.Settings().group(IndustrialRevolution.MOD_GROUP).maxCount(1), Upgrade.BUFFER)
        val SPEED_UPGRADE = UpgradeItem(Item.Settings().group(IndustrialRevolution.MOD_GROUP).maxCount(1), Upgrade.SPEED)
        val ENERGY_UPGRADE = UpgradeItem(Item.Settings().group(IndustrialRevolution.MOD_GROUP).maxCount(1), Upgrade.ENERGY)
    }
}