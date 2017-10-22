package com.SirBlobman.gemmary.block.custom;

import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.creative.GTabs;
import com.SirBlobman.gemmary.item.custom.ItemGem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGem extends Block {
    public static final PropertyBool FANCY = PropertyBool.create("fancy");
    private final ItemGem gem;
    public BlockGem(ItemGem gem, int harvestLevel) {
        super(Material.IRON);
        this.gem = gem;
        String name = (gem.getRegistryName().getResourcePath() + "_block");
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GTabs.BLOCKS);
        
        setDefaultState(blockState.getBaseState().withProperty(FANCY, GConfig.FANCY_TEXTURES));
        setHarvestLevel("pickaxe", harvestLevel);
        setHardness(gem.getMohsValue());
        setResistance(gem.getMohsValue());
    }
    
    public ItemGem getGem() {return gem;}
    public float getMohsValue() {return getGem().getMohsValue();} 
    public BlockStateContainer createBlockState() {return new BlockStateContainer(this, FANCY);}
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        boolean fancy = (meta == 0) ? false : true;
        IBlockState ibs = getDefaultState();
        return ibs.withProperty(FANCY, fancy);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        boolean fancy = state.getValue(FANCY);
        return (fancy ? 1 : 0);
    }
    
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        boolean fancy = GConfig.FANCY_TEXTURES;
        IBlockState ibs = getDefaultState();
        return ibs.withProperty(FANCY, fancy);
    }
}