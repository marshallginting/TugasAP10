package com.unpri.CharacterManager.model;

import java.util.Objects;

public class Character {
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    public Character(String name, CharacterType type) {
        this.name = name;
        this.type = type;
        this.level = 1;
        calculateStats();
        this.mana = 50;
    }

    public Character(String name, CharacterType type, int level, int mana) {
        this.name = name;
        this.type = type;
        this.level = level;
        calculateStats();
        setMana(mana);
    }

    private void calculateStats() {
        switch (this.type) {
            case WARRIOR:
                this.hitPoints = 100 + (this.level - 1) * 20;
                this.attack = 15 + (this.level - 1) * 5;
                break;
            case MAGE:
                this.hitPoints = 70 + (this.level - 1) * 10;
                this.attack = 25 + (this.level - 1) * 8;
                break;
            case ARCHER:
                this.hitPoints = 85 + (this.level - 1) * 15;
                this.attack = 20 + (this.level - 1) * 6;
                break;
            case ROGUE:
                this.hitPoints = 80 + (this.level - 1) * 12;
                this.attack = 22 + (this.level - 1) * 7;
                break;
        }
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CharacterType getType() { return type; }
    public void setType(CharacterType type) { this.type = type; }

    public int getLevel() { return level; }
    public void setLevel(int level) {
        if (level > 0) {
            this.level = level;
            calculateStats();
        }
    }

    public int getHitPoints() { return hitPoints; }
    public int getAttack() { return attack; }
    public int getMana() { return mana; }
    public void setMana(int mana) {
        if (mana < 0) throw new IllegalArgumentException("Mana tidak boleh negatif!");
        this.mana = mana;
    }

    public boolean isAlive() {
        return this.hitPoints > 0;
    }

    // Override equals() berdasarkan Nama (Case-insensitive atau standar)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("Character{name='%s', type=%s, level=%d, HP=%d, Attack=%d, Mana=%d}",
                name, type, level, hitPoints, attack, mana);
    }
}