package ru.netology.containerstask1.service;

public class ProdProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is prod";
    }
}