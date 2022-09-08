package phases;

import characters.Item;
import characters.enemies.Thief;

import static staticdata.GameData.*;

public class Phase2 {
    public void crossRoads() {

        boolean scene = true;
        boolean goingWestAgain = false;
        while (scene) {
            System.out.println(separator);
            System.out.println("\n\nPHASE 2: Crossroad Market");
            System.out.println("\n\nYou reached the crossroads.\n");
            System.out.println("There are 3 roads ahead of you.\n");
            System.out.println("There are also couple of shops there.\n");
            System.out.println("\tWhat do you want to do?");
            System.out.println("\t> 1: Go North (To the Guild).");
            System.out.println("\t> 2: Go West(Unknown).");
            System.out.println("\t> 3: Go East(Castle).");
            System.out.println("\t> 4: Go Back(To the Town Gate).");
            System.out.println("\t> 5: Visit shops.");
            System.out.println("\t> 6: Check Inventory.\n");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    toGuild();
                    break;
                case 2:
                    if (goingWestAgain) {
                        goWest();
                    } else {
                        if (battleThief()) {
                            goingWestAgain = true;
                            goWest();
                        }
                    }
                    break;

                case 3:
                    break;

                case 4:
                    return;

                case 5:
                    visitShops();
                    break;
                case 6:
                    player.checkInventory();
                    break;
                default:
                    break;
            }
        }
        System.out.println(separator);
    }

    private void visitShops() {
        boolean scene = true;
        while (scene) {
            System.out.println("\n" + divider);
            System.out.println("\n\nThere are many shops, which one would you like to enter?");
            System.out.println("\t> 1. Weapons Trader.");
            System.out.println("\t> 2. Potion Shop");
            System.out.println("\t> 3. Armory.");
            System.out.println("\t> 4. Food Store.");
            System.out.println("\t> 5. Check Inventory.");
            System.out.println("\t> 6. Back.\n\n");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    enterWeaponsTrader();
                    break;

                case 2:
                    enterPotionShop();
                    break;

                case 3:
                    enterArmory();
                    break;

                case 4:
                    enterFoodStore();
                    break;

                case 5:
                    player.checkInventory();
                    break;

                case 6:
                    scene = false;
                    break;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    private void enterWeaponsTrader() {
        System.out.println(separator);
        System.out.println("\n\n Crossroad Market: Weapons Trader\n\n");
        System.out.println(
                "Trader Morshu: Hello sir, what would you like to see? Spears, swords, draggers you wanted, it's yours my\nfriend as long as you have enough money.");

        boolean scene = true;

        while (scene) {
            System.out.println("\nWhat would you buy?");
            System.out.println("\t> 1. Sword");
            System.out.println("\t> 2. Spear");
            System.out.println("\t> 3. Dagger");
            System.out.println("\t> 4. Exit Shop!");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    weaponInfo(Item.SWORD, 30, 300);
                    break;
                case 2:
                    weaponInfo(Item.SPEAR, 25, 200);
                    break;
                case 3:
                    weaponInfo(Item.DAGGER, 15, 150);
                    break;
                case 4:
                    scene = false;
                    break;

                default:
                    break;
            }
        }
    }

    private void weaponInfo(Item item, int damage, int money) {
        System.out.println("\n\nWeapon: " + item.toString());
        System.out.println("Damage: " + damage);
        System.out.println("Cost: " + money + " coins");

        System.out.println("\n\t> 1. Buy.");
        System.out.println("\t> 2. Go back.");
        System.out.println("\n\t> You have " + player.getFromInventory(Item.MONEY) + " coins.");

        int choice = sc.nextInt();
        if (choice == 1) {
            if (player.getFromInventory(item) != 0) {
                System.out.println("\nYou already have this item in your inventory.");
                return;
            }
            if ((player.getFromInventory(Item.MONEY) >= money)) {
                player.addMoney(-money);
                player.addInInventory(item, 1);
                System.out.println("\nItem bought! Your item is added to your inventory.");
                System.out.println(divider);
                sc.nextLine();
            } else {
                System.out.println("Not enough money!");
                System.out.println(
                        "\nTrader Morshu: Sorry sir, I can't give credit, come back when you're little... mm... richer!.");
            }
        }
    }

    private void enterFoodStore() {
    }

    private void enterArmory() {
    }

    private void enterPotionShop() {
        System.out.println("\n\nCrossroad Market: Potion Shop.\n\n");
        System.out.println("Shopkeeper: WELCOME! WELCOME! WELCOME!");
        System.out.println("1 HP Potion");
        System.out.println("Health recovery: 30HP");
        System.out.println("Cost: 50 Coins");
        System.out.println("\n\t>1. Buy.");
        System.out.println("\t>2. Back.\n");
        System.out.println("You have " + player.getFromInventory(Item.MONEY) + " coins.\n");
        System.out
                .println("You have " + player.getFromInventory(Item.HP_POTIONS) + " HP Potion(s) in your inventory.\n");

        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("How many Hp potions you want to buy? - ");
            int num = sc.nextInt();
            if (num < 1) {
                System.out.println("Invalid number!");
                return;
            }
            int totalAmount = num * 50;
            if ((player.getFromInventory(Item.MONEY) >= totalAmount)) {
                player.addMoney(-totalAmount);
                player.addInInventory(Item.HP_POTIONS, num);
                System.out.println("\nItem bought! Your item is added to your inventory.");
                System.out.println(divider);
                sc.nextLine();
            } else {
                System.out.println("Not enough money!");
            }
        }
    }

    private void goWest() {
        System.out.println("\nGoing West\n");
    }

    private boolean battleThief() {
        Thief thief = setupThief("Bandit", 100, "knife", 8);
        System.out.println(separator);
        System.out.println("* A " + thief.getName() + " appeared! *\n");

        System.out.println(thief.getName() + ": Hand over me your valuables and I wont harm you.");
        sc.nextLine();
        System.out.println("\n\n\tWhat do you want to do?");
        System.out.println("\t> 1: Fight the " + thief.getName() + ".");
        System.out.println("\t> 2: Hand over your money.\n");

        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("\nYou: No!, I won't.\n");
            System.out.println(thief.getName() + ": Looks like you want to go the hard way.\n");
            sc.nextLine();

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

                choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("***********************BATTLE***********************");
                    boolean[] isCritical = {false, false};
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

                    if (!(isCritical[playerDamageCriticalIndex] || isCritical[enemyDamageCriticalIndex])) {
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
                        System.out.println("You won!");
                        player.addMoney(150);
                        System.out.println("\n(You received 150 coins from loot)");
                        System.out.println("You know have " + player.getFromInventory(Item.MONEY) + " coins.\n");
                        fight = false;
                        return true;
                    }
                }
            }

        }
        return false;
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

    public void toGuild() {
        Phase3 phase3 = new Phase3();
        sc.nextLine();
        phase3.adventurersGuild();
    }

    private void thiefStatus(Thief thief) {
        System.out.println("\t" + thief.getName() + "\'s HP: " + thief.getHitpoints() + "\n"
                + "\t" + thief.getName() + "\'s Weapon: " + thief.getWeapon() + "\n"
                + "\t" + thief.getName() + "\'s Damage: " + thief.getDamage() + "\n");
    }

    private Thief setupThief(String name, int hp, String weapon, int damage) {
        Thief thief = new Thief();
        thief.setName(name);
        thief.setHitpoints(hp);
        thief.setWeapon(weapon);
        thief.setDamage(damage);
        return thief;
    }

}
