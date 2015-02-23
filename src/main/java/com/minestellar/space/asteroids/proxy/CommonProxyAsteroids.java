/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.space.asteroids.proxy;

import net.minecraft.entity.player.EntityPlayerMP;

import com.minestellar.space.asteroids.util.ConfigManagerAsteroids;
import com.minestellar.space.asteroids.world.TeleporterAsteroids;
import com.minestellar.space.orbit.util.ConfigManagerOrbit;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CommonProxyAsteroids {

	public void preInit(FMLPreInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new PlayerTickHandlerCommon());
	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {

	}

	public void spawnParticle(String string, double x, double y, double z) {
	}

	public static class PlayerTickHandlerCommon {
		@SubscribeEvent
		public void onPlayerTick(PlayerTickEvent event) {
			if (event.player != null && event.player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) event.player;
				if (playerMP.posY >= 270) {
					if (playerMP.dimension == ConfigManagerOrbit.idDimensionOrbit) {
						playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, ConfigManagerAsteroids.idDimensionAsteroids, new TeleporterAsteroids(playerMP.mcServer.worldServerForDimension(ConfigManagerAsteroids.idDimensionAsteroids)));
					}
				}
			}
		}

	}
}