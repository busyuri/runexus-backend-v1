package infrastructure.persistence.adapters;

import domain.models.Entry;
import domain.ports.EntryService;
import infrastructure.persistence.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry createEntry(Entry entry) {
        entry.setEntryDate(LocalDate.now());
        return entryRepository.save(entry);
    }

    @Override
    public boolean deleteEntry(Long entryId, Long userId) {
        Entry entry = entryRepository.findById(entryId)
                .orElse(null);
        if (entry != null && entry.getUserId().equals(userId)) {
            entryRepository.deleteById(entryId);
            return true;
        }
        return false;
    }

    @Override
    public Entry getEntryById(Long entryId, Long userId) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        if (!entry.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to entry");
        }
        return entry;
    }

    @Override
    public List<Entry> getAllEntries(Long userId) {
        return entryRepository.findByUserId(userId);
    }

}
