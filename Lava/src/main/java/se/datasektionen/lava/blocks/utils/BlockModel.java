package se.datasektionen.lava.blocks.utils;
import net.minecraft.util.StringRepresentable;

public enum BlockModel implements StringRepresentable {
   FRAME,
   INSCRYPTION,
   STONE;

   public String toString() {
      return this.getSerializedName();
   }

   public String getSerializedName() {
      if (this == FRAME) {
    	  return "frame";
      }else if (this == INSCRYPTION){
    	  return "inscryption";
      }else {
    	  return "stone";
      }
   }
}