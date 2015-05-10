package com.minestellar.venus.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;
import com.minestellar.venus.MinestellarVenus;

public class BlockBasicVenus extends Block {
	private IIcon[] venusBlockIcon;

	public BlockBasicVenus(String name) {
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.venusBlockIcon = new IIcon[13];
		this.venusBlockIcon[0] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "venusSurfaceRock");
		this.venusBlockIcon[1] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "venusSubRock");
		this.venusBlockIcon[2] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "venusRock");
		this.venusBlockIcon[3] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "venusCobblestone");
		this.venusBlockIcon[4] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "sulfurOre");
		this.venusBlockIcon[5] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "uraniumOre");
		this.venusBlockIcon[6] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "gemOre");
		this.venusBlockIcon[7] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "crystalOre");
		this.venusBlockIcon[8] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "tinOre");
		this.venusBlockIcon[9] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "copperOre");
		this.venusBlockIcon[10] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "ironOre");
		this.venusBlockIcon[11] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "coalOre");
		this.venusBlockIcon[12] = par1IconRegister.registerIcon(MinestellarVenus.TEXTURE_PREFIX + "venusDungeonBrick");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.venusBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 13; ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) // FIX
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0) {
			return 1.25F;
		}

		if (meta == 1) {
			return 1.0F;
		}

		if (meta == 2) {
			return 1.5F;
		}

		if (meta == 3) {
			return 2.5F;
		}

		if (meta == 4) {
			return 2.5F;
		}

		if (meta == 5) {
			return 2.5F;
		}

		if (meta == 6) {
			return 2.5F;
		}

		if (meta == 7) {
			return 2.5F;
		}

		if (meta == 8) {
			return 2.5F;
		}

		if (meta == 9) {
			return 2.5F;
		}

		if (meta == 10) {
			return 2.5F;
		}

		if (meta == 11) {
			return 2.5F;
		}

		if (meta == 12) {
			return 25.0F;
		}

		return 1.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int metadata = world.getBlockMetadata(x, y, z);

		if (metadata == 12) {
			return 40.0F;
		}

		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	public MapColor getMapColor(int meta) {
		switch (meta) {
		case 0:
			return MapColor.redColor;
		default:
			return MapColor.redColor;
		}
	}

	@Override
	public int damageDropped(int meta) {
		if (meta == 2) {
			return 3;
		}

		return meta;
	}
}
