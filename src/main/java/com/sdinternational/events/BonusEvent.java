package com.sdinternational.events;

import com.sdinternational.domain.Company;

public class BonusEvent {

    public void apply(Company company) {
        company.reduceCash(-3000); // add money
    }
}