package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Jo;
import com.example.demo.entity.Member;
import com.example.demo.repository.JoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoServiceImpl implements JoService {
	
	private final JoRepository repository;
	
	// ** jo selectList
	@Override
	public List<Jo> selectList() {
		return repository.findAll();
	}
	// ** jo selectOne
	@Override
	public Jo selectOne(int jno) {
		Optional<Jo> result = repository.findById(jno);
		if(result.isPresent()) return result.get();
		else return null;
	}
	// ** insert & update
	@Override
	public Jo save(Jo entity) {
		return repository.save(entity);
	}
	// ** delete
	@Override
	public void deleteById(int jno) {
		repository.deleteById(jno);
	}
	
} // JoService class
