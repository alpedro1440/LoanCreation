package com.p2p.domain;

public class Loan {
    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    private Status status;

    public Loan() {
        this.status = Status.PENDING;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    // --- HASIL REFACTOR (DOMAIN BEHAVIOR) ---
    // Logika TC-03 & TC-04: Domain mengatur statusnya sendiri
    public void approve() {
        this.status = Status.APPROVED;
    }

    public void reject() {
        this.status = Status.REJECTED;
    }
}