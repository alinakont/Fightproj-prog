/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fightproj3;

import java.util.Scanner;
import java.lang.Math;
/**
 *
 * @author alina.aristarhovako
 */
public class FightProj3 {
//HP for player and opponent
    static int pHP = 100;
    static int oHP = 100;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Användarnamn: ");
        String player = scan.nextLine();
        System.out.println("\nVälj en karaktär:\n1. Mage: +5 defense\n2. Swordsman: +5 dexerity\n3. Brawler (barehanded): +5 strength\n");
        System.out.print("'1', '2' eller '3': ");
        int pchar = scan.nextInt();
        scan.nextLine();
        
        String boss = "Boss Daidelus";
        
        System.out.println("\n\nDu och dina motståndare får båda 100 HP. Tryck enter efter varje slag för att fortsätta striden.\n");
        System.out.println("\n  <Press enter to continue.>");
        scan.nextLine();
//rounds keeps track of how many enemies defeated before the boss
        int rounds = 1;
        
        while (rounds !=4) {
//resetting HP
            pHP = 100;
            oHP = 100;
//opponent character randomly decided
            String opponent = "";
            int ochar = 1 + (int)(Math.random()*3);
            switch (ochar) {
                case 1:
                    opponent = "Opponent mage";
                    break;
                case 2:
                    opponent = "Opponent swordsman";
                    break;
                default:
                    opponent = "Opponent brawler";
                    break;
            }
            
            System.out.println("FIGHT!");
//fightsequence
            while (pHP >= 0 && oHP >= 0) {
    //keeping track of turns with a forloop
                for (int i=0; i<2; i++) {
                    if (i==0) {
    //opponent goes first, calling fightmethod
                        fight1(ochar, pchar, opponent, player, true);
                    } else {
    //checking that player still has HP in order to continue
                        if(pHP >= 0 && oHP >= 0) {
    //players turn, calling fightmethod
                            fight1(pchar, ochar, player, opponent, false);
                        } else {
                            if (oHP <= 0) {
                                System.out.println("GAME OVER!\n------------");
                            } else if (pHP <= 0) {
                                System.out.println("You defeated the enemy!\n-------------------------");
                            }
                        }
                    }
                    System.out.println("\n  <Press enter to continue.>");
                    scan.nextLine();
                }
            }
    //checking that player is still in the game and continuing
            if (oHP <= 0) {
                break;
            } else {
                rounds++;
            }
        }
        
        
//checking for initiation of bossfight
        if (rounds >= 3) {
            System.out.println("You defeated all the enemies, time for the boss!\n--------------------------------------------------");
//resetting HP for bossfight   
            pHP = 100;
            oHP = 100;
//bossfight
            while (pHP >= 0 && oHP >= 0) {
                for (int i=0; i<2; i++) {
                    if (i==0) {
                        bossfight(boss, player, true);
                    } else {
                        if(pHP >= 0 && oHP >= 0) {
                            bossfight(player, boss, false);
                        }else {
                            if (pHP <= 0) {
                                System.out.println("GAME OVER!\n------------");
                            }
                            if (oHP <= 0) {
                                System.out.println("Congratulations, you defeated the Boss and won the game!\n--------------------------------------------------------");
                            }
                        }
                    }
                    System.out.println("\n  <Press enter to continue.>");
                    scan.nextLine();
                }
            }
        }
    }
    
//method for fighting as mage or brawler
    static void fight1(int achar, int dchar, String aname, String dname, boolean turn) {
//random int for defense and attack
        int aint = 1 + (int)(Math.random()*30);
        int dint = 1 + (int)(Math.random()*7);
        if (aint > dint) {
            int hit = aint-dint;
            if (turn) {
//boolean true: players turn
                System.out.println(aname+"'s turn!");
                oHP -= hit;
                
//adding defense and strenght points to hit depending on character choice
    //if defender is mage
                if (dchar == 1) {
                    int defense = 0 + (int)(Math.random()*5);
                    oHP += defense;
                    System.out.println("Defense +"+defense+"!");
                }
    //if attacker is brawler
                if (achar == 3) {
                    int attack = 0 + (int)(Math.random()*5);
                    oHP -= attack;
                    System.out.println("Attack +"+attack+"!");
                }
//printout for results
                    System.out.println(aname+" dealt "+hit+" damage to "+dname+", "+dname+" now has "+oHP+" HP left.");
            } else {
//boolean false: opponents turn
                System.out.println(aname+"'s turn!");
                pHP -= hit;
//adding defense and strenght points to hit
    //if defender is mage
                if (dchar == 1) {
                    int defense = 0 + (int)(Math.random()*5);
                    pHP += defense;
                    System.out.println("Defense +"+defense+"!");
                }
    //if attacker is brawler
                if (achar == 3) {
                    int attack = 0 + (int)(Math.random()*5);
                    pHP -= attack;
                    System.out.println("Attack +"+attack+"!");
                }
//printout results
                System.out.println(aname+" dealt "+hit+" damage to "+dname+", "+dname+" now has "+pHP+" HP left.");
            }
        } else {
//if attackpoints < defendpoints = miss
            System.out.println(aname+"'s turn!");
            System.out.println(aname+" missed!");
        }
        
    }

//method for fighting as swordsman
    static void fight2(String aname, String dname, boolean turn) {
        int aint = 1 + (int)(Math.random()*30);
        int dint = 1 + (int)(Math.random()*7);
        if (aint > dint) {
            int hit = aint-dint;
            if (turn) {
                System.out.println(aname+"'s turn!");
                oHP -= hit;
                System.out.println(dname+" defense +5!");
                System.out.println(aname+" dealt "+hit+" damage to "+dname+", "+dname+" now has "+oHP+" HP left.");
            } else {
                System.out.println(aname+"'s turn!");
                pHP -= hit;
                System.out.println(aname+" dealt "+hit+" damage to "+dname+", "+dname+" now has "+pHP+" HP left.");
            }
        } else {
            System.out.println(aname+"'s turn!");
            System.out.println(aname+" missed!");
        } 
    }
    
//method for bossfight
    static void bossfight(String aname, String dname, boolean turn) {
        int aint = 1 + (int)(Math.random()*30);
        int dint = 1 + (int)(Math.random()*7);
        if (aint > dint) {
            int hit = aint-dint;
            if (turn) {
//player's turn
                System.out.println(aname+"'s turn!");
    //boss has +5 defense
                int defense = 0 + (int)(Math.random()*5);
                oHP -= hit - defense;
                System.out.println(dname+" defense +"+defense+"!");
                System.out.println(aname+" dealt "+hit+" damage to "+dname+", "+dname+" now has "+oHP+" HP left.");
            } else {
//bosse's turn
                System.out.println(aname+"'s turn!");
    //boss has +5 attack
                int attack = 0 + (int)(Math.random()*5);
                pHP -= hit + attack;
                System.out.println(aname+" attack +"+attack+"!");
                System.out.println(aname+" dealt "+hit+" damage to "+dname+", "+dname+" now has "+pHP+" HP left.");
            }
        } else {
            System.out.println(aname+"'s turn!");
            System.out.println(aname+" missed!");
        } 
    }

}
