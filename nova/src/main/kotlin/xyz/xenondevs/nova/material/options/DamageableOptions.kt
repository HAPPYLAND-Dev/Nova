package xyz.xenondevs.nova.material.options

import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.data.config.ConfigAccess
import xyz.xenondevs.nova.data.provider.Provider
import xyz.xenondevs.nova.data.provider.map
import xyz.xenondevs.nova.data.provider.provider
import xyz.xenondevs.nova.data.serialization.json.RecipeDeserializer
import xyz.xenondevs.nova.material.ItemNovaMaterial

@HardcodedMaterialOptions
fun DamageableOptions(
    maxDurability: Int,
    itemDamageOnAttackEntity: Int,
    itemDamageOnBreakBlock: Int,
    repairIngredient: RecipeChoice? = null
): DamageableOptions = HardcodedDamageableOptions(maxDurability, itemDamageOnAttackEntity, itemDamageOnBreakBlock, repairIngredient)

sealed interface DamageableOptions {
    
    val maxDurabilityProvider: Provider<Int>
    val itemDamageOnAttackEntityProvider: Provider<Int>
    val itemDamageOnBreakBlockProvider: Provider<Int>
    val repairIngredientProvider: Provider<RecipeChoice?>
    
    val maxDurability: Int
        get() = maxDurabilityProvider.value
    val itemDamageOnAttackEntity: Int
        get() = itemDamageOnAttackEntityProvider.value
    val itemDamageOnBreakBlock: Int
        get() = itemDamageOnBreakBlockProvider.value
    val repairIngredient: RecipeChoice?
        get() = repairIngredientProvider.value
    
    companion object : MaterialOptionsType<DamageableOptions> {
        
        override fun configurable(material: ItemNovaMaterial): DamageableOptions =
            ConfigurableDamageableOptions(material)
        
        override fun configurable(path: String): DamageableOptions =
            ConfigurableDamageableOptions(path)
        
    }
    
}

private class HardcodedDamageableOptions(
     maxDurability: Int,
     itemDamageOnAttackEntity: Int,
     itemDamageOnBreakBlock: Int,
     repairIngredient: RecipeChoice?
) : DamageableOptions {
    override val maxDurabilityProvider = provider(maxDurability)
    override val itemDamageOnAttackEntityProvider = provider(itemDamageOnAttackEntity)
    override val itemDamageOnBreakBlockProvider = provider(itemDamageOnBreakBlock)
    override val repairIngredientProvider = provider(repairIngredient)
}

@Suppress("UNCHECKED_CAST")
private class ConfigurableDamageableOptions : ConfigAccess, DamageableOptions {
    
    override val maxDurabilityProvider = getEntry<Int>("max_durability")
    override val itemDamageOnAttackEntityProvider = getEntry<Int>("item_damage_on_attack_entity")
    override val itemDamageOnBreakBlockProvider = getEntry<Int>("item_damage_on_break_block")
    override val repairIngredientProvider = getOptionalEntry<Any>("repair_ingredient").map {
        val list = when (it) {
            is String -> listOf(it)
            else -> it as List<String>
        }
        RecipeDeserializer.parseRecipeChoice(list)
    }
    
    constructor(path: String) : super(path)
    constructor(material: ItemNovaMaterial) : super(material)
    
}