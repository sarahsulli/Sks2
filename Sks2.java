package SarahSullivan; 
import robocode.*;
import java.awt.Color;
import java.awt.*;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Sks2 - a robot by Sarah Sullivan
 */
public class Sks2 extends AdvancedRobot
{
    setColors(Color.red, Color.black, Color.black); 
    boolean movingForward;
    int dist = 50;

    int turnCounter;

    /**
     * run: Sks2's default behavior
     */
    public void run() {
        // Initialization of the robot should be put here

        setColors(Color.BLACK, Color.RED, Color.BLUE);// body,gun,radar

        // Robot main loop
        while(true) {
            // Replace the next 4 lines with any behavior you would like
            ahead(100); // Move ahead 100
            turnGunRight(360); // Spin gun around
            back(100); // Move back 100
            turnGunRight(360); // Spin gun around

        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {

        double absoluteBearing = getHeading() + e.getBearing();
        double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

        if (Math.abs(bearingFromGun) <= 3) {
            turnGunRight(bearingFromGun);

            if (getGunHeat() == 0) {
                fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));

            }
        } 
        else {
            turnGunRight(bearingFromGun);
        }

        if (bearingFromGun == 0) {
            scan();
        }
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        turnLeft(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

        ahead(dist);
        dist *= -3;
        scan();
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        back(30);

        turnRight(90);

		
    }	

    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

        turnGunRight(turnGunAmt);
        fire(2);
    }
}
