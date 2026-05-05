package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.guild.GuildMember;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;




public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        GuildMember engineSender = new EngineGuildMember("CouncilEngine", hall);

        System.out.println();
        System.out.println("=== Party Members ===");
        for (Hero hero : party) {
            System.out.println(hero);
        }

        System.out.println();
        System.out.println("=== Iterator Demo: Arrival Order ===");

        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest quest = ordered.next();
            questsTraversed++;

            System.out.println("Planning quest: " + quest);

            hall.dispatch("orders", engineSender, "Prepare mission plan for: " + quest.getTitle());
            messagesRouted++;
            membersNotified += 1;
        }

        System.out.println();
        System.out.println("=== Iterator Demo: High Priority Quests ===");

        QuestIterator highPriority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (highPriority.hasNext()) {
            Quest quest = highPriority.next();
            questsTraversed++;

            System.out.println("Urgent review: " + quest);

            hall.dispatch("urgent", engineSender, "Urgent quest requires attention: " + quest.getTitle());
            messagesRouted++;
            membersNotified += 3;
        }

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }

    private static class EngineGuildMember extends GuildMember {

        public EngineGuildMember(String name, GuildMediator mediator) {
            super(name, mediator);
        }

        @Override
        public void receive(String topic, GuildMember from, String payload) {
        }
    }
}
