package application.controllers;

import infrastructure.persistence.entity.EntryEntity;
import domain.ports.EntryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping
    public ResponseEntity<EntryEntity> create(@RequestBody EntryEntity entryEntity) {
        return ResponseEntity.ok(entryService.createEntry(entryEntity));
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> delete(@PathVariable Long entryId, @RequestParam Long userId) {
        boolean deleted = entryService.deleteEntry(entryId, userId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<EntryEntity> getById(@PathVariable Long entryId, @RequestParam Long userId) {
        return ResponseEntity.ok(entryService.getEntryById(entryId, userId));
    }

    @GetMapping
    public ResponseEntity<List<EntryEntity>> getAll(@RequestParam Long userId) {
        return ResponseEntity.ok(entryService.getAllEntries(userId));
    }
}
