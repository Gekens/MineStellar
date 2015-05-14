package com.minestellar.venus.proxy;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;

import com.minestellar.utils.world.IMinestellarWorldProvider;
import com.minestellar.core.world.CloudRenderer;
import com.minestellar.venus.entities.EntityEvolvedBlaze;
import com.minestellar.venus.entities.EntityVenusianTNT;
import com.minestellar.venus.entities.EntityVenusianVillager;
import com.minestellar.venus.entities.render.RenderEvolvedBlaze;
import com.minestellar.venus.entities.render.RenderVenusianTNT;
import com.minestellar.venus.entities.render.RenderVenusianVillager;
import com.minestellar.venus.items.ItemJetpack;
import com.minestellar.venus.items.VenusItems;
import com.minestellar.venus.world.SkyRendererVenus;
import com.minestellar.venus.world.WorldProviderVenus;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxyVenus extends CommonProxyVenus {
	private static Minecraft mc = FMLClientHandler.instance().getClient();

	private static int renderIndexGemArmor;
	private static int renderIndexSulfurArmor;
	private static int renderIndexJetpack;

	public static ArrayList<SoundPoolEntry> newMusic = new ArrayList<SoundPoolEntry>();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ClientProxyVenus.renderIndexGemArmor = RenderingRegistry.addNewArmourRendererPrefix("gem");
		ClientProxyVenus.renderIndexSulfurArmor = RenderingRegistry.addNewArmourRendererPrefix("sulfur");
		ClientProxyVenus.renderIndexJetpack = RenderingRegistry.addNewArmourRendererPrefix("jetpack");

		super.preInit(event);
	}

	@Override
	public int getGemArmorRenderIndex() {
		return ClientProxyVenus.renderIndexGemArmor;
	}

	@Override
	public int getSulfurArmorRenderIndex() {
		return ClientProxyVenus.renderIndexSulfurArmor;
	}

	@Override
	public int getJetpackArmorRenderIndex() {
		return ClientProxyVenus.renderIndexJetpack;
	}

	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedBlaze.class, new RenderEvolvedBlaze());
		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianVillager.class, new RenderVenusianVillager());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new TickHandlerClient());

		RenderingRegistry.registerEntityRenderingHandler(EntityVenusianTNT.class, new RenderVenusianTNT());

		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ClientProxyVenus.registerEntityRenderers();
		super.postInit(event);
	}

	public void registerRenderInfo() {
	}

	@Override
	public int getBlockRender(Block block) {
		return -1;
	}
	
	public static class TickHandlerClient {  
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public void onClientTick(ClientTickEvent event) {
			final Minecraft minecraft = FMLClientHandler.instance().getClient();
			final WorldClient world = minecraft.theWorld;
			final EntityClientPlayerMP player = minecraft.thePlayer;

			// TODO: Make work!
			if (Side.CLIENT != null) {
	    		if (player != null && world != null && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == VenusItems.jetpack && FMLClientHandler.instance().getClient().gameSettings.keyBindJump.isPressed() && player.posY < 360) {
	    			((ItemJetpack)player.inventory.armorItemInSlot(2).getItem()).setActive();
	    			player.motionY -= 0.05D;
	    			player.motionY += 0.07 + player.rotationPitch * 2 / 150 * 0.063;
	    			player.fallDistance = 0.0F;
	        		world.spawnParticle("largesmoke", player.posX, player.posY - 1D, player.posZ, 0, -0.5, 0);
	    		}
	        }	
		
			if (world != null) {
				if (world.provider instanceof WorldProviderVenus) {
					if (world.provider.getSkyRenderer() == null) {
						world.provider.setSkyRenderer(new SkyRendererVenus((IMinestellarWorldProvider) world.provider));
					}

					if (world.provider.getCloudRenderer() == null) {
						world.provider.setCloudRenderer(new CloudRenderer());
					}
				}
			}
		}
	}
}
