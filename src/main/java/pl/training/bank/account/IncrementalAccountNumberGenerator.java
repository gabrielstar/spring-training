package pl.training.bank.account;

import java.util.concurrent.atomic.AtomicLong;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String ACCOUNT_NUMBER_FORMAT = "%026d";

    private AtomicLong counter = new AtomicLong();

    @Override
    public String next() {
        return String.format(ACCOUNT_NUMBER_FORMAT, counter.incrementAndGet());
    }

}
