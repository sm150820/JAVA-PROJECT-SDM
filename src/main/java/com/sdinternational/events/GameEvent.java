package com.sdinternational.events;

import com.sdinternational.domain.Company;

public interface GameEvent {

    void apply(Company company);
}
