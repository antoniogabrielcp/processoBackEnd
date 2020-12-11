package com.teste.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teste.demo.db.ProcessoRepository;
import com.teste.demo.model.ImagemTransfers;
import com.teste.demo.model.Processo;
import com.teste.demo.model.ProcessoTransfers;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoController {
	@Autowired
	ProcessoRepository processoRepository;
	byte[] arquivo;

	@GetMapping("/processos")
	public List<Processo> getProcessos() {
		return (List<Processo>) processoRepository.findAll();
	}

	@PostMapping(value = "/processos")
	public void addProcesso(@RequestBody ProcessoTransfers processoTransfers) throws IOException {
		Processo processo = new Processo();
		processo.setId(processoTransfers.getId());
		processo.setNome(processoTransfers.getNome());
		processo.setCpf(processoTransfers.getCpf());
		processo.setMatricula(processoTransfers.getMatricula());
		processo.setOrgao(processoTransfers.getOrgao());
		processo.setSetor(processoTransfers.getSetor());
		processo.setBeneficio(processoTransfers.getBeneficio());
		processo.setArquivo(arquivo);
		processoRepository.save(processo);
	}
	
	@GetMapping("/processo/{id}")
	public Optional<Processo> getProcesso(@PathVariable("id") String id) {
		return processoRepository.findById(Long.parseLong(id));
	} 

	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Processo processo = new Processo();

		arquivo = file.getBytes();		
		return ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping(path = { "/get/{id}" })
	public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {
		Optional<Processo> processo = processoRepository.findById(Long.parseLong(id));
		
		byte[] img = processo.get().getArquivo();
		
		String fileName = "teste.jpg";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentLength(img.length);
		respHeaders.setContentType(new MediaType("jpeg", "json"));
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		return new ResponseEntity<byte[]>(img, respHeaders, HttpStatus.OK);		
	}
	
}
