package com.sdinternational.events;

import com.sdinternational.domain.Company;

public class MarketCrashEvent {

    public void apply(Company company) {
        company.reduceCash(5000); // lose money
    }
}