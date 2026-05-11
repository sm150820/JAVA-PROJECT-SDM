package com.sdinternational.events;

import com.sdinternational.domain.Company;

public class BonusEvent implements GameEvent {

    @Override
    public void apply(Company company) {

        System.out.println("💰 Bonus! Company gains 3000.");

        company.reduceCash(-3000);
    }
}