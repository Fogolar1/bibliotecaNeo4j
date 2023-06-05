package com.ban2.biblioteca;

import com.ban2.biblioteca.menu.MenuUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class BibliotecaDbApplication implements CommandLineRunner {

	private final MenuUtils menu;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaDbApplication.class, args);
	}

	@Override
	public void run(String... args) {
		boolean continuar;
		do
		{
			continuar = menu.menuPrincipal();
		}
		while (continuar);
	}
}
