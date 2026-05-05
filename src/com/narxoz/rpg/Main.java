package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        Hero warrior = new Hero("Thorin", 120, 25, 10);
        Hero mage = new Hero("Elora", 80, 100, 30, 5, 50);

        List<Hero> party = List.of(warrior, mage);

        QuestLog log = new QuestLog();
        log.add(new Quest("Goblin Hunt", QuestPriority.LOW, 50, false));
        log.add(new Quest("Escort Caravan", QuestPriority.NORMAL, 100, false));
        log.add(new Quest("Haunted Ruins", QuestPriority.HIGH, 200, true));
        log.add(new Quest("Dragon Slayer", QuestPriority.URGENT, 500, true));
        log.add(new Quest("Bandit Camp", QuestPriority.NORMAL, 120, false));

        GuildHall hall = new GuildHall();

        Captain captain = new Captain("Arthas", hall);
        Scout scout = new Scout("Valeera", hall);
        Healer healer = new Healer("Anduin", hall);
        Quartermaster qm = new Quartermaster("Borin", hall);

        System.out.println();
        System.out.println("=== Manual Iterator Demo ===");

        QuestIterator ordered = log.ordered();
        while (ordered.hasNext()) {
            System.out.println("Ordered: " + ordered.next());
        }

        System.out.println();

        QuestIterator reverse = log.reverse();
        while (reverse.hasNext()) {
            System.out.println("Reverse: " + reverse.next());
        }

        System.out.println();
        System.out.println("=== Mediator Demo ===");

        captain.issueOrder("orders", "Prepare for next mission");
        scout.reportRoute("scouting", "Route is clear");
        healer.prepareAid("healing", "Potions ready");
        qm.requestSupplies("supplies", "Weapons prepared");

        System.out.println();
        System.out.println("=== Council Engine Run ===");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, log, hall);

        System.out.println();
        System.out.println("Final Result: " + result);

    }
}
