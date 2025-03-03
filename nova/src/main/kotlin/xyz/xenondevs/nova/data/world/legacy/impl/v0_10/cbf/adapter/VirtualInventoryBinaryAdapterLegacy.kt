package xyz.xenondevs.nova.data.world.legacy.impl.v0_10.cbf.adapter

import de.studiocode.invui.virtualinventory.VirtualInventory
import de.studiocode.invui.virtualinventory.VirtualInventoryManager
import io.netty.buffer.ByteBuf
import xyz.xenondevs.nova.data.world.legacy.impl.v0_10.cbf.BinaryAdapterLegacy
import java.lang.reflect.Type

internal object VirtualInventoryBinaryAdapterLegacy : BinaryAdapterLegacy<VirtualInventory> {
    
    override fun write(obj: VirtualInventory, buf: ByteBuf) {
        val data = VirtualInventoryManager.getInstance().serializeInventory(obj)
        buf.writeInt(data.size)
        buf.writeBytes(data)
    }
    
    override fun read(type: Type, buf: ByteBuf): VirtualInventory {
        val data = ByteArray(buf.readInt())
        buf.readBytes(data)
        return VirtualInventoryManager.getInstance().deserializeInventory(data)
    }
    
    
}