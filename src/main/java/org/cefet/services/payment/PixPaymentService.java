package org.cefet.services.payment;

import java.util.UUID;

public class PixPaymentService implements IPaymentService {

    @Override
    //Esta é apenas uma simulação de pagamento
    public UUID pay(int userId, int invoiceId, Double value) {
        return UUID.randomUUID();
    }
}
