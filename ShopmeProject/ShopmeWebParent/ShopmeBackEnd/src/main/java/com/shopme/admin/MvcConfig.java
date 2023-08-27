package com.shopme.admin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.shopme.admin.paging.PagingAndSortingArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/*
	// access image from the URL path in local
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// display the user's photos in the Web browser
		exposeDirectory("user-photos", registry);

		// display the category's images in the Web browser
		exposeDirectory("../category-images", registry);

		// display the brand's logos in the Web browser
		exposeDirectory("../brand-logos", registry);
		// display the product's images in the Web browser
		exposeDirectory("../product-images", registry);
		exposeDirectory("../site-logo", registry);

	}

	private void exposeDirectory(String pathPatten, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPatten);
		String absolutePath = path.toFile().getAbsolutePath();

		String logicalPath = pathPatten.replace("../", "") + "/**";

		registry.addResourceHandler(logicalPath)
				.addResourceLocations("file:/" + absolutePath + "/");
	}
	 */

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new PagingAndSortingArgumentResolver());
	}
}
