package cmu.drones.systems;

import cmu.subsystems.BaseSubsystem;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

import java.util.List;

public abstract class DroneSubsystem extends BaseSubsystem implements DroneSystem {

    private ForgeTracker forgeTracker;

    @Override
    public void init(ShipAPI ship) {
        super.init(ship);

        forgeTracker = initDroneSystem(ship);
        SystemData.putDroneSystem(this, ship, Global.getCombatEngine());
    }

    @Override
    public void apply(MutableShipStatsAPI stats, String id, SubsystemState state, float effectLevel) {

    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {

    }

    @Override
    public void onActivation() {
        cycleDroneOrders();
    }

    @Override
    public void advance(float amount) {
        super.advance(amount);

        forgeTracker.advance(amount);
    }

    @Override
    public String getStatusString() {
        return null;
    }

    @Override
    public String getInfoString() {
        return null;
    }

    @Override
    public String getFlavourString() {
        return null;
    }

    @Override
    public int getNumGuiBars() {
        return 0;
    }

    @Override
    public void aiInit() {

    }

    @Override
    public void aiUpdate(float amount) {

    }

    @Override
    public int getIndexForDrone(ShipAPI drone) {
        List<ShipAPI> deployed = getForgeTracker().getDeployed();
        for (int i = 0; i < deployed.size(); i++) {
            if (drone == deployed.get(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ForgeTracker getForgeTracker() {
        return forgeTracker;
    }
}
