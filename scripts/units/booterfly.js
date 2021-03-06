const statuses = require("libs/eistatuses")
const refresh = require("libs/eirefresh")

const pygmy = extendContent(UnitType, "pygmy", {});
pygmy.constructor = () => extend(UnitEntity, {
});

const schaus = extendContent(UnitType, "schaus", {});
schaus.constructor = () => extend(UnitEntity, {
});

const ageronia = extendContent(UnitType, "ageronia", {});
ageronia.constructor = () => extend(UnitEntity, {
});

Events.on(ClientLoadEvent, b  => {
   pygmy.abilities.add(new StatusFieldAbility(statuses.revitalizing, 50, 110, 0));
   schaus.abilities.add(new StatusFieldAbility(statuses.revitalizing, 100, 170, 0));
   schaus.weapons.get(0).bullet.status = StatusEffects.sapped;
   schaus.weapons.get(0).bullet.statusDuration = 45;
   schaus.weapons.get(0).bullet.fragBullet.status = StatusEffects.sapped;
   schaus.weapons.get(0).bullet.fragBullet.statusDuration = 30;
   ageronia.abilities.add(new StatusFieldAbility(statuses.revitalizing, 180, 225, 0));
});
