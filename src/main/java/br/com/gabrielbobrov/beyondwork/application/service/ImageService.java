package br.com.gabrielbobrov.beyondwork.application.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.gabrielbobrov.beyondwork.util.IOUtils;



@Service
public class ImageService {
	
	@Value("${beyondwork.files.foto}")
	private String fotosDir;
	
	/*@Value("${bluefood.files.categoria}")
	private String categoriasDir;
	
	@Value("${bluefood.files.comida}")
	private String comidasDir;*/
	
	public void uploadLogotipo(MultipartFile multipartFile, String fileName) {
		try {
			IOUtils.copy(multipartFile.getInputStream(), fileName, fotosDir);
		} catch (IOException e) {
			throw new ApplicattionServiceException(e);
		}
	}
	
	public void uploadComida(MultipartFile multipartFile, String fileName) {
		try {
			IOUtils.copy(multipartFile.getInputStream(), fileName, fotosDir);
		} catch (IOException e) {
			throw new ApplicattionServiceException(e);
		}
	}
	
	public byte[] getBytes(String type, String imgName) {
		
		try {
		String dir;
		
		/*if("comida".equals(type)) {
			dir = comidasDir;
		}*/ if("foto".equals(type)){
			dir= fotosDir;
			
		}/*else if("categoria".equals(type)){
			dir= categoriasDir;
	}*/
		else {
			throw new Exception(type + " não é um tipo de imagem válida");
		}
		return IOUtils.getBytes(Paths.get(dir, imgName));
	}catch (Exception e){
		throw new ApplicattionServiceException(e);
	}
		
	}
	
	

}