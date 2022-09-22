package battleScenario;

import characters.Item;
import characters.enemies.Thief;

import static staticdata.GameData.*;

public class Battle {
    Thief thief;

    public Battle() {

    }

    public Battle(Thief thief) {
        this.thief = thief;
    }

    public boolean battle() {
        int thiefHP = thief.getHitpoints();
        int thiefDamage = thief.getDamage();
        int thiefCriticalDamage = thiefDamage + 12;
        int thiefCriticalChance = 30;

        int playerHP = player.getHitpoints();
        int playerDamage = player.getDamage();
        int playerCriticalDamage = playerDamage + 10;
        int playerCriticalChance = 50;

        boolean fight = true;

        System.out.println(separator);
        while (fight) {
            thiefStatus(thief);
            System.out.println(divider);
            player.playerStatus();
            System.out.println(separator);
            sc.nextLine();

            System.out.println("\n\n\tWhat do you want to do?");
            System.out.println("\t> 1: Attack the thief.");
            System.out.println("\t> 2: Flee.\n");

            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("***********************BATTLE***********************");
                boolean[] isCritical = { false, false };
                int playerDamageCriticalIndex = 0;
                int enemyDamageCriticalIndex = 1;

                if (Math.random() * 100 < playerCriticalChance) {
                    isCritical[playerDamageCriticalIndex] = true;
                    playerHP -= thiefDamage;
                    thiefHP -= playerCriticalDamage;

                    damageStatus(playerCriticalDamage, thiefDamage, thief.getName(), isCritical);

                } else if (Math.random() * 100 < thiefCriticalChance) {
                    isCritical[enemyDamageCriticalIndex] = true;
                    playerHP -= thiefCriticalDamage;
                    thiefHP -= playerDamage;

                    damageStatus(playerDamage, thiefCriticalDamage, thief.getName(), isCritical);

                }

                if (noCriticalDamageFromBothSides(isCritical)) {
                    damageStatus(playerDamage, thiefDamage, thief.getName(), isCritical);
                    playerHP -= thiefDamage;
                    thiefHP -= playerDamage;
                }

                System.out.println("****************************************************");

                sc.nextLine();
                sc.nextLine();
                System.out.println(separator);

                player.setHitpoints(playerHP);
                thief.setHitpoints(thiefHP);

                if (thiefHP < 1) {
                    System.out.println("You won! " + thief.getName() + " defeated!");
                    player.addMoney(150);
                    System.out.println("\n(You received 150 coins from loot)");
                    System.out.println("You know have " + player.getFromInventory(Item.MONEY) + " coins.\n");
                    fight = false;
                    return true;
                }

                if (playerHP < 1) {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean noCriticalDamageFromBothSides(boolean[] isCritical) {
        return !(isCritical[0] || isCritical[1]);
    }

    private void thiefStatus(Thief thief) {
        System.out.println("\t" + thief.getName() + "\'s HP: " + thief.getHitpoints() + "\n"
                + "\t" + thief.getName() + "\'s Weapon: " + thief.getWeapon() + "\n"
                + "\t" + thief.getName() + "\'s Damage: " + thief.getDamage() + "\n");
    }

    private void damageStatus(int playerDamage, int enemyDamage, String enemyName, boolean[] isCritical) {
        if (isCritical[0]) {
            System.out.println("( You slashed " + enemyName + ": " + playerDamage + " damage. [CRITICAL DAMAGE!])\n");
            System.out.println("( " + enemyName + " attacked You: " + enemyDamage + " damage. )\n");
        } else if (isCritical[1]) {
            System.out.println("( You slashed " + enemyName + ": " + playerDamage + " damage. )\n");
            System.out.println("( " + enemyName + " attacked You: " + enemyDamage + " damage. [CRITICAL DAMAGE!])\n");
        } else {
            System.out.println("( You slashed " + enemyName + ": " + playerDamage + " damage. )\n");
            System.out.println("( " + enemyName + " attacked You: " + enemyDamage + " damage. )\n");
        }
    }
}
