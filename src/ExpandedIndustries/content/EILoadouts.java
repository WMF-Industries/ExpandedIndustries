package ExpandedIndustries.content;

import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class EILoadouts{
    public static Schematic basicFrag, basicQuadrant;

    public static void load(){
        basicFrag = Schematics.readBase64("bXNjaAF4nGNgYmBiYWDJS8xNZeByzi9KtVJwK0pMZ+BKzs8rSc0r8U0sYGCqrmXgTkktTi7KLCjJzM9jYGBgy0lMSs0pZmCKjmVk4EnN1E0GatVNA+lkYGBkgAAAUOAWmQ==");
        basicQuadrant = Schematics.readBase64("bXNjaAF4nGNgY2BjYWDJS8xNZeBzzi9KtVIILE1MKUrMK2HgSs7PK0nNK/FNLGBgqq5l4E5JLU4uyiwoyczPY2BgYMtJTErNKWZgio5lZBBIzdRNBmrXLYTpZmBgZGBgAkIGAGDdGi0=");
    }
}