package com.unpri.CharacterManager.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    private List<Character> characters = new ArrayList<>();

    // Menambah karakter dengan validasi duplikat nama
    public boolean addCharacter(Character c) {
        for (Character existing : characters) {
            if (existing.equals(c)) {
                System.out.println("[GAGAL] Karakter dengan nama '" + c.getName() + "' sudah ada!");
                return false;
            }
        }
        characters.add(c);
        return true;
    }

    public Character findByName(String name) {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        Character c = findByName(name);
        if (c != null) {
            characters.remove(c);
            return true;
        }
        return false;
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan salinan (defensive copy) untuk menjaga enkapsulasi
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // --- MINI-CHALLENGE (Bagian B) ---
    
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0.0; // Mencegah pembagian dengan nol
        }
        int totalLevel = 0;
        for (Character c : characters) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characters.size();
    }

    public List<Character> findByType(CharacterType type) {
        List<Character> result = new ArrayList<>();
        for (Character c : characters) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    // --- FITUR ANALITIK RANCANGAN SENDIRI (Bagian C) ---
    
    /**
     * Mencari karakter dengan nilai Attack paling tinggi di dalam manager.
     * @return Character terkuat, atau null jika list kosong.
     */
    public Character findStrongestCharacter() {
        if (characters.isEmpty()) return null;
        
        Character strongest = characters.get(0);
        for (Character c : characters) {
            if (c.getAttack() > strongest.getAttack()) {
                strongest = c;
            }
        }
        return strongest;
    }
}