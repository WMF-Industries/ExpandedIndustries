package ExpandedIndustries.content;

import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class EILoadouts{
    public static Schematic basicFrag;

    public EILoadouts(){
    }

    public static void load() {
        basicFrag = Schematics.readBase64("bXNjaAF4nGNgYmBiZmDJS8xNZeByzi9KtVJwK0pMZ+BOSS1OLsosKMnMz2NgYGDLSUxKzSlmYIqOZWTgSc3UTQYq1U0DqWRgYGSAAADGiBF7");
    }
}