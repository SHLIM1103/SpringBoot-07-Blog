package com.example.demo.sym.service;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
	private final ManagerRepository managerRepository;

	public void register(Manager manager) {
		managerRepository.save(manager);
	}

	public void insertMany(int count) {
		var list = new ArrayList<Manager>();
		Manager m = null;
		for(int i=0; i< count; i++) {
//			m = dummy.makeManager();
//			list.add(m);
		}
		managerRepository.saveAll(null);
	}

}