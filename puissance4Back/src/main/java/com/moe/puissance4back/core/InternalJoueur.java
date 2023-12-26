package com.moe.puissance4back.core;

public class InternalJoueur {

    private static int count = 0;

    private int id;
    private String nom;

    public InternalJoueur(String nom) {
        this.id = ++count;
        this.nom = nom;
    }

    public InternalJoueur() {
	}

	public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
    
    
}
