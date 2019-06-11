package com.br.marte.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.marte.app.entity.Status;
import com.br.marte.app.model.StatusModel;
import com.br.marte.app.repository.StatusRepository;

@Service
@Transactional
public class StatusService {

	@Autowired
	private StatusRepository grupoRepository;

	/** CONSULA OS STATUS */
	@Transactional(readOnly = true)
	public List<StatusModel> consultarStatus() {

		List<StatusModel> statusModel = new ArrayList<StatusModel>();

		/* CONSULTA TODOS OS GRUPOS */
		List<Status> statusEntity = this.grupoRepository.findAll();

		statusEntity.forEach(status -> {
			statusModel.add(new StatusModel(status.getEvento_id(), status.getStatus()));
		});

		return statusModel;
	}

}
