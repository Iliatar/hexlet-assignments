package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    AuthorRepository repository;

    @Autowired
    AuthorMapper mapper;

    public List<AuthorDTO> getAll() {
        List<AuthorDTO> result = repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO createDTO) {
        Author model = mapper.map(createDTO);
        repository.save(model);
        AuthorDTO dto = mapper.map(model);
        return dto;
    }

    public AuthorDTO findById(Long id) {
        Author model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        return mapper.map(model);
    }

    public AuthorDTO update(AuthorUpdateDTO updateDTO, Long id) {
        Author model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        mapper.update(updateDTO, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
