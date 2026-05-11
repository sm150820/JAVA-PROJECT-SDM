package com.sdinternational.events;

import com.sdinternational.domain.Company;

public class MarketCrashEvent implements GameEvent {

    @Override
    public void apply(Company company) {

        System.out.println("⚠️ Market Crash! Company loses 5000.");

        company.reduceCash(5000);
    }
}