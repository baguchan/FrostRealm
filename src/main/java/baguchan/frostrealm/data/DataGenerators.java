package baguchan.frostrealm.data;

import baguchan.frostrealm.FrostRealm;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = FrostRealm.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent evt) {
		evt.getGenerator().addProvider(new BlockstateGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(new ItemModelGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		BlockTagsProvider blocktags = new BlockTagGenerator(evt.getGenerator(), evt.getExistingFileHelper());
		evt.getGenerator().addProvider(blocktags);
		evt.getGenerator().addProvider(new ItemTagGenerator(evt.getGenerator(), blocktags, evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(new EntityTagGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(new FluidTagGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(new LootGenerator(evt.getGenerator()));
		evt.getGenerator().addProvider(new CraftingGenerator(evt.getGenerator()));
		evt.getGenerator().addProvider(new FrostrealmWorldDataCompiler(evt.getGenerator()));
	}
}