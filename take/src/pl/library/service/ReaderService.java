package pl.library.service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import eu.myszojelenie.repository.CompanyRepository;
import pl.library.entity.Reader;
import pl.library.entity.dto.reader.NewReaderDto;
import pl.library.entity.dto.reader.ReaderDto;
import pl.library.entity.dto.reader.ReadersDto;
import pl.library.repository.ReaderRepository;

@Stateless
public class ReaderService {

    @EJB
    ReaderRepository readerRepository;
    
	 public ReaderDto create(NewReaderDto readerDto) {
	        Reader reader = new Reader();
	        reader.setName(readerDto.getName());
	        reader.setSurname(readerDto.getSurname());
	        reader.setRentals(readerDto.getRentals());
	        Reader createdReader = readerRepository.addReader(reader);
	        return mapToDto(createdReader);
	    }
	 
	  public Optional<ReaderDto> findReaderById(Long id) {
	        Optional<Reader> reader = readerRepository.findById(id);
	        return reader.map(this::mapToDto);
	    }

	    public ReadersDto findAll() {
	        List<Reader> readers = readerRepository.findAll();
	        List<ReaderDto> dtos = readers.stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
	        return new ReadersDto(dtos);
	    }

	    private ReaderDto mapToDto(Reader reader) {
	        ReaderDto dto = new ReaderDto();
	       dto.setName(reader.getName());
	       dto.setSurname(reader.getSurname());
	       dto.setRentals(reader.getRentals());
	        return dto;
	    }

}
