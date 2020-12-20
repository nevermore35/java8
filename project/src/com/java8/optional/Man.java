package com.java8.optional;

import java.util.Optional;

public class Man {
    private Optional<Godness> godness = Optional.empty();

    public Man() {
    }

    public Man(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }
}
