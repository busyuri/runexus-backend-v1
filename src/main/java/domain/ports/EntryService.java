package domain.ports;

import domain.models.Entry;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EntryService {
    Entry createEntry(Entry entry);
    boolean deleteEntry(Long entryId, Long userId);

    Entry getEntryById(Long entryId, Long userId);
    List<Entry> getAllEntries(Long userId);
}
