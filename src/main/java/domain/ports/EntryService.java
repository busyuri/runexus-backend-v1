package domain.ports;

import domain.models.Entry;
import infrastructure.persistence.entity.EntryEntity;

import java.util.List;
import java.util.Optional;

public interface EntryService {
    Optional<Entry> findEntryById(Long entryId);
    List<Entry> findJournalByUserId(Long userId);
    List<Entry> findAllEntries();

    Entry createEntry(Entry entry);
    boolean deleteEntry(Long entryId, Long userId);

}
