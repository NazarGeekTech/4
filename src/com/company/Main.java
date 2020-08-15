package com.company;

import java.util.Random;

public class Main {


    public static int bossHealth = 500;
    public static int bossDamage = 200;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 310, 320, 330};
    public static int[] heroesDamage = {50, 30, 35};
    public static String[] heroesAttackType = {"physical", "Magical",
            "Kinetic", "Medic",};
    public static int roundnumber = 0;


    public static void main(String[] args) {
        System.out.println("Game starts");
        printStatistics();
        while (!isGameFinished()) {
            roundnumber++;
            System.out.println("++++++++++++   round:  " + roundnumber + "++++++++++++");
            round();
        }

    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomindex = r.nextInt(heroesAttackType.length);  // 0,1,2,3,4,5,6
        bossDefenceType = heroesAttackType[randomindex];
        System.out.println("Boss choose " + bossDefenceType);
        // random falls to medic the Boss will get medic treatment too , cause they gave Hippocratic Oath (will help everybody)
        if (bossDefenceType == heroesAttackType[3]) {
            Random med = new Random();
            int helpBoss = med.nextInt(10) + 1;
            bossHealth = bossHealth + helpBoss;
            System.out.println("Boss get help from medic for " + helpBoss + "lives");
        }
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();

        }
        System.out.println("Monitoring of Medic for each round **************");
        //  this prints how much medic helped to each heroes
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                System.out.println(heroesAttackType[i] + "after getting help from medic " + medicaltreat());

            }
        }
        if (heroesHealth[3] == 0) {
            System.out.println("the Medic has been killed ////////// ");
        }
        medicaltreat();
        printStatistics();


    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("heroes won!!!");
            return true;

        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;

                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random r = new Random();
                        int coef = r.nextInt(7) + 3; //2,3,4,5,6,7,8
                        if (bossHealth - heroesDamage[i] * coef < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coef;
                        }
                        System.out.println("critical attack:" + (heroesDamage[i] * coef));
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + "Health: " + heroesHealth[i]);
        }
    }


    public static int medicaltreat() {
        Random m = new Random();
        int medicalAddition = m.nextInt(20) + 1;
        for (int i = 0; i < heroesHealth.length; i++) {
            // under this conditional statement medic will help those who alive and with lives under 100;
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[4] > 0) {
                return heroesHealth[i] = heroesHealth[i] + medicalAddition;
            }
        }

        return medicalAddition;
    }
}