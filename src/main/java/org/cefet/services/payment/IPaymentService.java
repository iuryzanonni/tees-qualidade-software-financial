package org.cefet.services.payment;

import java.util.UUID;

public interface IPaymentService {
    UUID pay(int userId, int invoiceId, Double value);
}
