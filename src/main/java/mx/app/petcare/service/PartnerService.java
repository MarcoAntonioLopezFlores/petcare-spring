package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import mx.app.petcare.dto.PartnerReadDto;
import mx.app.petcare.entity.Partner;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.PartnerRepository;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<List<PartnerReadDto>> findAllByStatusIsFalse(){
		try {			
			List<PartnerReadDto> partnersDto = mapList(partnerRepository.findByStatusIsFalse(), PartnerReadDto.class);
			return new ResponseEntity<List<PartnerReadDto>>(partnersDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<List<PartnerReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PartnerReadDto> findByUser(Long id){
		try {
			UserAccount user = new UserAccount();
			user.setId(id);			
			PartnerReadDto partnerDto = convertToDto(partnerRepository.findByUser(user));
			return new ResponseEntity<PartnerReadDto>(partnerDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PartnerReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PartnerReadDto> save(Partner partner) {
		try {
			partner.getUser().setPassword(passwordEncoder.encode(partner.getUser().getPassword()));
			PartnerReadDto partnerDto = convertToDto(partnerRepository.save(partner));
			return new ResponseEntity<PartnerReadDto>(partnerDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<PartnerReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PartnerReadDto> update(Partner partner) {
		try {			
			Partner partnerFound = partnerRepository.findById(partner.getId()).orElseThrow(() -> new NotFoundException("Partner does not exist"));
			partnerFound.setName(partner.getName());
			partnerFound.setDescription(partner.getDescription());
			partnerFound.setPhone(partner.getPhone());
			partnerFound.setRFC(partner.getRFC());			
			PartnerReadDto partnerDto = convertToDto(partnerRepository.save(partnerFound));
			return new ResponseEntity<PartnerReadDto>(partnerDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PartnerReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean changeStatus(long id){
		try {	
			Partner partnerFound = partnerRepository.findById(id).orElseThrow(() -> new NotFoundException("Partner does not exist"));
			partnerFound.setStatus(!partnerFound.isStatus());
			partnerRepository.save(partnerFound);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
	

	private PartnerReadDto convertToDto(Partner partner) {
		return modelMapper.map(partner, PartnerReadDto.class);
	}
}
