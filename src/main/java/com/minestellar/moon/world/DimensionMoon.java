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

package com.minestellar.moon.world;

import com.minestellar.moon.util.ConfigManagerMoon;
import net.minecraftforge.common.DimensionManager;

public class DimensionMoon {
	public static void init() {
		registerWorldProvider();
		registerDimensions();
	}

	public static void registerDimensions() {
		DimensionManager.registerDimension(ConfigManagerMoon.idDimensionMoon, ConfigManagerMoon.idDimensionMoon);
	}

	public static void registerWorldProvider() {
		DimensionManager.registerProviderType(ConfigManagerMoon.idDimensionMoon, WorldProviderMoon.class, true);
	}
}
