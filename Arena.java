public class Arena {
    Enum<Elements> Element;

    public Arena(Elements element) {
        this.Element = element;
    }

    private final int matchAttackBonus = 2;
    private final int weaknessDefenceBonus = -1;

    public BattleResult Battle(Fighter playerFighter, Fighter compFighter){
        /*Four outcomes: 
        * playerFighter is defeated
        * compFighter is defeated
        * neither is defeated
        * both are defeated
        */

        //Check player for arena bonuses:
        int playerAttackBonus = 0;
        int playerDefBonus = 0;
        if(playerFighter.getElement() == this.Element) {
            playerAttackBonus += matchAttackBonus;
        } else if(this.Element == Elements.getElementWeakness(playerFighter.getElement())) {
            playerDefBonus += weaknessDefenceBonus;
        }
        //Check comp for area bonuses
        int compAttackBonus = 0;
        int compDefBonus = 0;
        if(compFighter.getElement() == this.Element) {
            compAttackBonus += matchAttackBonus;
        } else if(this.Element == Elements.getElementWeakness(compFighter.getElement())) {
            compDefBonus += weaknessDefenceBonus;
        }

        Boolean playerIsDead = false;
        Boolean compIsDead = false;

        if(compFighter.getAttack() + compAttackBonus > playerFighter.getDefence() + playerDefBonus){
            System.out.printf("With +%d attack %s overwhelms %s's +%d defence.\n", compFighter.getAttack() + compAttackBonus, compFighter.getName(), playerFighter.getName(), playerFighter.getDefence() + playerDefBonus); 
            playerIsDead = true;
        }

        if(playerFighter.getAttack() + playerAttackBonus > compFighter.getDefence() + compDefBonus){
            System.out.printf("%s's +%d attack destroys %s who only had %d defence.\n", playerFighter.getName(), playerFighter.getAttack() + playerAttackBonus, compFighter.getName(), compFighter.getDefence() + compDefBonus);
            compIsDead = true;
        }

        if(playerIsDead){
            if(compIsDead){
                return BattleResult.WIPEOUT; //Player and comp are dead
            } else {
                return BattleResult.LOSS; //Player but not comp is dead
            }
        } else { //player is not dead
            if(compIsDead)
                return BattleResult.WIN; //Comp but not player is dead
            else
                return BattleResult.TIE; //Neither is dead
        }
    }
}