package se.datasektionen.lava.blocks.utils;
import net.minecraft.util.StringRepresentable;

public enum BlockModel implements StringRepresentable {
   FRAME,
   INSCRIPTION,
   STONE;

   public String toString() {
      return this.getSerializedName();
   }

   public String getSerializedName() {
      if (this == FRAME) {
    	  return "frame";
      }else if (this == INSCRIPTION){
    	  return "inscription";
      }else {
    	  return "stone";
      }
   }
}