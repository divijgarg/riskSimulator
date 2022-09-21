package risk;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int numAttack=3;  //how many attackers
        int numDefense=2; //how many defenders


        int numWon=0;
        double sims=1000000.0;
        double attRemain=0.0;
        ArrayList<Integer> allAttackRemain= new ArrayList<>();
        ArrayList<Integer> allDefenseRemain= new ArrayList<>();
        double defRemain=0.0;

        for (int i = 0; i < sims; i++) {
            int[] results=oneSim(numAttack,numDefense);
            if(results[0]==0){
                numWon+=1;
                allAttackRemain.add(results[1]);
                attRemain+=results[1];
            }
            else{
                allDefenseRemain.add(results[2]);
                defRemain+=results[2];
            }
        }
        System.out.println(numWon/sims);
        System.out.println(attRemain/(allAttackRemain.size()));
        System.out.println(defRemain/(allDefenseRemain.size()));
//        System.out.println(allAttackRemain);
//        System.out.println(allDefenseRemain);
    }

    public static int returnRoll() {
        return (int) ((Math.random() * 6) + 1);
    }
    /*3v2
    * 2v2
    */
    public static int attackType1(int attack, int defense) {
        int[] attackRolls = new int[attack];
        int[] defenseRolls = new int[defense];

        for (int i = 0; i < attackRolls.length; i++) {
            attackRolls[i] = returnRoll();
        }
        for (int i = 0; i < defenseRolls.length; i++) {
            defenseRolls[i] = returnRoll();
        }
        sortArray(attackRolls);
//        System.out.println(Arrays.toString(attackRolls));
        sortArray(defenseRolls);
//        System.out.println(Arrays.toString(defenseRolls));

        if (attackRolls[0] > defenseRolls[0]) {
            if (attackRolls[1] > defenseRolls[1]) {
                return 0;
            }
            return 1;
        }
        if (attackRolls[1] > defenseRolls[1]) {
            return 1;
        }
        return 2;
    }
    /*
    1v2
    3v1
    2v1
    1v1
     */
    public static int attackType2(int attack, int defense) {
        int[] attackRolls = new int[attack];
        int[] defenseRolls = new int[defense];

        for (int i = 0; i < attackRolls.length; i++) {
            attackRolls[i] = returnRoll();
        }
        for (int i = 0; i < defenseRolls.length; i++) {
            defenseRolls[i] = returnRoll();
        }
        sortArray(attackRolls);
//        System.out.println(Arrays.toString(attackRolls));
        sortArray(defenseRolls);
//        System.out.println(Arrays.toString(defenseRolls));

        if (attackRolls[0] > defenseRolls[0]) {
            //defense loses 1
            return 3;
        }
        else{
            //attack loses 1
            return 4;
        }
    }

    public static int[] oneSim(int numAttack, int numDefense){
        while(numAttack>1 && numDefense>0){
            int result=90;
            //3v2
            if(numAttack>3 && numDefense>=2){
                result=attackType1(3,2);
            }
            //2v2
            else if( numAttack>2 && numDefense>=2){
                result=attackType1(2,2);
            }
            //1v2
            else if (numAttack>1 && numDefense>=2){
                result=attackType2(1,2);
            }
            //3v1
            else if(numAttack>3 && numDefense>=1){
                result=attackType2(3,1);
            }
            //2v1
            else if(numAttack>2 && numDefense>=1){
                result=attackType2(2,1);
            }
            //1v1
            else if(numAttack>1 && numDefense>=1){
                result=attackType2(1,1);
            }
            if (result==0){
                numDefense-=2;
            }
            else if (result==1){
                numAttack-=1;
                numDefense-=1;
            }
            else if (result==2){
                numAttack-=2;
            }
            else if (result==3){
                numDefense-=1;
            }
            else if (result==4) {
                numAttack -= 1;
            }
//            System.out.println("numAttack = " + numAttack);
//            System.out.println("numDefense = " + numDefense);
        }
        if(numDefense==0){
            return new int[]{0,numAttack, numDefense};
        }
        return new int[]{1, numAttack, numDefense};
    }

    public static void sortArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if (array[j]<array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }

}