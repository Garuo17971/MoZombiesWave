package wily.mozombieswave;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import wily.mozombieswave.entity.*;

@EventBusSubscriber(value = Side.CLIENT, modid = MozombiesWaveMod.MODID)
public class ClientEvents {

	public static void registerEntityRender() {
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieDwarf.class, renderManager -> new RenderLiving(renderManager, new EntityZombieDwarf.Modeldwarfzombie(), 0.5f) {
			protected ResourceLocation getEntityTexture(Entity entity) {
				return new ResourceLocation(MozombiesWaveMod.MODID, "textures/zombie_dwarf.png");
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntitySurvivor.class, EntitySurvivor.RenderSurvivor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCreeper.class, renderManager -> new RenderLiving(renderManager, new ModelCreeper(), 0.5f) {
			protected ResourceLocation getEntityTexture(Entity entity) {
				return new ResourceLocation("mozombies_wave:textures/zombie_creeper.png");
			}
		});
		renderDefaultMoZombie(EntityDiscoZombie.class, EntityDiscoZombie.name);
		renderDefaultMoZombie(EntityNetherZombie.class, EntityNetherZombie.name);
		renderDefaultMoZombie(EntityZombieChef.class, EntityZombieChef.name);
		renderDefaultMoZombie(EntityZombieCyborg.class, EntityZombieCyborg.name);
		renderDefaultMoZombie(EntityZombieHerobrine.class, EntityZombieHerobrine.name);
		renderDefaultMoZombie(EntityZombieKing.class, EntityZombieKing.name);
		renderDefaultMoZombie(EntityZombieKnight.class, EntityZombieKnight.name);
		renderDefaultMoZombie(EntityZombieNotch.class, EntityZombieNotch.name);
		renderDefaultMoZombie(EntityZombieMiner.class, EntityZombieMiner.name);
		renderDefaultMoZombie(EntityZombiePa.class, EntityZombiePa.name);
		renderDefaultMoZombie(EntityZombiePirate.class, EntityZombiePirate.name);
	}
	public static void renderDefaultMoZombie(Class<? extends EntityMoZombieBase> mo, String name){
		RenderingRegistry.registerEntityRenderingHandler(mo, renderManager -> {
			return new RenderBiped(renderManager, new ModelZombie(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation(MozombiesWaveMod.MODID,"textures/" + name + ".png");
				}
			};
		});
	}
}
