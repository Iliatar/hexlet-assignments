package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Book;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    public List<BookDTO> getAll() {
        List<BookDTO> books = repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
        return books;
    }

    public BookDTO create(BookCreateDTO createDTO) {
        Book model = mapper.map(createDTO);
        repository.save(model);
        return mapper.map(model);
    }

    public BookDTO findById(Long id) {
        Book model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        return mapper.map(model);
    }

    public BookDTO update(BookUpdateDTO updateDTO, Long id) {
        Book model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        mapper.update(updateDTO, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
