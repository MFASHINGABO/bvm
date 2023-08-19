package com.wisdom.bevm;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "citizen-photos";
        String dirName1 = "candidate-photos";
        String dirName2 = "supervisor-photos";
        String userDirName = "user-photos";

        Path citizenPhotosDir = Paths.get(dirName);
        Path candidatePhotosDir = Paths.get(dirName1);
        Path supervisorPhotosDir = Paths.get(dirName2);
        Path userPhotosDir = Paths.get(userDirName);

        String citizenPhotosPath = citizenPhotosDir.toFile().getAbsolutePath();
        String candidatePhotosPath = candidatePhotosDir.toFile().getAbsolutePath();
        String supervisorPhotosPath = supervisorPhotosDir.toFile().getAbsolutePath();
        String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:" + citizenPhotosPath + "/");
        registry.addResourceHandler("/" + dirName1 + "/**").addResourceLocations("file:" + candidatePhotosPath + "/");
        registry.addResourceHandler("/" + dirName2 + "/**").addResourceLocations("file:" + supervisorPhotosPath + "/");
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:" + userPhotosPath + "/"); //use ("file:/") for windows
    }

}
