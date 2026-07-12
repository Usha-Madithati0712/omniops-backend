package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.LedgerEntry;

import java.util.List;
import java.util.Optional;

public interface LedgerEntryService {

    List<LedgerEntry> getAllEntries();

    Optional<LedgerEntry> getEntryById(Integer id);

    LedgerEntry saveEntry(LedgerEntry entry);

    LedgerEntry updateEntry(Integer id, LedgerEntry entry);

    void deleteEntry(Integer id);

}