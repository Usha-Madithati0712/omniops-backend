package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.LedgerEntry;
import com.omniops.omniops_backend.repository.LedgerEntryRepository;
import com.omniops.omniops_backend.service.LedgerEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LedgerEntryServiceImpl implements LedgerEntryService {

    @Autowired
    private LedgerEntryRepository ledgerEntryRepository;

    @Override
    public List<LedgerEntry> getAllEntries() {
        return ledgerEntryRepository.findAll();
    }

    @Override
    public Optional<LedgerEntry> getEntryById(Integer id) {
        return ledgerEntryRepository.findById(id);
    }

    @Override
    public LedgerEntry saveEntry(LedgerEntry entry) {
        return ledgerEntryRepository.save(entry);
    }

    @Override
    public LedgerEntry updateEntry(Integer id, LedgerEntry entry) {

        LedgerEntry existing = ledgerEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ledger entry not found"));

        existing.setEntryId(entry.getEntryId());
        existing.setEntryDate(entry.getEntryDate());
        existing.setEntryType(entry.getEntryType());
        existing.setDescription(entry.getDescription());
        existing.setDebit(entry.getDebit());
        existing.setCredit(entry.getCredit());
        existing.setBalance(entry.getBalance());

        return ledgerEntryRepository.save(existing);
    }

    @Override
    public void deleteEntry(Integer id) {
        ledgerEntryRepository.deleteById(id);
    }
}