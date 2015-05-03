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

package com.minestellar.core.blocks;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.tile.TileEntityCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockCable extends BlockContainer {

	float pixel = 1F / 16F;

	public static IIcon[] cableBlockIcon;

	protected BlockCable(String name) {
		super(Material.ground);
		this.setBlockName(name);

		this.setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2);
		this.useNeighborBrightness = true;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityCable cable = (TileEntityCable) world.getTileEntity(x, y, z);

		if (cable != null) {

			float minX = 11 * pixel / 2 - (cable.connections[5] != null ? (11 * pixel / 2) : 0);
			float minY = 11 * pixel / 2 - (cable.connections[1] != null ? (11 * pixel / 2) : 0);
			float minZ = 11 * pixel / 2 - (cable.connections[2] != null ? (11 * pixel / 2) : 0);
			float maxX = 1 - 11 * pixel / 2 + (cable.connections[3] != null ? (11 * pixel / 2) : 0);
			float maxY = 1 - 11 * pixel / 2 + (cable.connections[0] != null ? (11 * pixel / 2) : 0);
			float maxZ = 1 - 11 * pixel / 2 + (cable.connections[4] != null ? (11 * pixel / 2) : 0);

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityCable cable = (TileEntityCable) world.getTileEntity(x, y, z);

		if (cable != null) {

			float minX = 11 * pixel / 2 - (cable.connections[5] != null ? (11 * pixel / 2) : 0);
			float minY = 11 * pixel / 2 - (cable.connections[1] != null ? (11 * pixel / 2) : 0);
			float minZ = 11 * pixel / 2 - (cable.connections[2] != null ? (11 * pixel / 2) : 0);
			float maxX = 1 - 11 * pixel / 2 + (cable.connections[3] != null ? (11 * pixel / 2) : 0);
			float maxY = 1 - 11 * pixel / 2 + (cable.connections[0] != null ? (11 * pixel / 2) : 0);
			float maxZ = 1 - 11 * pixel / 2 + (cable.connections[4] != null ? (11 * pixel / 2) : 0);

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCable(meta);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		BlockCable.cableBlockIcon = new IIcon[3]; // UPDATE WHEN ADDING BLOCKS
		BlockCable.cableBlockIcon[0] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockCable0");
		BlockCable.cableBlockIcon[1] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockCable1");
		BlockCable.cableBlockIcon[2] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockCable2");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return BlockCable.cableBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 3; ++i) { // UPDATE WHEN ADDING BLOCKS
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
}
