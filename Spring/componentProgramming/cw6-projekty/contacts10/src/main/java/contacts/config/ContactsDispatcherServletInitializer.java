package contacts.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import contacts.web.WebConfig;

public class ContactsDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Konfiguracja komponentów warstwy pośredniej (logika biznesowa) i warstwy danych
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	// Konfiguracja kontrolerów, producentów widoków i odwzorowań obsługi
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	// Które ściezki obsługuje dyspozytor
	// w przypadku "/" ten dyspozytor obsługuje wszystkie ścieżki
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}