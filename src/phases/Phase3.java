package phases;

import static staticdata.GameData.*;

public class Phase3 {
    public void adventurersGuild() {
        System.out.println("\n\n" + separator);
        System.out.println("\nPhase 3: Warriors Fountain");
        System.out.println("\n\nYou reached Warriors Fountain, this place is full of adventurers.\n");
        System.out.println("\tWhat you would like to do?");
        System.out.println("\t> 1. Enter Adventurers Guild.");
        System.out.println("\t> 2. Enter Hospital.");
        System.out.println("\t> 3. Turn back (To the Crossroad Market).");
        
        sc.nextLine();
    }
}
