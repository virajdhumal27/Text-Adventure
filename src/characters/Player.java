package characters;

import java.util.Map;
import java.util.Map.Entry;

import static staticdata.GameData.sc;

public class Player {
    private String name;
    private int hitpoints;
    private String weapon;
    private int damage;
    private Map<Item, Integer> inventory;

    public void addMoney(int coins) {
        int current = inventory.get(Item.MONEY);
        inventory.put(Item.MONEY, coins + current);
    }

    public void addInInventory(Item item, int quantity) {
        inventory.put(item, quantity);
    }

    public int getFromInventory(Item item) {
        return inventory.getOrDefault(item, 0);
    }

    public void checkInventory() {
        boolean scene = true;
        while (scene) {
            System.out.println("========================================================");
            playerStatus();
            System.out.println("========================================================");
            System.out.println("\nInventory\n");
            for (Entry<Item, Integer> items: inventory.entrySet()) {
                System.out.println(items.getKey() + ": " +  items.getValue());
            }
            System.out.println("========================================================\n");
            System.out.println("\t>1. Equip Weapon");
            System.out.println("\t>2. Back");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\n\t> 1. Sword");
                System.out.println("\n\t> 2. Spear");
                System.out.println("\n\t> 3. Dagger");
                System.out.println("\n\t> 4. Knife");
                choice = sc.nextInt();

                if(getFromInventory(Item.SWORD) == 1 && choice == 1) {
                    equipWeapon(Item.SWORD);
                    System.out.println("You equiped Sword!");
                }
                else if(getFromInventory(Item.SPEAR) == 1 && choice == 2) {
                    equipWeapon(Item.SPEAR);
                    System.out.println("You equiped Spear!");
                }
                else if(getFromInventory(Item.DAGGER) == 1 && choice == 3) {
                    equipWeapon(Item.DAGGER);
                    System.out.println("You equiped Dagger!");
                }
                else if(getFromInventory(Item.KNIFE) == 1 && choice == 4) {
                    equipWeapon(Item.KNIFE);
                    System.out.println("You equiped Knife!");
                } else {
                    System.out.println("\nThis item is not present in your inventory.");
                }
            } else {
                scene = false;
            }
        }
    }

    public void playerStatus() {
        System.out.println("\tYour HP: " + getHitpoints() + "\n"
                + "\tYour Weapon: " + getWeapon() + "\n"
                + "\tYour Damage: " + getDamage() + "\n");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }
    public String getWeapon() {
        return weapon;
    }
    public void equipWeapon(Item weapon) {
        if(weapon == Item.KNIFE) {
            setWeapon("Knife");
            setDamage(10);
        } else if (weapon == Item.SWORD) {
            setWeapon("Sword");
            setDamage(30);
        } else if (weapon == Item.SPEAR) {
            setWeapon("Spear");
            setDamage(25);
        } else if (weapon == Item.DAGGER) {
            setWeapon("Dagger");
            setDamage(20);
        }
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public Map<Item, Integer> getInventory() {
        return inventory;
    }
    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = inventory;
    }
}
