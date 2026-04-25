package com.p2p.domain;

public class Borrower {
    private boolean verified;
    private int creditScore;

    public Borrower(boolean verified, int creditScore) {
        this.verified = verified;
        this.creditScore = creditScore;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getCreditScore() {
        return creditScore;
    }

    // --- HASIL REFACTOR (DOMAIN BEHAVIOR) ---
    // Logika TC-01: Cek apakah borrower bisa mengajukan pinjaman
    public boolean canApplyLoan() {
        return isVerified(); 
    }
}