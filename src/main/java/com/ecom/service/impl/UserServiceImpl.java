package com.ecom.service;

import com.ecom.model.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailService emailService;

	@Override
	public UserDtls saveUser(UserDtls user) {
		// Generar un token único para la verificación
		String verificationToken = UUID.randomUUID().toString();
		user.setVerificationToken(verificationToken);
		user.setAccountVerified(false);

		// Guardar usuario en la base de datos (lógica de persistencia)

		// Enviar correo de verificación
		sendVerificationEmail(user);

		return user;
	}

	@Override
	public void sendVerificationEmail(UserDtls user) {
		String verificationLink = "http://localhost:8080/verify?token=" + user.getVerificationToken();
		String subject = "Verifica tu cuenta";
		String body = "Por favor, verifica tu cuenta haciendo clic en el siguiente enlace: " + verificationLink;

		emailService.sendVerificationEmail(user.getEmail(), subject, body);
	}

	// Otros métodos de UserService...
}

