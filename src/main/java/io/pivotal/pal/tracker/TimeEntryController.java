package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    }
    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {

        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        timeEntry = timeEntryRepository.update(id, timeEntry);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        return new ResponseEntity<>(timeEntryRepository.delete(id), HttpStatus.NO_CONTENT);
    }
}