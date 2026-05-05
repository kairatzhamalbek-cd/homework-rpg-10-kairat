package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Loremaster " + getName() + "] received from "
                + from.getName() + " | topic: " + topic + " | " + payload);

        if (topic.equals("scouting")) {
            System.out.println("[Loremaster " + getName() + "] recalling history of this location.");
        } else if (topic.equals("urgent")) {
            System.out.println("[Loremaster " + getName() + "] checking ancient records for threats!");
        } else if (topic.equals("lore")) {
            System.out.println("[Loremaster " + getName() + "] sharing knowledge about curses and legends.");
        }
    }
}