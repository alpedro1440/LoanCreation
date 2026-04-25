package com.p2p.service;

import java.math.BigDecimal;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;

public class LoanService {
    
    // Method Utama menjadi sangat rapi dan deskriptif
    public Loan createLoan(Borrower borrower, BigDecimal amount) {
        
        // 1. Jalankan semua validasi
        validateBorrower(borrower);
        validateAmount(amount);

        // 2. Buat objek Loan
        Loan loan = new Loan();
        
        // 3. Logika Bisnis (TC-03 & TC-04) mendelegasikan aksi ke Domain
        if (borrower.getCreditScore() >= 600) {
            loan.approve(); 
        } else {
            loan.reject();
        }
        
        return loan;
    }

    // --- HASIL REFACTOR (EXTRACT METHOD) ---
    
    // Mengekstrak logika validasi KYC (TC-01)
    private void validateBorrower(Borrower borrower) {
        if (!borrower.canApplyLoan()) {
            throw new IllegalArgumentException("Borrower not verified");
        }
    }

    // Mengekstrak logika validasi Nominal (TC-02)
    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}