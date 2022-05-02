package se.datasektionen.lava.datagen;

import se.datasektionen.lava.setup.Registration;

import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.FRAME_BLOCK.get(), createSimpleTable("frame_block", Registration.FRAME_BLOCK.get()));
        lootTables.put(Registration.SLOPE_BLOCK.get(), createSimpleTable("slope_block", Registration.SLOPE_BLOCK.get()));
    }
}
