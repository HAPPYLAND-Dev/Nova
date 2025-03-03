package xyz.xenondevs.nova.world.block.model

import net.minecraft.world.entity.EquipmentSlot
import org.bukkit.Material
import xyz.xenondevs.nova.data.resources.model.data.ArmorStandBlockModelData
import xyz.xenondevs.nova.data.world.block.property.Directional
import xyz.xenondevs.nova.data.world.block.state.NovaBlockState
import xyz.xenondevs.nova.tileentity.requiresLight
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.yaw
import xyz.xenondevs.nova.world.fakeentity.impl.FakeArmorStand
import xyz.xenondevs.nova.world.fakeentity.metadata.impl.ArmorStandMetadata

class ArmorStandModelProvider(blockState: NovaBlockState) : BlockModelProvider {
    
    private val pos = blockState.pos
    private val material = blockState.material
    private val modelData = material.block as ArmorStandBlockModelData
    
    private val armorStands = ArrayList<FakeArmorStand>()
    private val multiBlockPositions = material.multiBlockLoader?.invoke(pos)
    
    override var currentSubId = 0
        private set
    
    init {
        val location = pos.location.center()
        
        val directional = blockState.getProperty(Directional::class)
        location.yaw = directional?.facing?.yaw ?: 180f // by default, look north (180°)
        
        armorStands += FakeArmorStand(location, false, ::setArmorStandValues)
        
        multiBlockPositions?.forEachIndexed { i, otherPos ->
            armorStands += FakeArmorStand(
                otherPos.location.center().apply { yaw = location.yaw }, false
            ) { ast, data -> setArmorStandValues(ast, data, i + 1) }
        }
    }
    
    private fun setArmorStandValues(armorStand: FakeArmorStand, data: ArmorStandMetadata, subId: Int = 0) {
        data.isInvisible = true
        data.isMarker = true
        data.isOnFire = modelData.hitboxType.requiresLight
        armorStand.setEquipment(EquipmentSlot.HEAD, modelData[subId].get(), false)
    }
    
    override fun load(placed: Boolean) {
        if (placed) {
            // cannot be moved out of this if as it would break blocks that change their hitbox type such as cables
            pos.block.type = modelData.hitboxType
            multiBlockPositions?.forEach { it.block.type = modelData.hitboxType }
        }
        armorStands.forEach(FakeArmorStand::register)
    }
    
    override fun remove(broken: Boolean) {
        armorStands.forEach(FakeArmorStand::remove)
        if (broken) {
            pos.block.type = Material.AIR
            multiBlockPositions?.forEach { it.block.type = Material.AIR }
        }
    }
    
    override fun update(subId: Int) {
        currentSubId = subId
        armorStands[0].setEquipment(EquipmentSlot.HEAD, modelData[subId].get(), true)
    }
    
    companion object : BlockModelProviderType<ArmorStandModelProvider> {
        override fun create(blockState: NovaBlockState) = ArmorStandModelProvider(blockState)
    }
    
}