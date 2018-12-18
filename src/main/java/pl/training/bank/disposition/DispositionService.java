package pl.training.bank.disposition;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountService;
import pl.training.bank.common.profiler.ExecutionTime;
import pl.training.bank.common.validator.Validate;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.OperationService;

@Log
@RequiredArgsConstructor
public class DispositionService {

    @NonNull
    private AccountService accountService;
    @NonNull
    private OperationService operationService;

    @ExecutionTime
    public void process(@Validate(exception = InvalidDispositionException.class) Disposition disposition) {
        Account account = accountService.getBy(disposition.getAccountNumber());
        Operation operation = operationService.getBy(disposition.getOperationName());
        operation.execute(account, disposition.getFunds());
        accountService.update(account);
    }

    public void init() {
        log.info("DispositionService: init");
    }

    public void destroy() {
        log.info("DispositionService: destroy");
    }

}
