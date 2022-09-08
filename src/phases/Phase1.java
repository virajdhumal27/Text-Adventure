package phases;

import static staticdata.GameData.*;

public class Phase1 {

    public void townGate() {
        boolean scene = true;
        boolean isPlayerReturned = false;
        while (scene) {

            System.out.println(separator);
            System.out.println("\n\nPHASE 1: Town Gate\n");
            System.out.println("Your are at the gate of the town.");
            System.out.println("A gaurd is standing infront of you.\n");
            System.out.println("\tWhat do you want to do?");
            System.out.println("\t> 1: Talk to the gaurd.");
            System.out.println("\t> 2: Attack the gaurd.");
            System.out.println("\t> 3: Check Inventory.");

            if (!isPlayerReturned) {
                System.out.println("\t> 4: Leave.\n");
            }

            int choice = sc.nextInt();

            System.out.println(separator);
            if (choice == 1) {
                System.out.println("Gaurd: Hello there Stranger. So your name is " + player.getName() + "?"
                        + "\n" + "Strange name." + "\nAre you new here? Never seen you around.\n");

                System.out.println("You: Yes I'm new to this town.\n");
                System.out.println("Gaurd: State your purpose.\n");
                System.out.println("You: I am an Adventurer. Can you show me where the Adventure Guild is?\n");
                System.out.println("Gaurd: Just walk towards the north and you will find it yourself.\n");
                System.out.println("You: Ohh...Ok... Thank you!\n");

                isPlayerReturned = true;

                sc.nextLine();
                sc.nextLine();
                Phase2 phase2 = new Phase2();
                phase2.crossRoads();
            } else if (choice == 2) {
                System.out.println("Gaurd: How dare you? "
                        + "\n\n*Gaurd slashed you*"
                        + "\n(you lost 30HP)\n(you lost your weapon)"
                        + " \n\nGaurd: Looks like you are a spy! Prision would be good room for you!");

                player.setHitpoints(player.getHitpoints() - 30);
                player.setWeapon("None");
                player.setDamage(6);

                System.out.println(separator);
                player.playerStatus();
                System.out.println(separator);
                scene = false;
                sc.nextLine();

            } else if (choice == 3) {
                player.checkInventory();
            } else if (choice == 4) {
                System.out.println("You left.\n\nPress any key to continue...");
                sc.nextLine();
                System.out.println("You have no where to go, so you go back to town.\n\nPress any key to continue...");
                sc.nextLine();
                sc.nextLine();
                continue;
            } else {
                System.out.println("Invalid Option\n\n");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    public void story() {
        System.out.println(separator);
        System.out.println("Hello " + player.getName() + ", lets start your adventure!\n");

        player.playerStatus();

        System.out.println(separator);
        System.out.println("Press any key to continue...");
        sc.nextLine();
    }
}
