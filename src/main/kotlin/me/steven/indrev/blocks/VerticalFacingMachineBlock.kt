package me.steven.indrev.blocks

import me.steven.indrev.blockentities.MachineBlockEntity
import me.steven.indrev.utils.Tier
import net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties

class VerticalFacingMachineBlock(
    settings: Settings,
    tier: Tier,
    screenHandlerType: ExtendedScreenHandlerType<*>?,
    blockEntityProvider: () -> MachineBlockEntity) : MachineBlock(settings, tier, screenHandlerType, blockEntityProvider) {

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        super.getPlacementState(ctx)
        return this.defaultState.with(FACING, ctx?.playerLookDirection?.opposite)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        super.appendProperties(builder)
        builder?.add(FACING)
    }

    companion object {
        val FACING: DirectionProperty = Properties.FACING
    }
}