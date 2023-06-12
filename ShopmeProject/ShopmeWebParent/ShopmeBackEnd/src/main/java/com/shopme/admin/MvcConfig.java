package com.shopme.admin;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// display the user's photos in the Web browser
		exposeDirectory("user-photos", registry);

		// display the category's images in the Web browser
		exposeDirectory("../category-images", registry);

		// display the brand's logos in the Web browser
		exposeDirectory("../brand-logos", registry);

	}

	private void exposeDirectory(String pathPatten, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPatten);
		String absolutePath = path.toFile().getAbsolutePath();

		String logicalPath = pathPatten.replace("../", "") + "/**";

		registry.addResourceHandler(logicalPath)
				.addResourceLocations("file:/" + absolutePath + "/");
	}

}
