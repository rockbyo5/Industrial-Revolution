package me.steven.indrev.blocks

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder
import me.steven.indrev.inventories.DefaultSidedInventory
import me.steven.indrev.registry.GeneratorRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.entity.AbstractFurnaceBlockEntity
import net.minecraft.container.ArrayPropertyDelegate
import net.minecraft.container.PropertyDelegate
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import team.reborn.energy.EnergyTier

class CoalGeneratorBlockEntity : GeneratorBlockEntity(GeneratorRegistry.COAL_GENERATOR_BLOCK_ENTITY, 0.1, EnergyTier.LOW), PropertyDelegateHolder {
    private val inventory = DefaultSidedInventory(ItemStack.EMPTY)
    private val delegate = ArrayPropertyDelegate(2)
    var burnTime: Int = 0
    var maxBurnTime: Int = 0

    override fun tick() {
        super.tick()
            if (shouldGenerate()) {
                burnTime--
                delegate[0] = burnTime
            }
            else if (maxStoredPower > energy) {
                val invStack = inventory.getInvStack(0)
                if (!invStack.isEmpty && BURN_TIME_MAP.containsKey(invStack.item)) {
                    burnTime = BURN_TIME_MAP[invStack.item] ?: return
                    maxBurnTime = burnTime
                    delegate[1] = maxBurnTime
                    invStack.count--
                    if (invStack.isEmpty) inventory.setInvStack(0, ItemStack.EMPTY)
                    else inventory.setInvStack(0, invStack)
                }
            }
        markDirty()
    }

    override fun fromTag(tag: CompoundTag?) {
        super.fromTag(tag)
        burnTime = tag?.getInt("BurnTime") ?: 0
        maxBurnTime = tag?.getInt("MaxBurnTime") ?: 0
        delegate[0] = burnTime
        delegate[1] = maxBurnTime
    }

    override fun toTag(tag: CompoundTag?): CompoundTag {
        tag?.putInt("BurnTime", burnTime)
        tag?.putInt("MaxBurnTime", maxBurnTime)
        return super.toTag(tag)
    }

    override fun getInventory(): Inventory = inventory

    override fun getInventory(state: BlockState?, world: IWorld?, pos: BlockPos?): SidedInventory = inventory

    override fun shouldGenerate(): Boolean = burnTime > 0 && energy < maxStoredPower

    companion object {
        private val BURN_TIME_MAP = AbstractFurnaceBlockEntity.createFuelTimeMap()
    }

    override fun getPropertyDelegate(): PropertyDelegate {
        delegate[0] = burnTime
        delegate[1] = maxBurnTime
        return delegate
    }
}