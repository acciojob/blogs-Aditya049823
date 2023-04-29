package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] screenArray = screenDimensions.split("X");

        Image image = imageRepository2.findById(id).get();


        String[] imageArray = image.getDimensions().split("X");

        int screen1 = Integer.parseInt(screenArray[0]);
        int screen2 = Integer.parseInt(screenArray[1]);

        int image1 = Integer.parseInt(imageArray[0]);
        int image2 = Integer.parseInt(imageArray[1]);

        return noOfCounts(screen1, screen2, image1, image2);
    }

    private int noOfCounts(int s1, int s2, int i1, int i2) {
        int length1 = s1 / i1;
        int length2 = s2 / i2;

        return length1 * length2;
    }
}
