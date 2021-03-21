package boilerplate.springboot.repository;

import boilerplate.springboot.entity.GuestbookEntry;
import org.springframework.data.repository.CrudRepository;

public interface GuestbookEntryRepository extends CrudRepository<GuestbookEntry, Long> {

}
