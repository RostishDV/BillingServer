package org.novonet.billing.service;

public class EntryWithSameNameAlreadyExist extends Exception {
    public EntryWithSameNameAlreadyExist(String name){
        super("An entry with the name \"" + name + "\" already exists");
    }
}
