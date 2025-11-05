package com.example;

public class Radio implements Talkable {
    
    @Override // implementação do método talk da interface Talkable
    public String talk() {
        return "Playing music...";
    }
}
