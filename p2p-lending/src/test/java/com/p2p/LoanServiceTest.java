package com.p2p;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;
import com.p2p.service.LoanService;

public class LoanServiceTest {

    private static final Logger logger = LogManager.getLogger(LoanServiceTest.class);

    // TC-01
    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {
        logger.info("TC-01: Start test borrower not verified");

        Borrower borrower = new Borrower(false, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        logger.debug("Borrower verified: false, amount: {}", amount);

        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });

        logger.info("TC-01: Passed (Loan rejected because borrower not verified)");
    }

    // TC-02
    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        logger.info("TC-02: Start test invalid loan amount");

        Borrower borrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();

        logger.debug("Testing amount = 0");
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, BigDecimal.ZERO);
        });

        logger.debug("Testing amount = -500");
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, BigDecimal.valueOf(-500));
        });

        logger.info("TC-02: Passed (Loan rejected for invalid amount)");
    }

    // TC-03
    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {
        logger.info("TC-03: Start test high credit score");

        Borrower borrower = new Borrower(true, 750);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(5000);

        logger.debug("Credit score: 750, amount: {}", amount);

        Loan loan = loanService.createLoan(borrower, amount);

        logger.debug("Loan status: {}", loan.getStatus());

        assertEquals(Loan.Status.APPROVED, loan.getStatus());

        logger.info("TC-03: Passed (Loan approved)");
    }

    // TC-04
    @Test
    void shouldRejectLoanWhenCreditScoreLow() {
        logger.info("TC-04: Start test low credit score");

        Borrower borrower = new Borrower(true, 550);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(5000);

        logger.debug("Credit score: 550, amount: {}", amount);

        Loan loan = loanService.createLoan(borrower, amount);

        logger.debug("Loan status: {}", loan.getStatus());

        assertEquals(Loan.Status.REJECTED, loan.getStatus());

        logger.info("TC-04: Passed (Loan rejected)");
    }
}