package baguchan.frostrealm.block.crop;

import baguchan.frostrealm.registry.FrostBlocks;
import baguchan.frostrealm.registry.FrostItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SugarBeetBlock extends BeetrootBlock implements BonemealableBlock {

	public SugarBeetBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
		return p_52302_.is(Blocks.FARMLAND) || p_52302_.is(FrostBlocks.FROZEN_FARMLAND.get());
	}

	protected ItemLike getBaseSeedId() {
		return FrostItems.SUGARBEET_SEEDS.get();
	}
}
