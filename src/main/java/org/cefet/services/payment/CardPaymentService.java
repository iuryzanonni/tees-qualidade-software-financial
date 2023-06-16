package org.cefet.services.payment;

import java.util.UUID;

public class CardPaymentService implements IPaymentService {
    @Override
    public UUID pay(int userId, int invoiceId, Double value) {
        return UUID.randomUUID();
    }
}
