package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.LedgerEntry;
import com.omniops.omniops_backend.service.LedgerEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ledger")
@CrossOrigin(origins = "*")
public class LedgerEntryController {

    @Autowired
    private LedgerEntryService ledgerEntryService;

    @GetMapping
    public List<LedgerEntry> getAllEntries() {
        return ledgerEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public LedgerEntry getEntryById(@PathVariable Integer id) {
        return ledgerEntryService.getEntryById(id)
                .orElseThrow(() -> new RuntimeException("Ledger entry not found"));
    }

    @PostMapping
    public LedgerEntry saveEntry(@RequestBody LedgerEntry entry) {
        return ledgerEntryService.saveEntry(entry);
    }

    @PutMapping("/{id}")
    public LedgerEntry updateEntry(@PathVariable Integer id,
                                   @RequestBody LedgerEntry entry) {
        return ledgerEntryService.updateEntry(id, entry);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Integer id) {
        ledgerEntryService.deleteEntry(id);
    }
}