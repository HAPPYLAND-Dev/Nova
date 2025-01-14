package xyz.xenondevs.nova.data.resources

import org.bukkit.Material
import xyz.xenondevs.nova.data.NamespacedId
import xyz.xenondevs.nova.data.config.PermanentStorage
import xyz.xenondevs.nova.data.resources.builder.content.FontChar
import xyz.xenondevs.nova.data.resources.model.data.BlockModelData
import xyz.xenondevs.nova.data.resources.model.data.ItemModelData

typealias ModelData = Pair<Map<Material, ItemModelData>?, BlockModelData?>

object Resources {
    
    internal lateinit var modelDataLookup: Map<String, ModelData>
    internal lateinit var guiDataLookup: Map<String, FontChar>
    internal lateinit var wailaDataLookup: Map<String, FontChar>
    internal lateinit var textureIconLookup: Map<String, FontChar>
    internal lateinit var languageLookup: Map<String, Map<String, String>>
    
    internal fun updateModelDataLookup(modelDataLookup: Map<String, ModelData>) {
        this.modelDataLookup = modelDataLookup
        PermanentStorage.store("modelDataLookup", modelDataLookup)
    }
    
    internal fun updateGuiDataLookup(guiDataLookup: Map<String, FontChar>) {
        this.guiDataLookup = guiDataLookup
        PermanentStorage.store("guiDataLookup", guiDataLookup)
    }
    
    internal fun updateWailaDataLookup(wailaDataLookup: Map<String, FontChar>) {
        this.wailaDataLookup = wailaDataLookup
        PermanentStorage.store("wailaDataLookup", wailaDataLookup)
    }
    
    internal fun updateTextureIconLookup(textureIconLookup: Map<String, FontChar>) {
        this.textureIconLookup = textureIconLookup
        PermanentStorage.store("textureIconLookup", textureIconLookup)
    }
    
    internal fun updateLanguageLookup(languageLookup: Map<String, Map<String, String>>) {
        this.languageLookup = languageLookup
        PermanentStorage.store("languageLookup", languageLookup)
    }
    
    fun getModelData(id: NamespacedId): ModelData {
        return modelDataLookup[id.toString()]!!
    }
    
    fun getModelData(path: ResourcePath): ModelData {
        return modelDataLookup[path.toString()]!!
    }
    
    fun getModelData(id: String): ModelData {
        return modelDataLookup[id]!!
    }
    
    fun getModelDataOrNull(id: NamespacedId): ModelData? {
        return modelDataLookup[id.toString()]
    }
    
    fun getModelDataOrNull(path: ResourcePath): ModelData? {
        return modelDataLookup[path.toString()]
    }
    
    fun getModelDataOrNull(id: String): ModelData? {
        return modelDataLookup[id]
    }
    
    fun getGUIChar(id: NamespacedId): FontChar {
        return guiDataLookup[id.toString()]!!
    }
    
    fun getGUIChar(path: ResourcePath): FontChar {
        return guiDataLookup[path.toString()]!!
    }
    
    fun getGUIChar(id: String): FontChar {
        return guiDataLookup[id]!!
    }
    
    fun getGUICharOrNull(id: NamespacedId): FontChar? {
        return guiDataLookup[id.toString()]
    }
    
    fun getGUICharOrNull(path: ResourcePath): FontChar? {
        return guiDataLookup[path.toString()]
    }
    
    fun getGUICharOrNull(id: String): FontChar? {
        return guiDataLookup[id]
    }
    
    fun getWailaIconChar(id: NamespacedId): FontChar {
        return wailaDataLookup[id.toString()]!!
    }
    
    fun getWailaIconChar(path: ResourcePath): FontChar {
        return wailaDataLookup[path.toString()]!!
    }
    
    fun getWailaIconChar(id: String): FontChar {
        return wailaDataLookup[id]!!
    }
    
    fun getWailaIconCharOrNull(id: NamespacedId): FontChar? {
        return wailaDataLookup[id.toString()]
    }
    
    fun getWailaIconCharOrNull(path: ResourcePath): FontChar? {
        return wailaDataLookup[path.toString()]
    }
    
    fun getWailaIconCharOrNull(id: String): FontChar? {
        return wailaDataLookup[id]
    }
    
    fun getTextureIconChar(id: NamespacedId): FontChar {
        return textureIconLookup[id.toString()]!!
    }
    
    fun getTextureIconChar(path: ResourcePath): FontChar {
        return textureIconLookup[path.toString()]!!
    }
    
    fun getTextureIconChar(id: String): FontChar {
        return textureIconLookup[id]!!
    }
    
    fun getTextureIconCharOrNull(id: NamespacedId): FontChar? {
        return textureIconLookup[id.toString()]
    }
    
    fun getTextureIconCharOrNull(path: ResourcePath): FontChar? {
        return textureIconLookup[path.toString()]
    }
    
    fun getTextureIconCharOrNull(id: String): FontChar? {
        return textureIconLookup[id]
    }
    
}