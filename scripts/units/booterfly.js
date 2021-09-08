const statuses = require("libs/statuses")
const refresh = require("libs/refresh")

const pygmy = extendContent(UnitType, "Pygmy", {});
pygmy.constructor = () => extend(UnitEntity, {
});
Events.on(ClientLoadEvent, b  => {
   pygmy.abilities.add(new StatusFieldAbility(statuses.revitalizing, 50, 110, 0));
});