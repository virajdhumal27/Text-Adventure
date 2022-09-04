import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import characters.Item;
import characters.Player;
import phases.Phase1;

import static staticdata.GameData.*;

public class App {

    Random random;
    

    public static void main(String[] args) throws Exception {
        App game = new App();
        game.playerSetup();
        game.phase1();
        game.close();
    }
    
    private void phase1() {
        Phase1 phase = new Phase1();
        phase.story();
        phase.townGate();
    }

    
    public void playerSetup() {

        sc = new Scanner(System.in);

        player = new Player();
        System.out.print("Please enter your name: ");
        String playerName = sc.nextLine();
        player.setName(playerName);
        player.setHitpoints(100);
        player.equipWeapon(Item.KNIFE);

        Map<Item, Integer> inventory = new HashMap<>();
        inventory.put(Item.MONEY, 1000);
        inventory.put(Item.HP_POTIONS, 0);
        inventory.put(Item.KNIFE, 1);

        player.setInventory(inventory);

    }

    public void close() {
        sc.close();
    }
}
